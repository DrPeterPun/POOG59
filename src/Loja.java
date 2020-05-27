package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Loja {
    private String codigoL;
    private String nomeL;
    private Encomendas encL;

    public Loja(){
        this.codigoL=" ";
        this.nomeL=" ";
        this.encL= new Encomendas();
    }

    public Loja(String codigoL, String nomeL){
        this.codigoL=codigoL;
        this.nomeL=nomeL;
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

    public Loja(Loja l){
        this.codigoL=getCodigoL();
        this.nomeL=getNomeL();
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
}
