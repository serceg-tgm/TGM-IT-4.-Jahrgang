package at.sgeyer.insy04.dbms;

import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySQL implementation of DBMS. MySQL is hosted on a remote server so the child class HostnameDBMS was chosen. <br/>
 * This class has default visibility. It can be initialized through the DBMSFactory
 *
 * @author Stefan Geyer
 * @version 1.0
 */
class MySQL extends HostnameDBMS {

    private String charset;

    public MySQL(Logger log, String hostname, String username, String password, String database, int port, String charset) {
        super(log, hostname, username, password, database, port);
        this.charset = charset;
    }

    @Override
    boolean initialize() {
        try {
            Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource").newInstance();
            return true;
        } catch (Exception e) {
            getLogger().error("MySQL DataSource class missing: " + e.getMessage() + ".");
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean open() {
        try {
            if (initialize()) {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + getHostname() + ":" + getPort() + "/" + getDatabase() + (this.charset != null && this.charset.length() > 0 ? "?characterEncoding=" + this.charset : ""), getUsername(), getPassword());
                return true;
            }
        } catch (SQLException e) {
            this.getLogger().error(e.getMessage());
            System.exit(1);
        }

        return false;
    }
}
