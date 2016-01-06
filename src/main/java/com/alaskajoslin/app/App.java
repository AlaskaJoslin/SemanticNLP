package com.alaskajoslin.app;

//Import my code
import db.*;
import server.*;
//Import server code
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author matthewjoslin
 */
public class App extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      HttpSession session = request.getSession(true);
      Cookie[] cookies = request.getCookies();
      for (int i = 0; i < cookies.length; i++) {
          Cookie c = cookies[i];
          String name = c.getName();
          String value = c.getValue();
          out.println(name + " = " + value);
      }

      // set a cookie

      String name = request.getParameter("cookieName");
      if (name != null && name.length() > 0) {
          String value = request.getParameter("cookieValue");
          Cookie c = new Cookie(name, value);
          response.addCookie(c);
      }

      out.println("<html>");
      out.println("<head>");
      out.println("<title>Hello World!</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Hello World!</h1>");
      out.println("</body>");
      out.println("</html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  throws IOException, ServletException
  {
      doGet(request, response);
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String [] args) 
  {
   try
    {
      System.out.println("Hello World!");
      db_connector semantic_db = new db_connector(args);
      System.out.println("Finished Initializing DB");
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
