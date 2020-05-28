package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Loja {
    private String codigoL;
    private String nomeL;
    private Encomendas encL;
    private String email;
    private String pass;
    private double gpsx;
    private double gpsy;

    public Loja(){
        this.codigoL=" ";
        this.nomeL=" ";
        this.encL= new Encomendas();
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
    
    public Encomendas getEncL() {
		return encL;
	}

	public void setEncL(Encomendas encL) {
		this.encL = encL;
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

    public Loja(Loja l){
        this.codigoL=getCodigoL();
        this.nomeL=getNomeL();
    }
    
    public String getEmail()
    {
    	return this.email;
    }
    
    public String getPass()
    {
    	return this.pass;
    }

    public Loja clone(){
        return new Loja(this);
    }
    
    //Com os registos de encomendas, não sei se esta função será precisa, visto que quem solicita a encomenda é o utilizador, acho que não será necessário ter uma lista
    //de encomendas para as lojas
    public void addEncL(Encomenda a) {
    	this.encL.addEncomenda(a.clone());
    }
    
    public List<Encomenda> registosL(RegEncomendas a){
    	List<Encomenda> enc = new ArrayList<>();
    	a.getEncTerminadas().stream().filter(x->x.getCodL()==this.codigoL).forEach(x->enc.add(x));
    	a.getEncRecusadas().stream().filter(x->x.getCodL()==this.codigoL).forEach(x->enc.add(x));
    	a.getEncAceites().stream().filter(x->x.getCodL()==this.codigoL).forEach(x->enc.add(x));
    	return enc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loja)) return false;
        Loja loja = (Loja) o;
        return Objects.equals(getCodigoL(), loja.getCodigoL()) &&
                Objects.equals(getNomeL(), loja.getNomeL());
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
