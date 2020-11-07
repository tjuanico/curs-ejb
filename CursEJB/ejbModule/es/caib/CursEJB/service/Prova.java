package es.caib.CursEJB.service;

import org.apache.log4j.Logger;

public class Prova {

	Logger logger = Logger.getLogger(Prova.class);
	
	public Prova() { }
	
	public void func1()
	{
		logger.info("Prova > func1() --> ok");
	}
}
