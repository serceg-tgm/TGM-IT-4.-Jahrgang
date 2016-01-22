package at.sgeyer.insy04.dbms;

import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PostgreSQL implementation of DBMS. PostgreSQL is hosted on a remote server so the child class HostnameDBMS was chosen. <br/>
 * This class has default visibility. It can be initialized through the DBMSFactory
 *
 * @author Stefan Geyer
 * @version 1.0
 */
class PostgreSQL extends HostnameDBMS {

    private String charset;

    public PostgreSQL(Logger log, String hostname, String username, String password, String database, int port, String charset) {
        super(log, hostname, username, password, database, port);
        this.charset = charset;
    }

    @Override
    boolean initialize() {
        try {
            Class.forName("org.postgresql.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            this.getLogger().error("PostgreSQL driver class missing: " + e.getMessage() + ".");
            return false;
        }
    }

    @Override
    public boolean open() {
        if (initialize()) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://" + getHostname() + ":" + getPort() + "/" + getDatabase() + "?charSet=" + this.charset, getUsername(), getPassword());
                return true;
            } catch (SQLException e) {
                this.getLogger().error(e.getMessage());
                System.exit(1);
                return false;
            }
        } else {
            return false;
        }
    }
}
