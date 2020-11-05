package es.caib.CursEJB.interfaces;

import javax.ejb.Local;

@Local
public interface SchedulerInterfaceLocal {
    public void startUpTimer();  // Inici
    public void shutDownTimer();  // Fi
}
