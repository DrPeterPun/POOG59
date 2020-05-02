import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class Utilizadores {
    private HashMap<String,Utilizador> utilizadores;

    public Utilizadores(){
        this.utilizadores =new HashMap<>();
    }

    public Utilizadores(Map<String,Utilizador> utilizadores){
        this.utilizadores =new HashMap<>();
        for(Utilizador l: utilizadores.values())
            this.utilizadores.put(l.clone().getIduser(),l.clone());
    }

    public Utilizadores (Utilizadores u){
        this.utilizadores = u.getUtilizadores();
    }    
      
    public HashMap<String,Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public Utilizadores clone(){
        return new Utilizadores(this);
    }

}
