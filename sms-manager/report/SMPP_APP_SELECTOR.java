import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SMPP_APP_SELECTOR extends HttpServlet {

	private String hostname = "localhost";
	private String ports = "3306";
	private String Database = "troy";
	private String name = "troyroot";
	private String password = "troy20root07";

	private String fromdate = null;
	private String todate = null;
	private String shortcode = null;
	private String msisdn = null;
	
	Connection conn = null; 
	Statement st = null; 
	ResultSet rs = null; 


    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        ResourceBundle rb =
            ResourceBundle.getBundle("LocalStrings",request.getLocale());

if (request.getParameter("shortcode")==null || request.getParameter("start_year")==null || request.getParameter("start_month")==null || request.getParameter("start_day")==null || request.getParameter("start_time")==null || request.getParameter("end_year")==null || request.getParameter("end_month")==null || request.getParameter("end_day")==null || request.getParameter("end_time")==null) {

	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
	 out.println("<html><body>bad query</body></html>");
}
else {
	response.setContentType("application/vnd.ms-excel");
	//response.setContentType("application/x-csv");        
	response.setHeader("Content-Disposition", "attachment; filename=Excel.csv");
	 response.addHeader("Content-description", "Forms Report");

        PrintWriter out = response.getWriter();


	try { 
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	String url = "jdbc:mysql://" + hostname + ":" + ports + "/" + Database;
	conn = DriverManager.getConnection(url, name, password); 
	} 
catch (IllegalAccessException e) { 
e.printStackTrace(); 
	} 
catch (ClassNotFoundException e) { 
e.printStackTrace(); 
	} 
catch (InstantiationException e) { 
e.printStackTrace(); 
	} 
catch (SQLException e) { 
e.printStackTrace(); 
	} 



  try {

      st = conn.createStatement();

	shortcode = request.getParameter("shortcode");
	
	fromdate = request.getParameter("start_year")+"-"+request.getParameter("start_month")+"-"+request.getParameter("start_day")+" "+request.getParameter("start_time");
	todate = request.getParameter("end_year")+"-"+request.getParameter("end_month")+"-"+request.getParameter("end_day")+" "+request.getParameter("end_time");

      if (request.getParameter("msisdn")+"*"=="*") {
	msisdn = request.getParameter("msisdn");
      rs = st.executeQuery("SELECT sms_id,srcAddr,destAddr,datetime delivered,REPLACE(REPLACE(message,CHAR(10),'^'),CHAR(13),'^') message,length FROM MT_SMS_MSGS_9440 WHERE sourceAddr='"+ msisdn +"' and destAddr='"+shortcode+"' and datetime between '"+fromdate+"' and '"+ todate +"' order by sms_id");
      }
      else {
      rs = st.executeQuery("SELECT sms_id,srcAddr,destAddr,datetime Delivered,REPLACE(REPLACE(message,CHAR(10),'^'),CHAR(13),'^') message,length FROM MT_SMS_MSGS_9440 WHERE destAddr='"+shortcode+"' and datetime between '"+fromdate+"' and '"+ todate +"' order by sms_id");
      }
//        out.println("sms_id,sourceAddr,destAddr,delivered,message,length");  
        out.println("delivered,msisdn,shortcode,message");  

    	while(rs.next()){
//        String sms_id= rs.getString("sms_id");
        String destAddr= rs.getString("destAddr");
        String delivered= rs.getString("delivered");
        String srcAddr= rs.getString("srcAddr");
        String message= rs.getString("message");
//        String length= rs.getString("length");

//        out.println(sms_id+","+srcAddr+","+destAddr+","+delivered+","+message+","+length);  
        out.println(delivered+","+srcAddr+","+destAddr+","+message);  
  }
}
	catch( Exception e ) {
      e.printStackTrace();
    }
}


    }
}



