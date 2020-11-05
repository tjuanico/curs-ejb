package es.caib.CursEJB.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * mysql> use prueba2;
 *  CREATE TABLE PROVEIDOR (ID INT NOT NULL, NOM VARCHAR(255) NOT NULL, PRIMARY KEY(ID));
 *  
 *   CREATE TABLE FACTURA (ID INT NOT NULL, DATA DATE, IMPORT DECIMAL(9,2), PROVEIDOR_ID INT, FOREIGN KEY (PROVEIDOR_ID) REFERENCES PROVEIDOR (ID) ON DELETE CASCADE, PRIMARY KEY(ID));
 */

@Entity
@Table(name="proveidor")
public class Proveidor {
	@Id
	private Integer id;  // Ojo amb els tipus Java vs BBDD
	
	@Column
	private String nom;
	
	@OneToMany(mappedBy = "proveidor_id", fetch = FetchType.EAGER)
	private List<Factura> factures = new ArrayList<>();
	
	
	// Proveidor
	public Proveidor() { }
			
	// Mètodes get - set
	public Integer getId() { return this.id;	}
	public void setId(Integer id) {	this.id = id;	}
		
	public String getNom() { return this.nom; }
	public void setNom(String value) { this.nom = value; }
	
	public List<Factura> getFactures() { return this.factures; }
	public void setFactures(List<Factura> l) { this.factures = l; }
	
}
