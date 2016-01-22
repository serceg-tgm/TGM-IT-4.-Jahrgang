package at.sgeyer.insy04.query.mysql;

import at.sgeyer.insy04.dbms.DBMS;
import at.sgeyer.insy04.dbms.DBMSException;
import at.sgeyer.insy04.query.QueryBuilder;
import at.sgeyer.insy04.query.QueryBuilderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * SELECT query builder.<br>
 * Date Created: 2012-09-09 17:45.
 *
 * @author Nicholas Solin
 * @author Stefan Geyer (modified)
 */
public class SelectQueryBuilder implements QueryBuilder {
    private static final Logger logger = LogManager.getLogger();

    public Duplicates duplicates = null;
    public Cache cache = null;
    private DBMS db;
    private String[] conditionals = {"OR", "||", "XOR", "AND", "&&"};
    private HashSet<String> columns = new HashSet<String>();
    private HashSet<String> tables = new HashSet<String>();
    private boolean high = false;
    private boolean join = false;
    private boolean small = false;
    private boolean big = false;
    private boolean buffer = false;
    private boolean calc = false;
    private ArrayList<String> where = new ArrayList<String>();
    private ArrayList<String> groupBy = new ArrayList<String>();
    private ArrayList<String> having = new ArrayList<String>();
    private ArrayList<String> orderBy = new ArrayList<String>();
    private int[] limit = null;
    private String procedure = "";
    private Into into = null;
    private String file = "";
    private String charset = "";
    private String options = "";
    private HashSet<String> variables = new HashSet<String>();
    private Boolean update = null;

    public SelectQueryBuilder(DBMS db) {
        try {
            setDBMS(db);
        } catch (DBMSException e) {
            logger.error(e.getMessage());
        }
    }

    public DBMS getDBMS() {
        return db;
    }

    private void setDBMS(DBMS db) throws DBMSException {
        if (db == null)
            throw new DBMSException("DBMS cannot be null in SELECT statement.");

        this.db = db;
    }

    public ArrayList<String> getColumns() {
        return new ArrayList<String>(columns);
    }

	/*public Select(DBMS db, String columns, String tables) throws DBMSException {
        //setDBMS(db);
		setColumns(columns);
		setTables(tables);
	}*/

    public SelectQueryBuilder columns(String... columns) {
        int counter = 0;
        //int added = 0;
        for (String column : columns) {
            if (column != null && column.length() != 0) {
                if (!column.contains("`")) {
                    this.columns.add(column);
                    //added++;
                } else {
                    db.getLogger().error("Column " + column + " in SELECT statement cannot have backticks.");
                }
            } else {
                db.getLogger().error("Column at position " + counter + " cannot be null or empty in SELECT statement.");
            }
            counter++;
        }
        return this;
    }

    public ArrayList<String> getTables() {
        return new ArrayList<String>(tables);
    }

    public SelectQueryBuilder tables(String... tables) {
        List<String> tmp = Arrays.asList(tables);
        if (tmp.contains("*")) {
            this.tables.add("*");
            return this;
        }

        for (String table : tmp) {
            if (table != null && !table.isEmpty()) {
                if (!table.contains("`")) {
                    this.tables.add(table);
                } else {
                    db.getLogger().error("Skipping table " + table + " in SELECT statement that has backticks.");
                }
            } else {
                db.getLogger().error("Skipping table " + table + " in SELECT statement for being null or empty.");
            }
        }
        return this;
    }

    public SelectQueryBuilder duplicates(Integer duplicates) {
        if (duplicates == null) {
            this.duplicates = null;
            return this;
        }

        this.duplicates = Duplicates.byID(duplicates);
        return this;
    }

    public SelectQueryBuilder high(boolean high) {
        this.high = high;
        return this;
    }

    public SelectQueryBuilder join(boolean join) {
        this.join = join;
        return this;
    }

    public SelectQueryBuilder small(boolean small) {
        this.small = small;
        return this;
    }

    public SelectQueryBuilder big(boolean big) {
        this.big = big;
        return this;
    }

    public SelectQueryBuilder buffer(boolean buffer) {
        this.buffer = buffer;
        return this;
    }

    public SelectQueryBuilder cache(Boolean cache) {
        if (cache == null) {
            this.cache = null;
            return this;
        }

        if (cache)
            this.cache = Cache.SQL_CACHE;
        else if (!cache)
            this.cache = Cache.SQL_NO_CACHE;
        return this;
    }

    public SelectQueryBuilder calc(boolean calc) {
        this.calc = calc;
        return this;
    }

    public SelectQueryBuilder where(String condition) {
        if (!checkCondition(condition))
            return this;

        where.add(condition);
        return this;
    }

    public SelectQueryBuilder where(String conditional, String condition) {
        if (where.size() != 0) {
            if (!checkConditional(conditional))
                return this;
        } else {
            db.getLogger().error("Cannot add conditional " + conditional + " to the front of a WHERE statement.");
        }
        if (!checkCondition(condition))
            return this;

        if (where.size() != 0)
            where.add(conditional);
        where.add(condition);
        return this;
    }

    public SelectQueryBuilder groupBy(String expression) {
        if (!validString(expression, "Skipping null or empty GROUP BY expression."))
            return this;

        groupBy.add(expression);
        return this;
    }

    public SelectQueryBuilder groupBy(String expression, boolean ascending) {
        if (!validString(expression, "Skipping null or empty GROUP BY expression."))
            return this;

        groupBy.add(expression);
        groupBy.add(ascending ? "ASC" : "DESC");
        return this;
    }

    public SelectQueryBuilder having(String condition) {
        if (!checkCondition(condition))
            return this;

        having.add(condition);
        return this;
    }

    public SelectQueryBuilder having(String conditional, String condition) {
        if (having.size() != 0) {
            if (!checkConditional(conditional))
                return this;
        } else {
            db.getLogger().error("Cannot add conditional " + conditional + " to the front of a HAVING statement.");
        }
        if (!checkCondition(condition))
            return this;

        if (having.size() != 0)
            having.add(conditional);
        having.add(condition);
        return this;
    }

    public SelectQueryBuilder orderBy(String expression) {
        if (!validString(expression, "Skipping null or empty ORDER BY expression."))
            return this;

        orderBy.add(expression);
        return this;
    }

    public SelectQueryBuilder orderBy(String expression, boolean ascending) {
        if (!validString(expression, "Skipping null or empty ORDER BY expression."))
            return this;

        orderBy.add(expression);
        orderBy.add(ascending ? "ASC" : "DESC");
        return this;
    }

    public SelectQueryBuilder limit(int rows) {
        this.limit = new int[2];
        this.limit[0] = 0;
        this.limit[1] = rows;
        return this;
    }

    public SelectQueryBuilder limit(int offset, int rows) {
        this.limit = new int[2];
        this.limit[0] = offset;
        this.limit[1] = rows;
        return this;
    }

    public SelectQueryBuilder limit() {
        this.limit = null;
        return null;
    }

    public SelectQueryBuilder procedure(String procedure) {
        if (!validString(procedure, "Skipped null or empty procedure."))
            return this;

        this.procedure = procedure;
        return this;
    }

    public SelectQueryBuilder outfile(String filename) {
        into = Into.OUT;
        file = filename;
        this.charset = "";
        this.options = "";
        variables = new HashSet<String>();
        return this;
    }

    public SelectQueryBuilder outfile(String filename, String options) {
        into = Into.OUT;
        file = filename;
        this.charset = "";
        this.options = options;
        variables = new HashSet<String>();
        return this;
    }

    public SelectQueryBuilder outfile(String filename, String charset, String options) {
        into = Into.OUT;
        file = filename;
        this.charset = charset;
        this.options = options;
        variables = new HashSet<String>();
        return this;
    }

    public SelectQueryBuilder dumpfile(String filename) {
        into = Into.DUMP;
        file = filename;
        variables = new HashSet<String>();
        return this;
    }

    public SelectQueryBuilder into(String variable) {
        into = Into.VARIABLE;
        file = "";
        variables.add(variable);
        return this;
    }

    public SelectQueryBuilder update(Boolean update) {
        this.update = update;
        return this;
    }

    public String toString() {
        if (columns.isEmpty())
            throw new QueryBuilderException("Cannot build SELECT statement");

        String string = "SELECT " + (duplicates != null ? duplicates + " " : "");
        string += (high ? "HIGH_PRIORITY " : "");
        string += (join ? "STRAIGHT_JOIN " : "");
        string += (small ? "SQL_SMALL_RESULT " : "");
        string += (big ? "SQL_BIG_RESULT " : "");
        string += (buffer ? "SQL_BUFFER_RESULT " : "");
        string += (cache != null ? cache + " " : "");
        string += (calc ? "SQL_CALC_FOUND_ROWS " : "");

        string += addCommas(columns) + " ";

        if (!tables.isEmpty()) {
            string += "FROM " + addCommas(tables) + " ";

            if (!where.isEmpty()) {
                string += "WHERE ";
                for (String w : where)
                    string += w + " ";
            }

            if (!groupBy.isEmpty()) {
                string += "GROUP BY ";
                string += addCommas(groupBy);
            }

            if (!having.isEmpty()) {
                string += "HAVING ";
                for (String h : having)
                    string += h + " ";
            }

            if (!orderBy.isEmpty()) {
                string += "ORDER BY ";
                string += addCommas(orderBy).replace(",", "");
            }

            if (limit != null)
                string += "LIMIT " + limit[0] + ", " + limit[1];

            if (procedure != "")
                string += "PROCEDURE " + procedure;

            if (into != null) {
                switch (into) {
                    case OUT:
                        string += "INTO OUTFILE '" + file + "' ";
                        if (charset != "")
                            string += "CHARACTER SET " + charset + " ";
                        string += options;
                        break;

                    case DUMP:
                        string += "INTO DUMPFILE '" + file + "' ";
                        break;

                    case VARIABLE:
                        string += "INTO ";
                        string += addCommas(variables);
                        break;
                }
            }

            string += (update != null ? (update ? "FOR UPDATE" : "LOCK IN SHARE MODE") : "");
        }

        return string;
    }

    public ResultSet execute() throws SQLException, DBMSException {
        if (columns.isEmpty())
            throw new QueryBuilderException("Must specify at least one column in a SELECT statement.");

        return db.query(this);
    }

    @Deprecated
    private boolean checkCondition(String condition) {
        if (condition == null || condition.length() == 0) {
            db.getLogger().error("Skipping null or empty WHERE condition.");
            return false;
        }
        return true;
    }

    private boolean checkConditional(String conditional) {
        validString(conditional, "Skipping null or empty WHERE conditional.");
        for (String c : conditionals)
            if (conditional.equals(c))
                return true;
        db.getLogger().error("Skipping unknown conditional " + conditional + ".");
        return false;
    }

    private boolean validString(String string, String error) {
        if (string == null || string.length() == 0) {
            db.getLogger().error(error);
            return false;
        }
        return true;
    }

    private String addCommas(Collection<String> strings) {
        String output = "";
        for (String string : strings) {
                output += string + ", ";
        }
        return output.substring(0, output.lastIndexOf(','));
    }

    private enum Duplicates {
        ALL("ALL"), DISTINCT("DISTINCT"), DISTINCTROW("DISTINCTROW");

        private String string;

        private Duplicates(String string) {
            this.string = string;
        }

        public static Duplicates byID(int id) throws QueryBuilderException {
            if (id < 0 || 2 < id)
                throw new QueryBuilderException("Duplicates must be between 0 and 2.");
            return Duplicates.values()[id];
        }

        public String toString() {
            return string;
        }
    }

    private enum Cache {
        SQL_CACHE,
        SQL_NO_CACHE;
    }

    private enum Into {
        OUT,
        DUMP,
        VARIABLE;
    }
}
