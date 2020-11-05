package es.caib.CursEJB.service;


import javax.ejb.Stateless;

import es.caib.CursEJB.entity.Persona;
import es.caib.CursEJB.interfaces.*;

 @Stateless
 public class CalculadoraBeanService implements CalculadoraInterface, CalculadoraRemoteInterface  {

	@Override
	public int suma(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int resta(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	PersonaService p = new PersonaService();
	
	@Override
	public int resta_especial(int a, int b) {
		// TODO Auto-generated method stub
	
		
		Persona pers = new Persona();
		pers.setDni("2222");
		p.addPersona(pers);
		
		
		
		return 0;
		
	}

}
