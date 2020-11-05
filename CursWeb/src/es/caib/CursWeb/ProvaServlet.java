package es.caib.CursWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	// EJB 3.0
	@EJB 
	private ReproductorInterfaceLocal myReproductor;
	
	@EJB 
	private PersonaInterfaceLocal myPersona;
	
	@EJB
	private ProveidorInterfaceLocal myProveidorService;
		
	@PostConstruct
	public void init() {
		System.out.print("Entrada a init: " + myCalculator);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.print("Entrada a doGet: " + myCalculator);
		int resultat = myCalculator.suma(3, 5); // Cridem EJB
		int resultat2 = myGenerador.seguent();
		String resultat3 = new String("");	
		Persona p = new Persona();
		p.setDni("222213J");
		p.setNom("Perico");
		myPersona.addPersona(p);
		
		Proveidor prov = myProveidorService.getProveidorById(1);
		
		resultat3 = myPersona.getNom("2222222J");
		
		myReproductor.atura();
		myReproductor.jump_song();
		myReproductor.reprodueix();
		
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
	        out.println("</body>");
	        out.println("</html>");
	    } finally {
	         out.close();  // Always close the output writer
	    }
	}
}
