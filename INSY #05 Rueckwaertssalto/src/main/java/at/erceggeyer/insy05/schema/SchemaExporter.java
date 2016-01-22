package at.erceggeyer.insy05.schema;

import schemacrawler.schema.Table;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Implementations of this class are meant to export a db's schema to relational model
 *
 * @author Stefan Geyer
 * @version 0.1
 */
public interface SchemaExporter {

    /**
     * Converts the content of one Database and adds them to the export context
     *
     * @param tables The tables that will be added to the export context
     */
    public abstract void convert(Collection<Table> tables);

    /**
     * Dumps the context of the loaded schemas to a file
     *
     * @param file The file that the data will be written to
     * @throws java.io.IOException Will be thrown if the file access failed
     */
    public abstract void write(File file) throws IOException;

    /**
     * Dumps the context of the loaded schemas to a file
     *
     * @param path The patch of the file that the data will be written to
     * @throws java.io.IOException Will be thrown if the file access failed
     */
    public abstract void write(String path) throws IOException;

    /**
     * Dumps the context of the loaded schemas
     *
     * @param a Will write the context to the given writer
     * @throws java.io.IOException Will be thrown if the file access failed
     */
    public abstract void write(Appendable a) throws IOException;
}
