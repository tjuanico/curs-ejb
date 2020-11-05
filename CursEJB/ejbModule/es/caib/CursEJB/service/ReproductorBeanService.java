package es.caib.CursEJB.service;

import javax.ejb.Stateless;

import es.caib.CursEJB.interfaces.ReproductorInterfaceLocal;
import es.caib.CursEJB.interfaces.ReproductorInterfaceRemote;

// El meu primer EJB 3.0
@Stateless
public class ReproductorBeanService implements ReproductorInterfaceLocal, ReproductorInterfaceRemote {

	@Override
	public void reprodueix() {
		// TODO Auto-generated method stub
		// Codi 		
	}

	@Override
	public void atura() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump_song() {
		// TODO Auto-generated method stub
		
	}

}
