import java.util.*;


public class Utilizadores {
    private Map<String,Utilizador> utilizadores;

    //Resolvi usar um TreeMap devido à ordem
    public Utilizadores(){
        this.utilizadores =new TreeMap<>();
    }

    public Utilizadores(Map<String,Utilizador> utilizadores){
        this.utilizadores =new TreeMap<>();
        for(Utilizador l: utilizadores.values())
            this.utilizadores.put(l.clone().getIduser(),l.clone());
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

    // estes dois métodos permitem adicionar um utilizador caso ele não exista no sistema
    // como estes métodos existem em todas as classes que existem hashmap não sei se não será necessário pô-las numa abstract class
    public void addUtilizadores(Utilizador u){
        if(!(existeUser(u.getIduser())))
        this.utilizadores.put(u.getIduser(),new Utilizador(u.getIduser(),u.getName(),u.getPassU(),u.getEmailU(),u.getGpsxuser(),u.getGpsyuser()));
    }

    public boolean existeUser(String cod){
        boolean r=false;
        if(this.utilizadores.containsKey(cod)) r= true;
        return r;
    }
/*
    public boolean loginUV (String email, String pass){
        boolean r=false;
        for(String l: utilizadores.keySet())
        r = (this.utilizadores.containsValue(email)&&this.utilizadores.containsValue(pass));
        return r;
    }
*/
    public boolean loginUV (String email, String pass){
        return (this.utilizadores.containsValue(email)&&this.utilizadores.containsValue(pass));
    }
}
