package es.caib.CursEJB.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Toni Juanico 31/10/2020
 * mysql> use prueba2;
 *  CREATE TABLE PROVEIDOR (ID INT NOT NULL, NOM VARCHAR(255) NOT NULL, PRIMARY KEY(ID));
 *  
 *   CREATE TABLE FACTURA (ID INT NOT NULL, DATA DATE, IMPORTE DECIMAL(9,2), PROVEIDOR_ID INT, FOREIGN KEY (PROVEIDOR_ID) REFERENCES PROVEIDOR (ID) ON DELETE CASCADE, PRIMARY KEY(ID));
 *   MySQL Type      Java Type
----------      ---------
CHAR            String
VARCHAR         String
LONGVARCHAR     String
NUMERIC         java.math.BigDecimal 
DECIMAL         java.math.BigDecimal
BIT             boolean
TINYINT         byte
SMALLINT        short
INTEGER         int
BIGINT          long
REAL            float
FLOAT           double
DOUBLE          double
BINARY          byte[]
VARBINARY       byte[]
LONGVARBINARY   byte[]
DATE            java.sql.Date 
TIME            java.sql.Time
TIMESTAMP       java.sql.Tiimestamp
 */
@Entity
@Table(name="factura")
public class Factura {
	@Id
	private Integer id;  // Ojo amb els tipus Java vs BBDD
	
	@Column
	private Date data; // Ojo amb els tipus Java vs BBDD imports javax.sql.Date;
	
	@Column
	BigDecimal importe;
	
	@Column
	private Integer proveidor_id;
	
	// Proveidor
	public Factura() { }
			
	// Mètodes get - set
	public Integer getId() { return this.id;	}
	public void setId(Integer id) {	this.id = id;	}
		
	public Date getData() { return this.data; }
	public void setData(Date d) { this.data = d; }
	
	public BigDecimal getImport() { return this.importe; }
	public void setImport(BigDecimal i) { this.importe = i; }
	
	public Integer getProveidor_id() { return this.proveidor_id; }
	public void setProveidor_id(Integer proveidor_id) { this.proveidor_id = proveidor_id; }
	
}
