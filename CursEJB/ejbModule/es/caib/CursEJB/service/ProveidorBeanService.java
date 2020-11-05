package es.caib.CursEJB.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.caib.CursEJB.entity.Factura;
import es.caib.CursEJB.entity.Proveidor;
import es.caib.CursEJB.interfaces.ProveidorInterfaceLocal;

@Stateless
public class ProveidorBeanService implements ProveidorInterfaceLocal{

	@PersistenceContext(unitName="cursweb-ds")
	EntityManager em;
	
	@Override
	public Proveidor getProveidorById(Integer id) {
		Proveidor p = new Proveidor();
		p = em.find(Proveidor.class,id);
		
		for (Factura f : p.getFactures()) {
            System.out.println(f.getImport());
        }
						
		return p;
	}
	

}
