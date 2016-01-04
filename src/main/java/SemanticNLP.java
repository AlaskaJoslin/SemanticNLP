
import db.*;
import server.*;

/**
 *
 * @author matthewjoslin
 */
public class SemanticNLP {
  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try
      {
        db_connector semantic_db = new db_connector(args);
        client_connector.initialize(args);
        client_connector.getInstance().run();
        attachShutDownHook();
      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
    }

    public static void attachShutDownHook(){
      Runtime.getRuntime().addShutdownHook(new Thread() {
        public void run() {
          System.out.println("Attempting shutdown");
          try
          {
            client_connector.getInstance().shutdownAndAwaitTermination();
          }
          catch(Exception ex)
          {
            System.out.println("Shutdown wasn't graceful.");
          }
        }
      });
      System.out.println("Attached shut down hook");
    }
}
