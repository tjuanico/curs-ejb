package es.caib.CursEJB.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import es.caib.CursEJB.entity.Factura;
import es.caib.CursEJB.entity.Proveidor;
import es.caib.CursEJB.interfaces.PersonaInterfaceLocal;
import es.caib.CursEJB.interfaces.ProveidorInterfaceLocal;

@Stateless
public class ProveidorBeanService implements ProveidorInterfaceLocal{

	Logger logger = Logger.getLogger(ProveidorBeanService.class);
	
	@PersistenceContext(unitName="cursweb-ds")
	EntityManager em;
	
	@Inject
	PersonaInterfaceLocal myPersona;
	
	@Override
	public Proveidor getProveidorById(Integer id) {
		Proveidor p = new Proveidor();
		p = em.find(Proveidor.class,id);
		
		for (Factura f : p.getFactures()) {
            System.out.println(f.getImport());
        }
			
		logger.info("Obtinguts proveidors");
		String result = myPersona.getNom("222222");
		logger.info("Obtingudes persones " + result);
		
		return p;
	}
	

}
