package es.caib.CursEJB.service;

import javax.ejb.Stateful;

import es.caib.CursEJB.interfaces.GeneradorInterface;

@Stateful
public class GeneradorBeanService implements GeneradorInterface {

	public static int sequencia;
	
	public GeneradorBeanService() {
		sequencia = 0;
	}
	@Override
	public int seguent() {
		sequencia = sequencia + 1;
		return sequencia;
	}

}

