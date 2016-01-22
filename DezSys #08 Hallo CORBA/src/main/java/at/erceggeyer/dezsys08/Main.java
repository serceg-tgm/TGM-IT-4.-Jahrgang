package at.erceggeyer.dezsys08;

import at.erceggeyer.dezsys08.echo.Echo;
import at.erceggeyer.dezsys08.echo.EchoHelper;
import com.sun.corba.se.impl.logging.ORBUtilSystemException;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * The client-side in Java:
 * <p>
 * - Creates and initializes an ORB
 * - Obtains a reference to the root naming context
 * - Looks up "Echo" in the naming context and receives a reference to that CORBA object
 * - Invokes the object's echoString() operations and prints the result
 *
 * @author http://docs.oracle.com/javase/7/docs/technotes/guides/idl/jidlExample.html#client
 * @author Stefan Geyer (changed "Hello" to "Echo")
 * @author Stefan Erceg (comments)
 */
public class Main {

    static Echo echoImpl;

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent[] subContext = {new NameComponent("test", "my_context"), new NameComponent("Echo", "Object")};
            echoImpl = EchoHelper.narrow(ncRef.resolve(subContext));

            System.out.println("Obtained a handle on server object: " + echoImpl);
            System.out.println(echoImpl.echoString("Hallo CORBA!"));

        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
            System.out.println(1);
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
            System.out.println(2);

        } catch (NotFound notFound) {
            notFound.printStackTrace();
            System.out.println(3);

        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
            System.out.println(4);

        }
    }

}
