package com.alaskajoslin.app.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.simple.*;
import org.apache.logging.log4j.status.*;
import org.apache.logging.log4j.util.*;

public class client_connector {
  private static final Logger LOGGER = LogManager.getLogger();
  private static client_connector singleton = null;
  private final ServerSocket semantic_traffic;
  private final ExecutorService thread_pool;

  private client_connector(String[] args) throws IOException
  {
    //semantic_traffic = new ServerSocket(Integer.parseInt(args[0]));
    semantic_traffic = new ServerSocket(800);
    thread_pool = Executors.newCachedThreadPool();
  }

  public static client_connector initialize(String[] args) throws IOException {

      if(singleton == null) {
         //Make this thread safe
         singleton = new client_connector(args);
      }
      return singleton;
   }

  public static client_connector getInstance() throws Exception {
      if(singleton == null) {
        throw new Exception("You need to call initialize first");
      }
      return singleton;
   }

   public void run() { // run the service
     try {
       for (;;) {
         thread_pool.execute(new SemanticThread(semantic_traffic.accept()));
       }
     } catch (IOException ex) {
       shutdownAndAwaitTermination();
     }
   }

   public void shutdownAndAwaitTermination() {
     thread_pool.shutdown(); // Disable new tasks from being submitted
     try {
       System.out.println("Attempting termination.");
       // Wait a while for existing tasks to terminate
       if (!thread_pool.awaitTermination(60, TimeUnit.SECONDS)) {
         thread_pool.shutdownNow(); // Cancel currently executing tasks
         // Wait a while for tasks to respond to being cancelled
         if (!thread_pool.awaitTermination(60, TimeUnit.SECONDS))
             System.err.println("Pool did not terminate");
       }
     } catch (InterruptedException ie) {
       // (Re-)Cancel if current thread also interrupted
       thread_pool.shutdownNow();
       // Preserve interrupt status
       Thread.currentThread().interrupt();
     }
   }
}
