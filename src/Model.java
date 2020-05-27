package src;

import java.util.List;
import java.util.stream.Collectors;

public class Model { //Criei esta classe, não sei se vai ser o model, mas a ideia é ser um tipo de SGV como fizemos em LI3
	private Utilizadores users;
	private Voluntarios volts;
	private EmpresasT transportadoras;
	private Lojas lojas;
	private RegEncomendas regEnc;

	public Model() {
		this.users= new Utilizadores();
		this.volts= new Voluntarios();
		this.transportadoras= new EmpresasT();
		this.lojas= new Lojas();
		this.regEnc= new RegEncomendas();
	}	
	
	public void RegistaUser(Utilizador user) {
		this.users.addUtilizador(user);
	}
	
	public void RegistaVoluntario(Voluntario volt) {
		this.volts.addVoluntario(volt);
	}
	
	public void RegistaTransportadora(EmpresaT transportadora) {
		this.transportadoras.addEmpresa(transportadora);
	}
	
	public void RegistaLoja(Loja loja) {
		this.lojas.addLoja(loja);
	}
	
	//Verificar se o email e a pass coincidem, não sei se se faz isto nesta classe
	/*public boolean checkEmailPass(String a, String b, String c) {
		return register(a,b,c);
	}*/
	
	//Inserir uma encomenda de uma loja, por parte do utilizador(Não testei)
	/*public void inserirEnc (Encomenda b){
	 
	}*/
	
	
	
	
	
	
	
	
	

}
