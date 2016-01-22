package at.sgeyer.insy04.dbms;

import at.sgeyer.insy04.query.QueryBuilder;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstract class that will be the superclass for every DBMS implemented in the future
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public abstract class DBMS {

    protected Connection connection;
    private Logger logger;

    public DBMS(Logger l) {
        this.logger = l;
    }

    /**
     * This method loads the driver
     *
     * @return Returns true if the driver was loaded successfully
     */
    abstract boolean initialize();

    /**
     * This method initializes the connection
     *
     * @return Returns if the connection was opened successfully
     */
    public abstract boolean open();

    /**
     * Closes the connection if it was opened
     *
     * @return Returns if the connection was closed successfully
     */
    public boolean close() {
        try {
            this.connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Queries a SQL Statement at the opened connection
     *
     * @param query The SQL Statement that will be queried
     * @return Returns the result for the query if successful, otherwise null
     */
    public ResultSet query(String query) throws DBMSException, SQLException {
        System.out.println(query);
        if (isOpen()) {
            return this.connection.prepareStatement(query).executeQuery();
        } else throw new DBMSException("An opened connection is required for querying");
    }

    /**
     * Calls toString() method from builder and calls query(String)
     *
     * @param builder Builder that will be converted to a String
     * @return Returns the result for the query if successful, otherwise null
     * @throws at.sgeyer.insy04.dbms.DBMSException
     */
    public ResultSet query(QueryBuilder builder) throws DBMSException, SQLException {
        return this.query(builder.toString());
    }

    /**
     * @return Returns true if connection was opened before
     */
    public boolean isOpen() {
        if (connection != null) {
            try {
                // Checks if the connection is valid for 1 second
                if (connection.isValid(1))
                    return true;
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Logger getLogger() {
        return logger;
    }
}
