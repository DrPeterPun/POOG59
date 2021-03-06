package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Loja implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1607658183283744900L;
	private String codigoL;
    private String nomeL;
    private String email;
    private String pass;
    private double gpsx;
    private double gpsy;

    public Loja(){
        this.codigoL=" ";
        this.nomeL=" ";
        this.email="";
        this.pass="";
        this.gpsx=0;
        this.gpsy=0;
    }

    public Loja(String codigoL, String nomeL, String email , String pass ,double gpsx ,double gpsy){
        this.codigoL=codigoL;
        this.nomeL=nomeL;
        this.email=email;
        this.pass=pass;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
    }

	public String getCodigoL() {
        return codigoL;
    }

    public String getNomeL() {
        return nomeL;
    }
    
    public double getGpsx()
    {
    	return this.gpsx;
    }
    
    public double getGpsy()
    {
    	return this.gpsy;
    }

    
    public String getEmail()
    {
    	return this.email;
    }


    public Loja clone(){
        return new Loja(	this.codigoL,
        					this.nomeL,
        					this.email,
        					this.pass,
        					this.gpsx,
        					this.gpsy);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loja)) return false;
        Loja loja = (Loja) o;
        return Objects.equals(getCodigoL(), loja.getCodigoL()) &&
                Objects.equals(getNomeL(), loja.getNomeL());
    }
    
    /**Método que calcula a distância de uma loja a um utilizador
     * @param gpsxu Coordenada x de um utilizador
     * @param gpsyu Coordenada y de um utilizador*/
    public double detDistL(double gpsxu, double gpsyu)
	{
		
		double dist = Math.sqrt(Math.pow((this.gpsx - gpsxu), 2) + Math.pow((this.gpsy - gpsyu), 2));
		return dist;
	}
    
    @Override
    public String toString() {
        return "Loja{" +
                "codigoL='" + codigoL + '\'' +
                ", nomeL='" + nomeL + '\'' +
                '}';
    }

	public boolean logIn(String pass) {
		return this.pass==pass;
	}
}
