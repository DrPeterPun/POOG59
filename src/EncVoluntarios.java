package src;

import java.util.ArrayList;
import java.util.List;

public class EncVoluntarios extends Voluntario {//criei esta classe para guardar o histórico de encomendas de um voluntário
    private List<Encomenda> encV;
	
	public EncVoluntarios() {
		super();
		this.encV= new ArrayList<>();
	}
	
	public void addEncVol(Encomenda a, String ID) {
		if(getCodVol().equals(ID)) this.encV.add(a.clone());
	}

	public List<Encomenda> getEncV() {
		return encV;
	}

	@Override
	public String toString() {
		return "EncVoluntarios [encV=" + encV + ", getEncV()=" + getEncV() + ", getCodVol()=" + getCodVol()
				+ ", getNome()=" + getNome() + ", getGpsx()=" + getGpsx() + ", getGpsy()=" + getGpsy() + ", getRaio()="
				+ getRaio() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
   
	
}
