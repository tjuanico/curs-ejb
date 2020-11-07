package es.caib.CursEJB.service;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import es.caib.CursEJB.entity.Persona;
import es.caib.CursEJB.interfaces.PersonaInterfaceLocal;

@Stateless
public class PersonaService implements PersonaInterfaceLocal {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private QueueConnectionFactory queueConnectionFactory;
	@Resource(mappedName = "java:/jms/queue/COLA")
	private Queue queue;
	
	@PersistenceContext(unitName="cursweb-ds")
	EntityManager em;
	
	Logger logger = Logger.getLogger(PersonaService.class);
	
	@Override
	public String getNom(String dni) {
		
		// Amb SQL Normal
		Query query = em.createQuery("SELECT p FROM Persona p WHERE p.dni = '"+ dni +"'");  
		Persona p = (Persona)query.getSingleResult();
		p.setNom("112233S");
		//em.persist(p);
		logger.info("getNom persona --> Dormim");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Acció simulada que dura un minut. Recalcular nòmina/pensió/ajut
		return p.getNom();
		
	}
	
	@Override
	public void addPersona(Persona p) {
		try
		{
			
			logger.info("Inserim persona > Inici");
			
			em.persist(p); // JPA insert into PERSONA (id, nom) values (1,'perico');
			logger.info("Persona inserida");
					
			// Acció que dura un minut COLL D'AMPOLLA DETECTAT!!!!!
			//Thread.sleep(15000); // Acció simulada que dura un minut. Recalcular nòmina/pensió/ajut
			
			// Canviem l'acció per un missatge a la cua.
			try {
			    String message = "Hola món curs Arquitectura EJB 3.0: " + p.getDni();

			    Connection connection = queueConnectionFactory.createConnection();
			    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			    MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
			    TextMessage textMessage = session.createTextMessage();
			    textMessage.setText(message);
			    messageProducer.send(textMessage);
			    logger.info("Missatge enviat a la coa correctament");
		   } 
		   catch (JMSException ex) {
			   logger.info("Error enviant missatge: " + ex.toString());
		   }
			
		}
		catch(Exception ex) {
			logger.info("Error inserint persona" + ex.toString());
		}	
	}


	@Override
	public void revisaSignatura() {
		// TODO Auto-generated method stub
		Prova p = new Prova();
		p.func1();
	}
	
	
}
