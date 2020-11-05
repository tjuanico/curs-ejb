package es.caib.CursEJB.service;


import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
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

import es.caib.CursEJB.entity.Persona;
import es.caib.CursEJB.interfaces.PersonaInterfaceLocal;

@Stateless
@RolesAllowed("ROL_1")
public class PersonaService implements PersonaInterfaceLocal {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private QueueConnectionFactory queueConnectionFactory;
	@Resource(mappedName = "java:/jms/queue/COLA")
	private Queue queue;
	
	@PersistenceContext(unitName="cursweb-ds")
	EntityManager em;
	
	@Override
	public String getNom(String dni) {
		
		Query query = em.createQuery("SELECT p FROM Persona p WHERE p.dni = '"+ dni +"'"); // INNER JOIN LEFT JOIN RIGHT JOIN 
		Persona p = (Persona)query.getSingleResult();
		
		return p.getNom();
		
		
	}

	
	@Override
	public void addPersona(Persona p) {
		try
		{
			// String mySQL = new String("insert into PERSONA (id, nom) values (" + p.dni + ")"
			em.persist(p); // JPA insert into PERSONA (id, nom) values (1,'perico');
			System.out.println("Inserim persona");
			
			// Acció que dura un minut COLL D'AMPOLLA DETECTAT!!!!!
			//Thread.sleep(60000); // Acció simulada que dura un minut. Recalcular nòmina/pensió/ajut
			
			// Canviem l'acció per un missatge a la cua.
			try {
			    String message = "Hola món curs Arquitectura EJB 3.0: " + p.getDni();

			    Connection connection = queueConnectionFactory.createConnection();
			    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			    MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
			    TextMessage textMessage = session.createTextMessage();
			    textMessage.setText(message);
			    messageProducer.send(textMessage);
			    System.out.println("Missatge enviat correctament");
		   } 
		   catch (JMSException ex) {
			      System.out.println("Error enviant missatge: " + ex.toString());
		   }
			
		}
		catch(Exception ex) {
			System.out.println("Error inserint persona" + ex.toString());
		}	
	}


	@Override
	public void revisaSignatura() {
		// TODO Auto-generated method stub
		Prova p = new Prova();
		p.func1();
	}
	
	
}
