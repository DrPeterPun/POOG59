package src;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class EmpresasT {
    private Map<String,EmpresaT> transportadoras;


    public EmpresasT(){
        this.transportadoras= new TreeMap<>();

    }

    public EmpresasT(Map<String,EmpresaT> transportadoras){
        this.transportadoras =new TreeMap<>();
        for(EmpresaT l: transportadoras.values())
            this.transportadoras.put(l.clone().getCodigo(),l.clone());
    }

    public Map<String,EmpresaT> getTransportadoras(){
        return this.transportadoras;
    }

    public EmpresasT(EmpresasT t){
        this.transportadoras=t.getTransportadoras();
    }

    public EmpresasT clone(){
        return new EmpresasT(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpresasT)) return false;
        EmpresasT empresasT = (EmpresasT) o;
        return Objects.equals(getTransportadoras(), empresasT.getTransportadoras());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransportadoras());
    }

    @Override
    public String toString() {
        return "EmpresasT{" +
                "transportadoras=" + transportadoras +
                '}';
    }

    // estes dois métodos permitem adicionar uma empresa caso ela não exista no sistema
    // como estes métodos existem em todas as classes que existem hashmap não sei se não será necessário pô-las numa abstract class
    public boolean addEmpresa (EmpresaT e){
        if(!(existeEmpresa(e.getCodigo())))
        {
        	this.transportadoras.put(e.getCodigo(),new EmpresaT(e));
        	return true;
        }
        else return false;
    }

    public boolean existeEmpresa(String cod){
        boolean r=false;
        if(this.transportadoras.containsKey(cod)) r=true;
        return r;
    }

	public boolean logIn(String codigo, String pass) {
		for(Map.Entry<String,EmpresaT> entry : this.transportadoras.entrySet()) {
			if(entry.getValue().logIn(pass))
			{
				return true;
			}
		}
		return false;
	}
}
