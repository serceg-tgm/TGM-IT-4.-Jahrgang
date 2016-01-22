package at.sgeyer.insy04.exporting;

import at.sgeyer.insy04.dbms.DBMS;
import at.sgeyer.insy04.dbms.DBMSException;

import java.util.ArrayList;
import java.util.List;

/**
 * The exporter stores all registered targets and executes them all at once
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class Exporter {

    private DBMS dbms;

    private List<Exportable> targets;

    public Exporter(DBMS dbms) throws DBMSException {
        this.targets = new ArrayList<>();

        if (dbms == null)
            throw new DBMSException("DBMS in Exporter cannot be null");

        this.dbms = dbms;
    }

    public void addTarget(Exportable e) {
        this.targets.add(e);
    }

    public void removeTarget(Exportable e) {
        this.targets.remove(e);
    }

    /**
     * Exports all registered targets
     */
    public void export() {
        this.dbms.open();

        for (Exportable e : targets)
            e.export(this.dbms);

        this.dbms.close();
    }

}
