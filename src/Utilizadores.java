package src;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;


public class Utilizadores implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1565948116459711786L;
	/**Map que guarda como keys o código de um utilizador e como values a informação desse utilizador*/
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
      
    public Map<String,Utilizador> getUtilizadores() {
        return new HashMap<String,Utilizador>(this.utilizadores);
    }

    public Utilizadores clone(){
        return new Utilizadores(this.getUtilizadores());
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
    
    
   /**Método que adiciona um utilizador ao map de utilizadores
    * @param u Um utilizador*/
    public boolean addUtilizador(Utilizador u){
        if(!(existeUser( u.getIdUser() ) ) )
        	{
        	this.utilizadores.put(u.getIdUser(), u.clone());
        	return true;
        	}
        return false;
                
   }
    
    /**Método que nos diz se um tulizador já existe no map
     * @param cod Código de utilizador*/
    public boolean existeUser(String cod){
        boolean r=false;
        if(this.utilizadores.containsKey(cod)) r= true;
        return r;
    }
    
    /**Método que faz o logIn de um utilizador
     * @param email Email de um utilizador
     * @param pass Palavra-passe de um utilizador*/
	public boolean logIn(String email, String pass) {
		for(Map.Entry<String,Utilizador> entry : this.utilizadores.entrySet()) {
			if(entry.getValue().getEmail().compareTo(email)==0 && (entry.getValue().logIn(pass) ))
			{
				return true;				
			}
		}
		return false;
	}
	
		public Utilizador getUserById(String codUt) {
			// TODO Auto-generated method stub
			return null;
		}
}