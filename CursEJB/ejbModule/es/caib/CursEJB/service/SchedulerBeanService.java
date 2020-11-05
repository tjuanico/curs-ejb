package es.caib.CursEJB.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Timer;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

import es.caib.CursEJB.entity.Persona;
import es.caib.CursEJB.interfaces.SchedulerInterfaceLocal;

@Stateless
public class SchedulerBeanService implements SchedulerInterfaceLocal{
	
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
		
		System.out.println("Començam temporitzador: ");
		timerService.createTimer(initialExpiration.getTime(),intervalDuration,null);
		
	}

	@Override
	public void shutDownTimer() {
		Collection<Timer> timers = timerService.getTimers();
		System.out.println("Tancam temporitzador");
		if (timers != null)
		{
			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
				Timer t = (Timer) iterator.next();
				t.cancel();
				System.out.println("shutdowntimer canceled.");
			}
		}
	}
	
	@Timeout
	public void execute(Timer timer)
	{
		Prova p2 = new Prova();
		p2.func1();
		System.out.println("Cridam");
		PersonaService mypersona = new PersonaService();
		Persona p = new Persona();
		p.setDni("222213J");
		p.setNom("Perico");
		mypersona.addPersona(p);
		System.out.println("Fet");
	}
	
	
}
