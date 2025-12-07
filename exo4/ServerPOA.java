import MatrixOpsApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class ServerPOA {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            
            // Create servant and register it with the ORB
            MatrixOpsPOAImpl matrixOpsImpl = new MatrixOpsPOAImpl();
            
            // Get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(matrixOpsImpl);
            MatrixOps href = MatrixOpsHelper.narrow(ref);
            
            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // Bind the Object Reference in Naming
            String name = "MatrixOps";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
            
            System.out.println("MatrixOps Server ready and waiting ...");
            
            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
        
        System.out.println("MatrixOps Server Exiting ...");
    }
}
