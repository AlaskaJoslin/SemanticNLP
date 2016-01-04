package server;

import java.net.*;

class SemanticThread implements Runnable {
   private final Socket socket;
   SemanticThread(Socket socket) { this.socket = socket; }
   public void run() {
     // read and service request on socket

   }
 }
