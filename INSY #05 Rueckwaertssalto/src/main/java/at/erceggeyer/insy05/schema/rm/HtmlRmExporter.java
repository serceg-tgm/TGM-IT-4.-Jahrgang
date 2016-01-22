package at.erceggeyer.insy05.schema.rm;

import at.erceggeyer.insy05.schema.SchemaExporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import schemacrawler.schema.Column;
import schemacrawler.schema.ForeignKey;
import schemacrawler.schema.ForeignKeyColumnReference;
import schemacrawler.schema.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * This implementation of the RmExporter will export the schema in HTML code.<br>
 * Foreign Keys: italic<br>
 * Primary Keys: underline<br>
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class HtmlRmExporter implements SchemaExporter {

    private static final Logger logger = LogManager.getLogger();

    private String html;

    public HtmlRmExporter() {
        this.html = "";
    }

    @Override
    public void convert(Collection<Table> tables) {
        // HTML Structure; display:inline-block; will display every div in the same line
        this.html += "<html><head><style>div { display:inline-block; }</style></head><body>";

        for (Table t : tables) {
            // Remove elements the schema crawler might pull from the database
            String tableName = t.getName().replace("`", "");
            this.html += tableName + " ( ";
            for (Column c : t.getColumns()) {
                String columnName = c.getName().replace("`", "");
                // Generate element depending on the column properties
                if (c.isPartOfPrimaryKey() && c.isPartOfForeignKey()) {
                    this.html += "<div style='font-style: italic; text-decoration: underline;'>" + c.getReferencedColumn().getParent().getName() + "." + columnName + "</div>";
                } else if (c.isPartOfPrimaryKey()) {
                    this.html += "<div style='text-decoration: underline;'>" + columnName + "</div>";
                } else if (c.isPartOfForeignKey()) {
                    this.html += "<div style='font-style: italic;'>" + c.getReferencedColumn().getParent().getName() + "." + columnName + "</div>";
                } else {
                    this.html += "<div>" + columnName + "</div>";
                }

                this.html += ", ";
            }

            this.html = this.html.substring(0, this.html.lastIndexOf(','));

            this.html += " ) <br>";
        }

        // Close HTML structure again
        this.html += "</html></body>";

        logger.info("HTML RM was exported successfully");
    }

    @Override
    public void write(File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(this.html);
        bw.flush();
        bw.close();
    }

    @Override
    public void write(String path) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
        bw.write(this.html);
        bw.flush();
        bw.close();
    }

    @Override
    public void write(Appendable a) throws IOException {
        a.append(this.html);
    }
}
