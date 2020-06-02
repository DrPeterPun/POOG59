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
    
    /*
    public RegEncomendas getRegEnc()
    {
    	return this.regEnc.clone();
    }

    public void addEnc(Encomenda a) {
		this.regEnc.addEnc(a);
	}
	
	// no caso de o transportes ser feito por uma empresa vem logo para aqui e nao para as abertas, se for por um voluntario ele precisa de aceitar
	public void encAceite(Encomenda a) {
		this.regEnc.encAceite(a);
	}
	
	// o voluntario recusou a encomenda
	public void encRecusada(Encomenda a) {
		this.regEnc.encRecusada(a);
	}
	
	// a Loja diz que a encomenda esta cpronta
	public void encPronta(Encomenda a){
		this.regEnc.encPronta(a);
	}
	
	// a encomenda ja foi entrgue mas ainda nao foi avaliada pelo user
	public void encPorAvaliar(Encomenda a){
		this.regEnc.encPorAvaliar(a);
	}
	
	// encomenda foi completada
	public void encCompleta(Encomenda a){
		this.regEnc.encCompleta(a);
	}
*/
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loja)) return false;
        Loja loja = (Loja) o;
        return Objects.equals(getCodigoL(), loja.getCodigoL()) &&
                Objects.equals(getNomeL(), loja.getNomeL());
    }
    
    public double detDistL(double gpsxu, double gpsyu,double gpsxl, double gpsyl)
	{
		
		double dist = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
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
