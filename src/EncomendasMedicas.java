package src;

import java.util.ArrayList;

public class EncomendasMedicas extends Encomenda {
 
	public EncomendasMedicas() {
		super();
	}
	
	public EncomendasMedicas(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas,int estado) {
		super(codEnc,codUt,codL,peso,encomendas,estado);
	}
	
	/*public EncomendasMedicas clone() {
		new EncomendasMedicas(this);
	}*/
}
