package es.caib.CursEJB.interfaces;

import javax.ejb.Remote;

@Remote
public interface CalculadoraRemoteInterface {
		public int suma(int a, int b);
		public int resta_especial(int a, int b);
}
