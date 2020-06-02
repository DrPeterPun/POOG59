package src;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;


public class Utilizadores implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1565948116459711786L;
	private Map<String,Utilizador> utilizadores; // mapa <idUser, utilizador>

    //Resolvi usar um TreeMap devido à ordem
    public Utilizadores(){
        this.utilizadores =new TreeMap<>(); 
    }

    public Utilizadores(Map<String,Utilizador> utilizadores){
        this.utilizadores = new TreeMap<>();
        for(Utilizador l: utilizadores.values())
            this.utilizadores.put(l.clone().getIdUser(),l.clone());
    }

    public Utilizadores (Utilizadores u){
        this.utilizadores = u.getUtilizadores();
    }    
      
    public Map<String,Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public Utilizadores clone(){
        return new Utilizadores(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizadores)) return false;
        Utilizadores that = (Utilizadores) o;
        return Objects.equals(getUtilizadores(), that.getUtilizadores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUtilizadores());
    }

    @Override
    public String toString() {
        return "Utilizadores{" +
                "utilizadores=" + utilizadores +
                '}';
    }

    public Optional<Utilizador> getUser(String email)
    {
    	for(Utilizador l: utilizadores.values())
    	{
    		if(l.getEmail().compareTo(email)==0)
    		{
    			return Optional.of(l);
    		}
    	}
    	return Optional.empty();    	
    }
    
    
   // estes dois métodos permitem adicionar um utilizador caso ele não exista no sistema
   // como estes métodos existem em todas as classes que existem hashmap não sei se não será necessário pô-las numa abstract class
    public boolean addUtilizador(Utilizador u){
        if(!(existeUser( u.getIdUser() ) ) )
        	{
        	this.utilizadores.put(u.getIdUser(), u.clone());
        	return true;
        	}
        return false;
                
   }

    public boolean existeUser(String cod){
        boolean r=false;
        if(this.utilizadores.containsKey(cod)) r= true;
        return r;
    }

	public boolean logIn(String email, String pass) {
		for(Map.Entry<String,Utilizador> entry : this.utilizadores.entrySet()) {
			if(entry.getValue().getEmail().compareTo(email)==0 && (entry.getValue().logIn(pass) ))
			{
				return true;				
			}
		}
		return false;
	}
	
	/*
	public void addEnc(Encomenda a) {
		String cod = a.getCodT();
		if(this.utilizadores.containsKey(cod))
		{
			this.utilizadores.get(cod).addEnc(a);
		}
		 
	}
		
	// no caso de o transportes ser feito por uma empresa vem logo para aqui e nao para as abertas, se for por um voluntario ele precisa de aceitar
	public void encAceite(Encomenda a) {
		String cod = a.getCodT();
		if(this.utilizadores.containsKey(cod))
		{
			this.utilizadores.get(cod).encAceite(a);
		}
	}
	
	// o voluntario recusou a encomenda
	public void encRecusada(Encomenda a) {
		String cod = a.getCodT();
		if(this.utilizadores.containsKey(cod))
		{
			this.utilizadores.get(cod).encRecusada(a);
		}
	}
	
	// a Loja diz que a encomenda esta cpronta
	public void encPronta(Encomenda a){
		String cod = a.getCodT();
		if(this.utilizadores.containsKey(cod))
		{
			this.utilizadores.get(cod).encPronta(a);
		}
	}
	
	// a encomenda ja foi entrgue mas ainda nao foi avaliada pelo user
	public void encPorAvaliar(Encomenda a){
		String cod = a.getCodT();
		if(this.utilizadores.containsKey(cod))
		{
			this.utilizadores.get(cod).encPorAvaliar(a);
		}
	}
	
	// encomenda foi completada
	public void encCompleta(Encomenda a){
		String cod = a.getCodT();
		if(this.utilizadores.containsKey(cod))
		{
			this.utilizadores.get(cod).encCompleta(a);
		}
	}
	
	*/
	/// TESTE
		public void printMap()
		{
			Set<Entry<String, Utilizador>> set = this.utilizadores.entrySet();
			for(Entry<String, Utilizador> ti: set)
			{
				System.out.println(ti.getKey() + " " + ti.getValue().getIdUser());
			}
		}

		public Utilizador getUserById(String codUt) {
			// TODO Auto-generated method stub
			return null;
		}
}