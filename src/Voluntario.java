package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntario implements TransporteInterface {
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

    public Voluntario(String codVol, String nome, double gpsx,double gpsy, double raio,String email, String pass,ArrayList<Integer> ratings){
        this.codVol= codVol;
        this.nome=nome;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
        this.raio=raio;
        this.email=email;
        this.pass=pass;
        this.email=email;
        this.pass=pass;
        this.ratings=ratings;
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

    //sets para o gps???
    
    public Voluntario(Voluntario t){
        this.codVol=t.getCodVol();
        this.nome=t.getNome();
        this.gpsx=t.getGpsx();
        this.gpsy=t.getGpsy();
        this.raio=t.getRaio();
    }

    public Voluntario clone(){
        return new Voluntario(this);
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

	public double detDist(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso)
	{

		double dist = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
		return dist;
		
	}
}
