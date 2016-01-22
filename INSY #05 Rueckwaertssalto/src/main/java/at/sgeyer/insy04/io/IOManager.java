package at.sgeyer.insy04.io;

/**
 * Every implementation has a source where data can be gathered from/to.<br>
 * This interface is ment to generalize those classes to one.
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public interface IOManager {

    /**
     * This operation might be blocking
     *
     * @return Returns a whole line that was imported from a source.
     */
    public String readLine();

    /**
     * This operation might be blocking
     *
     * @param line The line that will be written to the source.
     */
    public void writeLine(String line);
}
