package src;

public interface TransporteInterface {

	public TransporteInterface clone();

	public String getCodigo();
	public String getNome();
	public double detPreco(double gpsxu, double gpsyu,double gpsxl, double gpsyl,double peso);
	public double detDist(double gpsxu, double gpsyu,double gpsxl, double gpsyl);
	public boolean inRaio(double gpsxu, double gpsyu,double gpsxl, double gpsyl);
	public double getRating();
	public boolean addRating(int n);
	public boolean logIn(String pass);

	public String getEmail();


}