package es.caib.CursEJB.interfaces;

import javax.ejb.Local;

@Local
public interface ReproductorInterfaceLocal {
	// Resol un cas d'ús. Reproductor de música
	public void reprodueix();
	public void atura();
	public void jump_song();
}
