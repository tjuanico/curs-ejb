package es.caib.CursEJB.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import es.caib.CursEJB.entity.Persona;

/* Autor: Toni Juanico
 * octubre: 2020
 * Classe Prova té la finalitat de provar la persistència sense injecció d'EJB
 * RECORDA!!!:
 * 
 * Do I have to close() every EntityManager?
 * 
 * It depends how you obtained it.
 * If you created it using EntityManagerFactory you will have to close it no matter what framework you use.
 * If you obtained it using dependency injection (eg using EJB and @PersistenceContext annotation) you should not close it by hand (AFAIK it will cause RuntimeException).
 */
public class Prova {

	Logger logger = Logger.getLogger(Prova.class);
	final EntityManagerFactory emf;
	final EntityManager em;
	
	public Prova() {
		/* Exemple sense utilitzar injecció de dependències */
		emf = Persistence.createEntityManagerFactory("cursweb-ds"); 
		em = emf.createEntityManager();
		
		/* inclús hi ha una tercera manera EntityManager em = (EntityManager)ctx.lookup("Chapter03PersistenceUnit"); */
	}
	
	public void func1(Persona p)
	{
		logger.info("Insertam persona a partir d'instanciació");
		em.persist(p);
		logger.info("Prova > func1() --> ok");
	}
}
