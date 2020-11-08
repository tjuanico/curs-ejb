package es.caib.CursEJB.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="persona")
@NamedQueries({ @NamedQuery(name = "Persona.getAll", query = "select p from Persona p"),
	            @NamedQuery(name = "Persona.getByName",query="SELECT p FROM Persona p WHERE p.nom = :nom")
	})
public class Persona {
	
	@Id
	private String dni; // VARCHAR2 [SQL Standard] --> String Java
	
	@Column
	private String nom;
	
	// Persona
	public Persona() { }
		
	// Mètodes get - set
	public String getDni() { return this.dni;	}
	public void setDni(String dni) {	this.dni = dni;	}
	
	public String getNom() { return this.nom; }
	public void setNom(String value) { this.nom = value; }
	
}