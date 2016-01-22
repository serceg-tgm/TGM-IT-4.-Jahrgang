package at.erceggeyer.insy05;

import at.erceggeyer.insy05.targets.EntityRelationshipDiagramTarget;
import at.sgeyer.insy04.exporting.Exportable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.erceggeyer.insy05.cli.CommandLineParser;
import at.erceggeyer.insy05.cli.Input;
import at.erceggeyer.insy05.targets.RelationalModelTarget;
import at.sgeyer.insy04.dbms.DBMS;
import at.sgeyer.insy04.dbms.DBMSException;
import at.sgeyer.insy04.dbms.DBMSFactory;
import at.sgeyer.insy04.exporting.Exporter;

/**
 * Main class, calls both targets (RM and ERD) and executed them
 *
 * @author Stefan Geyer
 * @version 1.0
 */
public class Main {
    private static final Logger logger = LogManager.getLogger();

    public Main(String[] args) {

		// Methode zum Parsen der Argumente wird vom CommandLineParser ausgefuehrt
		Input cli = new CommandLineParser();
		cli.parseArgs(args);

        // Init DBMS
        DBMS dbms = DBMSFactory.getHostnameDBMS(cli.getDBMS(), LogManager.getRootLogger(), cli.getHostname(), cli.getUsername(), cli.getUserpwd(), cli.getDBName(), cli.getPort());

        // Init Exporter
        Exporter exporter = null;
        try {
            exporter = new Exporter(dbms);
        } catch (DBMSException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }

        // Init the targets
        Exportable rmTarget = new RelationalModelTarget(cli.getFilename());
        Exportable erdTarget = new EntityRelationshipDiagramTarget(cli.isCracked(), cli.getFilename());

        // Add the targets to the exporter
        exporter.addTarget(rmTarget);
        exporter.addTarget(erdTarget);

        //export

        exporter.export();
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
