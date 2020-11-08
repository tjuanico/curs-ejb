package es.caib.CursEJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import es.caib.CursEJB.entity.Persona;

@Local
public interface PersonaInterfaceLocal {
	public String getNom(String dni); // Obtenim persona per dni i tornam el nom
	public String getDni(String nom); // Obtenim persona pel nom i tornam el dni
	public void addPersona(Persona p); // Inserim persona a la base de dades
	public List<Persona> getTothom(); // Obté la llista de totes les persones
	public void revisaSignatura(); 
	
	
}

