package es.caib.CursWeb;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.caib.CursEJB.interfaces.SchedulerInterfaceLocal;

public class InitScheduler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// EJB 3.0
	@EJB 
	private SchedulerInterfaceLocal myScheduler;

	public void init() throws ServletException{
		myScheduler.startUpTimer();
	}
		
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException  
	{  
	       // do nothing, only for initialization purposes  
	}  
}
