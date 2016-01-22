package at.sgeyer.insy04.exporting;

import at.sgeyer.insy04.dbms.DBMS;

/**
 * Classes which implement this interface should do all the query related stuff in the export method
 * since it will be called in the export method of the Exporter if the class was registered
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public interface Exportable {

    /**
     * This method contains query related stuff (the goal for the specific targets).
     *
     * @param dbms The DBMS all the data can be gathered from
     */
    public void export(DBMS dbms);
}
