package at.erceggeyer.insy05.targets;

import at.erceggeyer.insy05.schema.rm.HtmlRmExporter;
import at.erceggeyer.insy05.schema.SchemaExporter;
import at.sgeyer.insy04.dbms.DBMS;
import at.sgeyer.insy04.dbms.HostnameDBMS;
import at.sgeyer.insy04.exporting.Exportable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This Exporter targets will export the metadata of the database as relational model
 *
 * @author Stefan Geyer
 */
public class RelationalModelTarget implements Exportable {

    private static final Logger logger = LogManager.getLogger();

    private String filename;

    public RelationalModelTarget(String outFileName) {
        this.filename = outFileName;
    }

    @Override
    public void export(DBMS dbms) {
        SchemaCrawlerOptions options = new SchemaCrawlerOptions();
        // Set what details are required in the schema - this affects the
        // time taken to crawl the schema
        options.setSchemaInfoLevel(SchemaInfoLevel.standard());

        Database database = null;

        try {
            database = SchemaCrawlerUtility.getDatabase(dbms.getConnection(), options);
        } catch (SchemaCrawlerException e) {
            e.printStackTrace();
        }

        Schema schema = null;

        if (dbms instanceof HostnameDBMS) {
            HostnameDBMS hdbms = (HostnameDBMS) dbms;
            schema = database.getSchema(hdbms.getDatabase());
        } else {
            if (database.getSchemas().size() > 0) {
                schema = new ArrayList<>(database.getSchemas()).get(0);
            } else {
                logger.error("Could not load schema from database while trying to generate the RM. No schemas found.");
                System.exit(1);
            }
        }

        try {
            SchemaExporter exporter = new HtmlRmExporter();
            exporter.convert(database.getTables(schema));
            exporter.write(this.filename + ".html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
