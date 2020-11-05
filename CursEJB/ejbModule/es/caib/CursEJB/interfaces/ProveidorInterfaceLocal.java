package es.caib.CursEJB.interfaces;

import javax.ejb.Local;

import es.caib.CursEJB.entity.Proveidor;

@Local
public interface ProveidorInterfaceLocal {
	public Proveidor getProveidorById(Integer id);
}
