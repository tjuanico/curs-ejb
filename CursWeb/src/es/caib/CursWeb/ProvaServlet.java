package es.caib.CursWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import es.caib.CursEJB.entity.Persona;
import es.caib.CursEJB.entity.Proveidor;
import es.caib.CursEJB.interfaces.*;

public class ProvaServlet extends HttpServlet {
	/**
	 Author: Toni Juanico* 
	 Comments: EJB Injection*/
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CalculadoraInterface myCalculator;
	
	@EJB
	private GeneradorInterface myGenerador;
	

	@EJB 
	private ReproductorInterfaceLocal myReproductor; 	// EJB 3.0
	
	@EJB
	private PersonaInterfaceLocal myPersona;
	
	@EJB
	private ProveidorInterfaceLocal myProveidorService;
	
	Logger logger = Logger.getLogger(ProvaServlet.class);
		
	@PostConstruct
	public void init() {
		logger.info("Entrada a init: " + myCalculator);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		logger.info("Entrada a doGet: " + myCalculator);
		int resultat = myCalculator.suma(3, 5); // Cridem EJB
		int resultat2 = myGenerador.seguent();
		
		String resultat3 = new String("");	
		Persona p = new Persona();
		p.setDni("222213J");
		p.setNom("Perico");
		myPersona.addPersona(p);
		
		Proveidor prov = myProveidorService.getProveidorById(1);
		
		resultat3 = myPersona.getNom("222222");
		
		myReproductor.atura();
		myReproductor.jump_song();
		myReproductor.reprodueix();
		
		List<Persona> llistaPersones = myPersona.getTothom();
		String my_dni = myPersona.getDni("Maria");
		
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	 
	    try {
	    	out.println("<!DOCTYPE html>");
	        out.println("<html><head>");
	        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	        out.println("<title>Hello, World</title></head>");
	        out.println("<body>");
	        out.println("<h1>Hello, world!</h1>");  // says Hello
	        // Echo client's request information
	        out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
	        out.println("<p>Protocol: " + request.getProtocol() + "</p>");
	        out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
	        out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
	        // Generate a random number upon each request
	        out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
	        out.println("<p>Resultat crida EJB stateless: suma(3,5)" + resultat + "</p>");
	        out.println("<p>Resultat crida EJB statefull: " + resultat2 + "</p>");
	        out.println("<p>Nom de la persona amb dni 2222222J: " + resultat3 + "</p>");
	        out.println("<p>Número de factures del proveidor: " + prov.getFactures().size() + "</p>");
	        out.println("<p>DNI de na Maria: " + my_dni + "</p>");
	        out.println("<table cellspacing='1' cellpadding='2' border='0'>");
	        out.println("<tr><td><b>DNI</b></td><td><b>Nom</b></td></tr>");
	        for (Persona p4 : llistaPersones) {
	            out.println("<tr><td>" + p4.getDni() + "</td><td>" + p4.getNom()+ "</td></tr>");
	        }
	        
	        out.println("</table>");
	        out.println("</body>");
	        out.println("</html>");
	    } finally {
	         out.close();  // Always close the output writer
	    }
	}
}
