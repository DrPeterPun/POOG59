package src;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class Voluntarios implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2009483883153455525L;
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
    public boolean addVoluntario(Voluntario v) {
        if(!(existeV(v.getCodVol())))
        {
        	this.voluntarios.put(v.getCodVol(),v.clone());
        	return true;
        }
        else return false;
    }

    public boolean existeV(String cod){
        boolean r=false;
        if(this.voluntarios.containsKey(cod))r=true;
        return r;
    }

	public boolean logIn(String codigo, String pass) {
		for(Map.Entry<String,Voluntario> entry : this.voluntarios.entrySet()) {
			if(entry.getValue().logIn(pass))
			{
				return true;
			}
		}
		return false;
	}
	/// TESTE
	public void printMap()
	{
		Set<Entry<String, Voluntario>> set = voluntarios.entrySet();
		for(Entry<String, Voluntario> ti: set)
		{
			System.out.println(ti.getKey() + " " + ti.getValue().getCodigo());
		}
	}

}
