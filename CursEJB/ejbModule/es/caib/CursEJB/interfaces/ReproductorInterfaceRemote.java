package es.caib.CursEJB.interfaces;

import javax.ejb.Remote;

@Remote
public interface ReproductorInterfaceRemote {
	    // Resol un cas d'�s. Reproductor de m�sica
		public void reprodueix();
		public void atura();
}
