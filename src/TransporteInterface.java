package src;

public interface TransporteInterface {

	public TransporteInterface clone();
	/*acho que o melhor em x de ter uma classe encomendas e uma class voluntarios fazer a interface que 
	eles os 2 implementam e depois fazer uma classe que guarda uma collection deste interface (ou seja tanto os
	voluntarios como as empresas no mesmo mapa/set/lista...)
	*/

	public String getCodigo();
	public String getNome();
	public double detPreco(double gpsxu, double gpsyu,double gpsxl, double gpsyl);
	public double detDist(double gpsxu, double gpsyu,double gpsxl, double gpsyl);
	public boolean inRaio(double gpsxu, double gpsyu,double gpsxl, double gpsyl);
	public double getRating();
	public boolean addRating(int n);
    /*public void addEnc(Encomenda a);
    public void encAceite(Encomenda a);
	public void encRecusada(Encomenda a);
	public void encPronta(Encomenda a);
	public void encPorAvaliar(Encomenda a);
	public void encCompleta(Encomenda a);*/
	public boolean logIn(String pass);

	public String getEmail();


}