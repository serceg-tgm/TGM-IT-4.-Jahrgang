package at.sgeyer.insy04.query;

/**
 * This class was created to add restrictions to the QueryBuilder.
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class QueryBuilderException extends RuntimeException {

    private String message;

    public QueryBuilderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
