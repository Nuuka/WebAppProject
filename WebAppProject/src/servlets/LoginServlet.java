package servlets;  
  
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import dao.LoginDao;   
  
public class LoginServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        String n=request.getParameter("username");    
        String p=request.getParameter("userpass");   
          
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        	session.setAttribute("name", n);  
  
        if(LoginDao.validate(n, p)){    
        	session.setAttribute("loginStatus", "success");
            RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");    
            rd.forward(request,response);    
        }    
        else{    
            //out.print("<p style=\"color:red\">Sorry username or password error</p>");  
        	// Redirect to login page if invalid user and pass
        	session.setAttribute("loginStatus", "failed");
        	
            RequestDispatcher rd=request.getRequestDispatcher("login.jsp");    
            rd.forward(request,response);    
        }    
  
        out.close();    
    }
    
}   