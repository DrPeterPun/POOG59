import java.util.ArrayList;

public class Utilizadores {
    private ArrayList<Utilizador> utilizadores;

    public Utilizadores(){
        this.utilizadores =new ArrayList<>();
    }

    public Utilizadores(ArrayList<Utilizador> utilizadores){
        this.utilizadores=new ArrayList<>();
        for(Utilizador l: utilizadores)
            this.utilizadores.add(l.clone());
    }

    public Utilizadores (Utilizadores u){
        this.utilizadores = getUtilizadores();
    }    
      
    public ArrayList<Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public Utilizadores clone(){
        return new Utilizadores(this);
    }

}
