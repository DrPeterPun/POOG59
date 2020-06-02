package src;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class Lojas implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2710839853129070335L;
	private Map<String,Loja> lojas;

    //Resolvi usar um TreeMap devido à ordem
    public Lojas(){
        this.lojas=new TreeMap<>();
    }

    public Lojas(Map<String,Loja> lojas){
        this.lojas=new TreeMap<>();
        for(Loja l: lojas.values())
            this.lojas.put(l.clone().getCodigoL(),l.clone());
    }

    public Map<String,Loja> getLojas() {
        return this.lojas;
    }

    public Lojas (Lojas l){
        this.lojas=l.getLojas();
    }

    public Lojas clone(){
        return new Lojas(this);
    }

    // estes dois métodos permitem adicionar uma loja caso ele não exista no sistema
    // como estes métodos existem em todas as classes que existem hashmap não sei se não será necessário pô-las numa abstract class
    public boolean addLoja(Loja l) {
        if(!(existeLoja(l.getCodigoL())))
        {
        	this.lojas.put(l.getCodigoL(),l.clone());
        	return true;
        }
        else return false;
    }

    public boolean existeLoja(String cod){
        boolean r=false;
        if(this.lojas.containsKey(cod)) r=true;
        return r;
    }
    
    public Optional<Loja> getLoja(String codigo){
        Optional<Loja> opt = Optional.empty();
        if(this.lojas.containsKey(codigo)) {
        	opt = Optional.ofNullable(this.lojas.get(codigo));
        }
        return opt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lojas)) return false;
        Lojas lojas1 = (Lojas) o;
        return Objects.equals(getLojas(), lojas1.getLojas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLojas());
    }

    @Override
    public String toString() {
        return "Lojas{" +
                "lojas=" + lojas +
                '}';
    }


	public boolean logIn(String codigo, String pass) {
		for(Map.Entry<String,Loja> entry : this.lojas.entrySet()) {
			if(entry.getValue().logIn(pass))
			{
				return true;
			}
		}
		return false;
	}
/*
	public void addEnc(Encomenda a) {
		String cod = a.getCodT();
		if(this.lojas.containsKey(cod))
		{
			this.lojas.get(cod).addEnc(a);
		}
	 
	}
	*/
	
	// no caso de o transportes ser feito por uma empresa vem logo para aqui e nao para as abertas, se for por um voluntario ele precisa de aceitar
	/*public void encAceite(Encomenda a) {
		String cod = a.getCodT();
		if(this.lojas.containsKey(cod))
		{
			this.lojas.get(cod).encAceite(a);
		}
	}
	
	// o voluntario recusou a encomenda
	public void encRecusada(Encomenda a) {
		String cod = a.getCodT();
		if(this.lojas.containsKey(cod))
		{
			this.lojas.get(cod).encRecusada(a);
		}
	}
	
	// a Loja diz que a encomenda esta cpronta
	public void encPronta(Encomenda a){
		String cod = a.getCodT();
		if(this.lojas.containsKey(cod))
		{
			this.lojas.get(cod).encPronta(a);
		}
	}
	
	// a encomenda ja foi entrgue mas ainda nao foi avaliada pelo user
	public void encPorAvaliar(Encomenda a){
		String cod = a.getCodT();
		if(this.lojas.containsKey(cod))
		{
			this.lojas.get(cod).encPorAvaliar(a);
		}
	}
	
	// encomenda foi completada
	public void encCompleta(Encomenda a){
		String cod = a.getCodT();
		if(this.lojas.containsKey(cod))
		{
			this.lojas.get(cod).encCompleta(a);
		}
	}
	*/
	/// TESTE
		public void printMap()
		{
			Set<Entry<String, Loja>> set = lojas.entrySet();
			for(Entry<String, Loja> ti: set)
			{
				System.out.println(ti.getKey() + " " + ti.getValue().getCodigoL());
			}
		}
	
}
