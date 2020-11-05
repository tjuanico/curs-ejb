package es.caib.CursEJB.interfaces;

import javax.ejb.Local;

/**
 * @author Toni Juanico
 *
 */
@Local
public interface CalculadoraInterface {
	public int suma(int a, int b);
	public int resta(int a, int b);
}
