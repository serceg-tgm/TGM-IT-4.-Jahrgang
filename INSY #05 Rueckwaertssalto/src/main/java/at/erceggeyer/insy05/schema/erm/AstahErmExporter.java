package at.erceggeyer.insy05.schema.erm;

import at.erceggeyer.insy05.schema.SchemaExporter;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.ERDiagramEditor;
import com.change_vision.jude.api.inf.editor.ERModelEditor;
import com.change_vision.jude.api.inf.editor.TransactionManager;
import com.change_vision.jude.api.inf.exception.*;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import schemacrawler.schema.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class will export the schema of a database as an Astah ERM
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class AstahErmExporter implements SchemaExporter {

    private static final Logger logger = LogManager.getLogger();

    private ProjectAccessor accessor;
    private ERDiagramEditor diagramEditor;
    private ERModelEditor modelEditor;

    private String filename;

    private int x = 10;
    private int y = 10;

    public AstahErmExporter(boolean cracked, String filename) {
        if (cracked) {
            File dotAstah = new File(System.getProperty("user.home") + File.pathSeparator + ".astah");
            if (dotAstah.exists())
                dotAstah.delete();
        }

        this.filename = filename;

        if (!filename.contains(".")) {
            this.filename += ".asta";
        }
    }

    @Override
    public void convert(Collection<Table> tables) {
        try {
            this.accessor = AstahAPI.getAstahAPI().getProjectAccessor();

            // Get model editor to create models in a er diagram
            this.modelEditor = this.accessor.getModelEditorFactory().getERModelEditor();

            this.diagramEditor = this.accessor.getDiagramEditorFactory().getERDiagramEditor();
            // Create a project and get a root model
            this.accessor.create(this.filename);

            IModel project = this.accessor.getProject();

            // Begin transaction when creating or editing models
            TransactionManager.beginTransaction();

            // Create a model package
            IERModel erModel = this.modelEditor.createERModel(project, "Database Schema");

            // Not quite sure what to put here, but a schema is required to create entities
            IERSchema schema = erModel.getSchemata()[0];

            // Create a diagram, that is the actual panel where the elements will be placed on
            IERDiagram diagram = this.diagramEditor.createERDiagram(schema, "Database Diagram");

            // The used foreign keys will be stored in here so they don't get created twice
            List<String> foreignKeys = new ArrayList<>();

            // Set the notation. Astah supports IDEF1X and IE
            diagram.setNotation("IDEF1X");

            // Create the entities
            for (Table t : tables) {
                IEREntity entity = this.modelEditor.createEREntity(schema, t.getName(), t.getName());
                for (Column c : t.getColumns()) {
                    IERDatatype dataType = getExistingDataType(schema, c.getColumnDataType().getName());
                    if (dataType == null)
                        dataType = this.modelEditor.createERDatatype(erModel, c.getName());
                    if (!c.isPartOfForeignKey())
                        createEntity(entity, dataType, c);
                }
            }

            // Create presentations
            for (INamedElement e : this.accessor.findElements(IEREntity.class)) {
                this.diagramEditor.createNodePresentation(e, new Point2D.Double(x, y));
                setNextPresentationLocation();
            }

            // Create Relations
            for (Table t : tables) {
                for (ForeignKey fk : t.getForeignKeys()) {
                    for (ForeignKeyColumnReference fkcr : fk.getColumnReferences()) {
                        if (!foreignKeys.contains(fk.getName())) {
                            // Fallback, so FKs don't get created multiple times
                            foreignKeys.add(fk.getName());

                            // Get the elements from the model that were generated before
                            IEREntity primary = (IEREntity) this.accessor.findElements(IEREntity.class, fkcr.getPrimaryKeyColumn().getParent().getName())[0];
                            IEREntity foreign = (IEREntity) this.accessor.findElements(IEREntity.class, fkcr.getForeignKeyColumn().getParent().getName())[0];

                            // Get the graphical models
                            INodePresentation primaryPresentation = (INodePresentation) primary.getPresentations()[0];
                            INodePresentation foreignPresentation = (INodePresentation) foreign.getPresentations()[0];

                            // Create the relationship
                            IERRelationship relation = null;

                            if (fkcr.getForeignKeyColumn().isPartOfPrimaryKey()) {
                                relation = this.modelEditor.createIdentifyingRelationship(primary, foreign, "", "");
                            } else {
                                relation = this.modelEditor.createNonIdentifyingRelationship(primary, foreign, "", "");
                            }

                            relation.setCardinality(getCardinality(fkcr.getForeignKeyColumn()));

                            this.diagramEditor.createLinkPresentation(relation, primaryPresentation, foreignPresentation);
                        }
                    }
                }
            }

            TransactionManager.endTransaction();

            // Save the project
            this.accessor.save();

            // Close the project
            this.accessor.close();

            logger.info("Astah ERM was exported successfully");
        } catch (InvalidEditingException e) {
            // Abort transaction
            TransactionManager.abortTransaction();
            logger.error("An error occurred while editing the Astah ERD: " + e.getMessage());
        } catch (InvalidUsingException e) {
            e.printStackTrace();
        } catch (ProjectLockedException e) {
            // Abort transaction
            TransactionManager.abortTransaction();
            logger.error("The file that Astah tried to edit was locked. Maybe another program is using it?");
        } catch (LicenseNotFoundException e) {
            // Abort transaction
            TransactionManager.abortTransaction();
            logger.error("No licence was found. Try to run the program in cracked mode or buy a licence if you want to avoid this error.");
        } catch (IOException e) {
            // Abort transaction
            TransactionManager.abortTransaction();
            logger.error("Error while Astah was writing the changes to the file: ");
        } catch (ProjectNotFoundException e) {
            // Abort transaction
            TransactionManager.abortTransaction();
            logger.error("The Astah project was not found. Maybe another program modified the file?");
        } catch (ClassNotFoundException e) {
            // Abort transaction
            TransactionManager.abortTransaction();
            logger.error("Error while loading Astah classes. Please report this to a developer: " + e.getMessage());
        }
    }

    private void setNextPresentationLocation() {
        this.x += 200;
        if (this.x > 2000) {
            this.x = 10;
            this.y += 300;
        }
    }

    private String getCardinality(Column fk) {
        if (fk.isPartOfUniqueIndex())
            return "1";

        return "1orMore";
    }

    private void createEntity(IEREntity entity, IERDatatype dataType, Column c) throws InvalidEditingException {
        IERAttribute attribute = this.modelEditor.createERAttribute(entity, c.getName(), c.getName(), dataType);

        if (c.isPartOfPrimaryKey())
            attribute.setPrimaryKey(true);

        if (c instanceof TableConstraintColumn) {
            TableConstraintColumn tc = (TableConstraintColumn) c;
            tc.getTableConstraint().getTableConstraintType();

        }
    }

    private IERDatatype getExistingDataType(IERSchema schema, String name) {
        for (IERDatatype type : schema.getDatatypes()) {
            if (name.equalsIgnoreCase(type.getName()))
                return type;
        }

        return null;
    }

    @Override
    public void write(File file) throws IOException {
        // This method exists to fit the interface's declaration. Astah requires the file name while initializing though. This method wont do anything
    }

    @Override
    public void write(String path) throws IOException {
        // This method exists to fit the interface's declaration. Astah requires the file name while initializing though. This method wont do anything
    }

    @Override
    public void write(Appendable a) throws IOException {
        // This method exists to fit the interface's declaration. Astah requires the file name while initializing though. This method wont do anything
    }
}
