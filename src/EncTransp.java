package src;

import java.util.ArrayList;
import java.util.List;

public class EncTransp extends EmpresaT{//criei esta classe para guardar o histórico de encomendas de uma transportadora
    private List<Encomenda> encTransp;
	
	public EncTransp() {
		super();
		this.encTransp= new ArrayList<>();
	}
	
	public void addEncTransp(Encomenda a, String ID) {
		if(getCodigoEmpresa().equals(ID)) this.encTransp.add(a.clone());
	}

	public List<Encomenda> getEncTransp() {
		return encTransp;
	}

	@Override
	public String toString() {
		return "EncTransp [encTransp=" + encTransp + ", getEncTransp()=" + getEncTransp() + ", getCodigoEmpresa()="
				+ getCodigoEmpresa() + ", getNomeEmpresa()=" + getNomeEmpresa() + ", getNif()=" + getNif()
				+ ", getRaio()=" + getRaio() + ", getPrecokm()=" + getPrecokm() + ", getGpsx()=" + getGpsx()
				+ ", getGpsy()=" + getGpsy() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
    
	
}
