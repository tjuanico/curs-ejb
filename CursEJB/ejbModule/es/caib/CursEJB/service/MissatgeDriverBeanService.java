package es.caib.CursEJB.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

/* ------------------------------------------
 * RECORDA MODIFICAR STANDALONE.XML -> STANDALONE-FULL.XML
 * RECORDA JDK 1.8 i facelet 12 > 10 > 1.8
 * RECORDA Donar d'alta la coa al JMS Queue > Configuration > subsystems -> Messaging / Category -> Server -> default destinations
 * RECORDA standalone.bat -c standalone-full.xml
 * Toni Juanico: 01/11/2020 -> Exemple MDB
 */
@MessageDriven(mappedName = "java:/jms/queue/COLA", activationConfig =  {
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	    @ActivationConfigProperty(propertyName="destination", propertyValue="java:/jms/queue/COLA")
	    })
public class MissatgeDriverBeanService implements MessageListener {
	
	Logger logger = Logger.getLogger(MissatgeDriverBeanService.class);
	
	public void onMessage(Message message) {
		// Processament del missatge rebut
		logger.info("Roger that --> Missatge rebut, començam a treballar");
		try {
			logger.info("dormim 60seg.");
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // D
		finally {
			logger.info("Roger that --> Bona feina finalitzada");
		}
	}
}
