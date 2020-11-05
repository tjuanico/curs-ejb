package es.caib.CursEJB.interfaces;

import javax.ejb.Local;

import es.caib.CursEJB.entity.Persona;

@Local
public interface PersonaInterfaceLocal {
	public String getNom(String dni); // Obtenim persona per dni
	public void addPersona(Persona p); // Inserim persona a la base de dades
	public void revisaSignatura(); 
}

