package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntario implements TransporteInterface, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4931069963104972692L;
	private String codVol;
    private String nome;
    private double gpsx;
    private double gpsy;
    private double raio;
    private Encomendas encV;
    private String email;
    private String pass;
    private ArrayList<Integer> ratings;

    public Voluntario (){
        this.codVol=" ";
        this.nome=" ";
        this.gpsx=0;
        this.gpsy=0;
        this.raio=0;
        this.encV= new Encomendas();
        this.email="";
        this.pass="";
        this.ratings = new ArrayList<Integer>();

    }

    
    
    //é de eesperar que este seja o metudo utilizado para registar um novo voluntario pq tem toda a informacao inicial mas nao tem ratings e nao tem encomendas ja feitas
    public Voluntario(String codVol, String nome, double gpsx,double gpsy, double raio,String email, String pass){
        this.codVol= codVol;
        this.nome=nome;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
        this.raio=raio;
        this.email=email;
        this.pass=pass;
        this.email=email;
        this.pass=pass;
        this.ratings=new ArrayList<Integer>();
    }
    
    public Voluntario(String codVol, String nome, double gpsx,double gpsy, double raio,String email, String pass, ArrayList<Integer> ratings){
        this.codVol= codVol;
        this.nome=nome;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
        this.raio=raio;
        this.email=email;
        this.pass=pass;
        this.email=email;
        this.pass=pass;
        this.ratings= new ArrayList<Integer>(ratings);
    }
    
    
    public Encomendas getEncV() {
		return encV;
	}

	public void setEncV(Encomendas encV) {
		this.encV = encV;
	}

	public String getCodVol(){
        return this.codVol;
    }

    public String getNome(){
        return this.nome;
    }

    public double getGpsx(){
        return this.gpsx;
    }

    public double getGpsy(){
        return this.gpsy;
    }

    public double getRaio(){
        return this.raio;
    }
    
    public String getEmail()
    {
    	return this.email;
    }
    
    public String getPass()
    {
    	return this.pass;
    }

    public Voluntario clone(){
        return new Voluntario(	this.codVol,
        						this.nome,
        						this.gpsx,
        						this.gpsy,
        						this.raio,
        						this.email,
        						this.pass);
    }
    
    public void addEncV(Encomenda a) {
    	this.encV.addEncomenda(a.clone());
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
    
    /*public List<Encomenda> registosV(RegEncomendas a){
    	List<Encomenda> enc = new ArrayList<>();
    	a.getEncTerminadas().stream().filter(x->x.);
    
    }*/
    
    @Override
    public String toString() {
        return "Voluntario{" +
                "codVol='" + codVol + '\'' +
                ", nome='" + nome + '\'' +
                ", gpsx=" + gpsx +
                ", gpsy=" + gpsy +
                ", raio=" + raio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return Double.compare(that.getGpsx(), getGpsx()) == 0 &&
                Double.compare(that.getGpsy(), getGpsy()) == 0 &&
                Double.compare(that.getRaio(), getRaio()) == 0 &&
                Objects.equals(getCodVol(), that.getCodVol()) &&
                Objects.equals(getNome(), that.getNome());
    }

	public boolean logIn(String pass) {
		return this.pass==pass;
	}
	
	public double detPreco(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso) {
		return 0;
	}

	public String getCodigo() {
		return this.codVol;
	}

	public boolean inRaio(double gpsxu, double gpsyu, double gpsxl, double gpsyl) {
		return (Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2))<=this.raio )  && (Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2))<=this.raio);
	}



	@Override
	public double detPreco(double gpsxu, double gpsyu, double gpsxl, double gpsyl) {
		return 0;
	}



	@Override
	public double detDist(double gpsxu, double gpsyu, double gpsxl, double gpsyl) {
		double dist = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
		return dist;
	}
	
	// ---relativo as encomendas---
	/*
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
	}*/
	

	
}
