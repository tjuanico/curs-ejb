package es.caib.CursEJB.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import es.caib.CursEJB.entity.Persona;

/* Autor: Toni Juanico
 * octubre: 2020
 * Classe Prova t� la finalitat de provar la persist�ncia sense injecci� d'EJB
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
		/* Exemple sense utilitzar injecci� de depend�ncies */
		emf = Persistence.createEntityManagerFactory("cursweb-ds"); 
		em = emf.createEntityManager();
		
		/* incl�s hi ha una tercera manera EntityManager em = (EntityManager)ctx.lookup("Chapter03PersistenceUnit"); */
	}
	
	public void func1(Persona p)
	{
		logger.info("Insertam persona a partir d'instanciaci�");
		em.persist(p);
		logger.info("Prova > func1() --> ok");
	}
}
