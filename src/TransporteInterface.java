package src;

public interface TransporteInterface {

	public TransporteInterface clone();
	/*acho que o melhor em x de ter uma classe encomendas e uma class voluntarios fazer a interface que 
	eles os 2 implementam e depois fazer uma classe que guarda uma collection deste interface (ou seja tanto os
	voluntarios como as empresas no mesmo mapa/set/lista...)
	*/

	public String getCodigo();
	public double detPreco(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso);

}