package es.caib.CursEJB.interfaces;

import javax.ejb.Remote;

@Remote
public interface ReproductorInterfaceRemote {
	    // Resol un cas d'ús. Reproductor de música
		public void reprodueix();
		public void atura();
}
