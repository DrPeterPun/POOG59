import java.util.*;

public class Voluntarios {
    private Map<String,Voluntario> voluntarios;

    //Resolvi usar um TreeMap devido à ordem
    public Voluntarios (){
        this.voluntarios=new TreeMap<>();
    }

    public Voluntarios(TreeMap<String,Voluntario> voluntarios){
        this.voluntarios=new TreeMap<>();
            for(Voluntario l: voluntarios.values())
                this.voluntarios.put(l.clone().getCodVol(),l.clone());
    }

    public Map<String,Voluntario> getVoluntarios(){
        return this.voluntarios;
    }

    public Voluntarios(Voluntarios t){
        this.voluntarios=t.getVoluntarios();
    }

    public Voluntarios clone(){
        return new Voluntarios(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntarios)) return false;
        Voluntarios that = (Voluntarios) o;
        return Objects.equals(getVoluntarios(), that.getVoluntarios());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVoluntarios());
    }

    @Override
    public String toString() {
        return "Voluntarios{" +
                "voluntarios=" + voluntarios +
                '}';
    }

    // estes dois métodos permitem adicionar um voluntario caso ele não exista no sistema
    // como estes métodos existem em todas as classes que existem hashmap não sei se não será necessário pô-las numa abstract class
    public void addVoluntario(Voluntario v) {
        if(!(existeV(v.getCodVol())))
        this.voluntarios.put(v.getCodVol(),new Voluntario(v.getCodVol(),v.getNome(),v.getGpsx(),v.getGpsy(),v.getRaio()));
    }

    public boolean existeV(String cod){
        boolean r=false;
        if(this.voluntarios.containsKey(cod))r=true;
        return r;
    }


}
