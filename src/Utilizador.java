package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utilizador {
    private String iduser;
    private String name;
    private double gpsxuser;
    private double gpsyuser;
    private Encomendas encUser;
    private String email;
    private String pass;

    public Utilizador(){
        this.iduser=" ";
        this.name=" ";
        this.gpsxuser=0;
        this.gpsyuser=0;
        this.encUser= new Encomendas();
        this.email="";
        this.pass="";
    }

    public Utilizador(String iduser,String name,double gpsxuser, double gpsyuser,String email, String pass){
        this.iduser=iduser;
        this.name=name;
        this.gpsxuser=gpsxuser;
        this.gpsyuser=gpsyuser;
        this.email=email;
        this.pass=pass;
    }

    public String getIduser() {
        return this.iduser;
    }

    public String getName(){
        return this.name;
    }

    public double getGpsx() {
        return this.gpsxuser;
    }

    public double getGpsy() {
        return this.gpsyuser;
    }

    //sets
    // metudo private por seguranca (mudar este tripo de informacao deve ser mais restrito imo)
    public void setIduser(String id) {
        this.iduser=id;
    }
    // metudo private por seguranca (mudar este tripo de informacao deve ser mais restrito imo)
    public void setName(String name){
        this.name=name;
    }
 
    public void setGpsxuser(double gpsx) {
        this.gpsxuser=gpsx;        
    }

    public void setGpsyuser(double gpsy) {
        this.gpsyuser=gpsy;
    }
    
    
   public Encomendas getEncUser() {
		return encUser;
	}

	public void setEncUser(Encomendas encUser) {
		this.encUser = encUser;
	}

	public String getEmail()
    {
    	return this.email;
    }
    
    public String getPass()
    {
    	return this.pass;
    }
	/* public Map<String,String> getEmailpass() {
        return this.emailpass.values().stream().collect(Collectors.toMap;
*/
    public Utilizador(Utilizador t){
        this.iduser=getIduser();
        this.name=getName();
        this.gpsxuser=getGpsx();
        this.gpsyuser=getGpsy();
    }


    public Utilizador clone(){
        return new Utilizador(this);
    }
    
    //Aqui tem que se acrescentar esta encomenda às encomendas pendentes, não sei se se faz aqui ou no model
    public void addEncU(Encomenda a) {
        this.encUser.addEncomenda(a.clone());
    }
    
    //Função que põe numa list os registos de encomendas de um utilizador(encomendas terminhas, recusadas e aceites);
    public List<Encomenda> registosU(RegEncomendas a){
    	List<Encomenda> enc = new ArrayList<>();
    	a.getEncTerminadas().stream().filter(x->x.getCodUt()==this.iduser).forEach(x->enc.add(x));
    	a.getEncRecusadas().stream().filter(x->x.getCodUt()==this.iduser).forEach(x->enc.add(x));
    	a.getEncAceites().stream().filter(x->x.getCodUt()==this.iduser).forEach(x->enc.add(x));
    	return enc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizador)) return false;
        Utilizador that = (Utilizador) o;
        return Double.compare(that.getGpsx(), getGpsx()) == 0 &&
                Double.compare(that.getGpsy(), getGpsy()) == 0 &&
                Objects.equals(getIduser(), that.getIduser()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "iduser='" + iduser + '\'' +
                ", name='" + name + '\'' +
                ", gpsxuser=" + gpsxuser +
                ", gpsyuser=" + gpsyuser +
                '}';
    }

	public boolean logIn(String pass) {
		return this.pass==pass;
	}
}
