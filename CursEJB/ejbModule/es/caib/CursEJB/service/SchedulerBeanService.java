package es.caib.CursEJB.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Timer;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;

import es.caib.CursEJB.entity.Persona;
import es.caib.CursEJB.interfaces.PersonaInterfaceLocal;
import es.caib.CursEJB.interfaces.SchedulerInterfaceLocal;

@Stateless
@RolesAllowed("ROL_1")
public class SchedulerBeanService implements SchedulerInterfaceLocal{
	
	Logger logger = Logger.getLogger(SchedulerBeanService.class);
	
	/** Injectam el servei de temps */
	@Resource TimerService timerService;
	
	/** Hora d'inici */
	private static final int START_HOUR = 16;
 
	/** Minuts  */
	private static final int START_MINUTES = 45;
	
	/** Segons: 00  */
	private static final int START_SECONDS = 0;
	
	/** Interval d'execució: 1440 = 24 hores */
	private static final int INTERVAL_IN_MINUTES = 1;
	
	@Override
	public void startUpTimer() {
		shutDownTimer();
		Calendar initialExpiration = Calendar.getInstance();
		initialExpiration.set(Calendar.HOUR_OF_DAY, START_HOUR );
		initialExpiration.set(Calendar.MINUTE, START_MINUTES);
		initialExpiration.set(Calendar.SECOND, START_SECONDS);
		
		long intervalDuration = new Integer(INTERVAL_IN_MINUTES).longValue()*60*1000;
		
		logger.info("Començam temporitzador: ");
		timerService.createTimer(initialExpiration.getTime(),intervalDuration,null);
		
	}

	@Override
	public void shutDownTimer() {
		Collection<Timer> timers = timerService.getTimers();
		logger.info("Tancam temporitzador");
		if (timers != null)
		{
			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
				Timer t = (Timer) iterator.next();
				t.cancel();
				logger.info("shutdowntimer canceled.");
			}
		}
	}
	
	@EJB
	PersonaInterfaceLocal myPersona;
	
	@Timeout
	public void execute(Timer timer)
	{
		Prova p2 = new Prova();
		p2.func1();
		logger.info("Cridam");
		Persona p = new Persona();
		p.setDni("222213J");
		p.setNom("Perico");
		myPersona.addPersona(p);
		logger.info("Fet");
	}
	
	
}
