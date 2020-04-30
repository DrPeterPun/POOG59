import java.util.ArrayList;
import java.util.Objects;

public class Voluntarios {
    private ArrayList<Voluntario> voluntarios;

    public Voluntarios (){
        this.voluntarios=new ArrayList<>();
    }

    public Voluntarios(ArrayList<Voluntario> voluntarios){
        this.voluntarios=new ArrayList<>();
        if(voluntarios!=null)
        {
            for(Voluntario l: voluntarios)
                this.voluntarios.add(l.clone());
        }
    }

    public ArrayList<Voluntario> getVoluntarios(){
        return this.voluntarios;
    }

    public Voluntarios(Voluntarios t){
        this.voluntarios=t.getVoluntarios();
    }

    public Voluntarios clone(){
        return new Voluntarios(this);
    }

    // estes dois metodos permitem introduzir um voluntario diretamente na lista, é preciso fazer metodos analogos para as outras classes
    public void addVoluntario(String id, String email,String pass, double gpsx,double gpsy, float raio)
    {
        Voluntario v = new Voluntario(id, email, pass, gpsx, gpsy, raio);
        this.voluntarios.add(v);
    }

    public void addVoluntario(Voluntario v)
    {
        this.voluntarios.add(v);
    }



    @Override
    public String toString() {
        return "Voluntarios{ voluntarios=" + voluntarios +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntarios)) return false;
        Voluntarios that = (Voluntarios) o;
        return Objects.equals(getVoluntarios(), that.getVoluntarios());
    }

    public void registo(Voluntario v){
        this.voluntarios.add(v);
    }
}
