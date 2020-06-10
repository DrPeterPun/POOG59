package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class EmpresaT implements TransporteInterface, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7642785093124277585L;
	private String codigoEmpresa;
    private String nomeEmpresa;
    private int nif;
    private double raio;
    private double precokm;
    private double gpsx;
    private double gpsy;
    private Encomendas encT;
    private String email;
    private String pass;
    private ArrayList<Integer> ratings;

    public EmpresaT(){
        this.codigoEmpresa=" ";
        this.nomeEmpresa=" ";
        this.nif=0;
        this.raio=0;
        this.precokm=0;
        this.gpsx=0;
        this.gpsx=0;
        this.encT= new Encomendas();
        this.email="";
        this.pass="";
        this.ratings = new ArrayList<Integer>();
    }

    public EmpresaT(String codigoEmpresa, String nomeEmpresa, int nif, double raio,double precokm,double gpsx,double gpsy, String email, String pass, ArrayList<Integer> ratings){
        this.codigoEmpresa=codigoEmpresa;
        this.nomeEmpresa=nomeEmpresa;
        this.nif=nif;
        this.raio=raio;
        this.precokm=precokm;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
        this.email=email;
        this.pass=pass;
        this.ratings = new ArrayList<Integer>(ratings); 
    }
           
 	public Encomendas getEncT() {
		return encT;
	}

	public void setEncT(Encomendas encT) {
		this.encT = encT;
	}

	public String getCodigo(){
        return this.codigoEmpresa;
    }

    public String getNome(){
        return this.nomeEmpresa;
    }

    public int getNif(){
        return this.nif;
    }

    public double getRaio(){
        return this.raio;
    }

    public double getPrecokm(){
        return this.precokm;
    }

    public double getGpsx(){
        return this.gpsx;
    }

    public double getGpsy(){
        return this.gpsy;
    }

    public String getEmail()
    {
    	return this.email;
    }
    
    public String getPass()
    {
    	return this.pass;
    }
    
    public double getRating()
    {
    	double r = 0;
    	for(int rating:ratings)
    	{
    		r+=rating;
    	}
    	return r/ratings.size();
    }
    
    public boolean addRating(int n)
    {
    	if(n>=0 && n<=5)
    	{
    		this.ratings.add(n);
    		return true;
    	}
    	 return false;
    }
         
    public EmpresaT clone(){
        return new EmpresaT( 	this.codigoEmpresa,
        						this.nomeEmpresa,
        						this.nif,
        						this.raio,
        						this.precokm,
        						this.gpsx,
        						this.gpsy,
        						this.email,
        						this.pass,
        						this.ratings);
    }
    
    public void addEncT(Encomenda a) {
    	this.encT.addEncomenda(a.clone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpresaT)) return false;
        EmpresaT empresaT = (EmpresaT) o;
        return getNif() == empresaT.getNif() &&
                getRaio() == empresaT.getRaio() &&
                getPrecokm() == empresaT.getPrecokm() &&
                Objects.equals(getCodigo(), empresaT.getCodigo()) &&
                Objects.equals(getNome(), empresaT.getNome()) &&
                Objects.equals(getGpsx(), empresaT.getGpsx()) &&
                Objects.equals(getGpsy(), empresaT.getGpsy());
    }

    @Override
    public String toString() {
        return "EmpresaT{" +
                "codigoEmpresa='" + codigoEmpresa + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", nif=" + nif +
                ", raio=" + raio +
                ", precokm=" + precokm +
                ", gpsx='" + gpsx + '\'' +
                ", gpsy='" + gpsy + '\'' +
                '}';
    }


	public boolean logIn(String pass) {
		return this.pass==pass;
	}
	
	public double detPreco(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso)
    {
        double preco;
        preco = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
        preco=preco*this.precokm;
        preco=preco*this.precokm;
        if(peso>2)
            preco=preco*1.5;
        return preco;

    }
    
	
	public double detDist(double gpsxu, double gpsyu,double gpsxl, double gpsyl)
	{
		
		double dist = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
		return dist;
	}

	@Override
	public boolean inRaio(double gpsxu, double gpsyu, double gpsxl, double gpsyl) {
		return (Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2))<=this.raio )  && (Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2))<=this.raio);
	}
	
	/*public void addEnc(Encomenda a) {
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
	
	
}
