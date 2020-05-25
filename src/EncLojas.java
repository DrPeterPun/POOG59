package src;

import java.util.ArrayList;
import java.util.List;

public class EncLojas extends Loja{//criei esta classe para guardar o histórico de encomendas de uma loja
private List<Encomenda> encLojas;
	
	public EncLojas() {
		super();
		this.encLojas= new ArrayList<>();
	}
	
	public void addEncLoja(Encomenda a, String ID) {
		if(getCodigoL().equals(ID)) this.encLojas.add(a.clone());
	}

	public List<Encomenda> getEncLojas() {
		return encLojas;
	}

	@Override
	public String toString() {
		return "EncLojas [encLojas=" + encLojas + ", getEncLojas()=" + getEncLojas() + ", getCodigoL()=" + getCodigoL()
				+ ", getNomeL()=" + getNomeL() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	
}
