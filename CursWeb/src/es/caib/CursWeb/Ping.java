package es.caib.CursWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ping extends HttpServlet {
	// Tornarem [OK] -> si tot funciona
	// Tornarem [KO] -> si hi ha algun problema.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	 
	    boolean provaBD = true; // Fem connexió a BBDD
	    boolean provaSistemaExtern = true; // Fem connexió a sistema extern
	    
	    String resultat = new String("");
	    
	    if (provaBD && provaSistemaExtern)
	    	resultat = "[OK]";
	    else
	    	resultat = "[KO][BBDD KO][SISTEMA_EXTERN OK]";
	    try {
	    	out.println("<!DOCTYPE html>");
	        out.println("<html><head>");
	        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	        out.println("<title>Ping.</title></head>");
	        out.println("<body>");
	        out.println("<h1>" + resultat + "</h1>");  // says Hello
	        // Echo client's request information
	    } finally {
	         out.close();  // Always close the output writer
	    }
	}
}
