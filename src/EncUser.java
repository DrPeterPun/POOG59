package src;

import java.util.ArrayList;
import java.util.List;

public class EncUser extends Utilizador {//criei esta classe para guardar o histórico de encomendas de um utilizador
	private List<Encomenda> encUser;
	
	public EncUser() {
		super();
		this.encUser= new ArrayList<>();
	}
	
	public void addEncUser(Encomenda a, String ID) {
		if(getIduser().equals(ID)) this.encUser.add(a.clone());
	}

	public List<Encomenda> getEncUser() {
		return encUser;
	}

	@Override
	public String toString() {
		return "EncUser [encUser=" + encUser + ", getEncUser()=" + getEncUser() + ", getIduser()=" + getIduser()
				+ ", getName()=" + getName() + ", getGpsxuser()=" + getGpsxuser() + ", getGpsyuser()=" + getGpsyuser()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	

}
