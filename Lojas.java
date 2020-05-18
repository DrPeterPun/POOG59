import java.util.*;

public class Lojas {
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
    public void addLoja(Loja l) {
        if(!(existeLoja(l.getCodigoL())))
        this.lojas.put(l.getCodigoL(),new Loja(l.getCodigoL(),l.getNomeL() ));
    }

    public boolean existeLoja(String cod){
        boolean r=false;
        if(this.lojas.containsKey(cod)) r=true;
        return r;
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
}
