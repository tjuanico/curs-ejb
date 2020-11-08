package es.caib.CursEJB.service;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
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
	
	//Logger logger = Logger.getLogger(PersonaService.class);
	Logger logger;
	
	@PostConstruct
	void init() {
		logger = Logger.getLogger(PersonaService.class);
		logger.info("Dins @PostConstruct");
	}
	
	@PreDestroy()
	void destroy() {
		logger.info("Dins @PreDestroy");
	}
	
	
	@Override
	public String getNom(String dni) {
		
		// Amb SQL Normal
		Query query = em.createQuery("SELECT p FROM Persona p WHERE p.dni = '"+ dni +"'");  
		Persona p = (Persona)query.getSingleResult();
		p.setNom("112233S");
		//em.persist(p);
		logger.info("getNom persona --> Dormim");
		try {
			// Acció simulada que dura un minut. Recalcular nòmina/pensió/ajut
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		return p.getNom();
		
	}
	
	@Override
	public void addPersona(Persona p) {
		try
		{
			
			logger.info("Inserim persona > Inici");
			
			em.persist(p); // JPA insert into PERSONA (id, nom) values (1,'perico');
			logger.info("Persona inserida");
			
			logger.info("Cridam una classe normal de Java la que instanciarà un entityManager");
			Prova pr = new Prova();
			pr.func1(p);
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
		Prova p = new Prova();
		Persona pers = new Persona();
		pers.setDni("7654R");
		pers.setNom("xi xang");
		//p.func1(pers);
	}

	@Override
	public String getDni(String nom) {
		Query query = em.createNamedQuery("Persona.getByName");
		query.setParameter("nom",nom);
		List<Persona> llistaPersones = query.getResultList();
		String resultado = new String("");
		
		for (Persona p : llistaPersones) {
			logger.info("Trobada: " + p.getNom());
            resultado = p.getDni();
        }
		return resultado;
	}

	@Override
	public List<Persona> getTothom() {
		Query query = em.createNamedQuery("Persona.getAll");
		List<Persona> results = query.getResultList();
		return results;
	}
	
	
	@AroundInvoke
	public Object TimerLog(InvocationContext ctx) throws Exception {
		String beanClassName = ctx.getClass().getName(); 
		String businessMethodName = ctx.getMethod().getName();
		String target = beanClassName + "." + businessMethodName ; 
		long startTime = System.currentTimeMillis(); 
		logger.info("Invoking ------------> " + target); 
		try { 
			return ctx.proceed(); 
		} 
		finally { logger.info("Exiting" + target); 
		long totalTime = System.currentTimeMillis() - startTime; 
		logger.info("Mètode: " + businessMethodName + "in" + beanClassName + "takes" + totalTime + "ms to execute");
		} 
	}

}
