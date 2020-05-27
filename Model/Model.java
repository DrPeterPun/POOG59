package Model;

import java.util.List;
import java.util.stream.Collectors;

import src.EmpresaT;
import src.EmpresasT;
import src.Encomenda;
import src.Loja;
import src.Lojas;
import src.RegEncomendas;
import src.Utilizador;
import src.Utilizadores;
import src.Voluntario;
import src.Voluntarios;

public class Model { //Criei esta classe, nÃ£o sei se vai ser o model, mas a ideia Ã© ser um tipo de SGV como fizemos em LI3
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
	
	//--------- Global --------- 
	
	public boolean RegistaUser(Utilizador user) {
		if(this.users.addUtilizador(user))
		{
			//diz que foi concluido com sucesso
			return true;
		}
		else {
			//diz que nao deu
			return false;
		}
	}
	
	public boolean RegistaVoluntario(Voluntario volt) {
		if(this.volts.addVoluntario(volt))
		{
			//diz que foi concluido com sucesso
			return true;
		}
		else {
			//diz que nao deu
			return false;
		}
	}
	
	public boolean RegistaTransportadora(EmpresaT transportadora) {
		if(this.transportadoras.addEmpresa(transportadora))
		{
			//diz que foi concluido com sucesso
			return true;
		}
		else {
			//diz que nao deu
			return false;
		}
	}
	
	public boolean RegistaLoja(Loja loja) {
		if(this.lojas.addLoja(loja))
		{
			//diz que foi concluido com sucesso
			return true;
		}
		else {
			//diz que nao deu
			return false;
		}
	}
	
	public boolean logInUser(String email, String pass)
	{
		return  this.users.logIn(email, pass);
	}
	
	public boolean logInVol(String email, String pass)
	{
		return  this.volts.logIn(email, pass);
	}
	
	public boolean logInEmp(String email, String pass)
	{
		return  this.transportadoras.logIn(email, pass);
	}
	
	public boolean logInLoja(String email, String pass)
	{
		return this.lojas.logIn(email, pass);
	}
	
	//Inserir uma encomenda de uma loja, por parte do utilizador(NÃ£o testei)
	/*public void inserirEnc (Encomenda b){
	 
	}*/
	
	
	/*
	Processo ate uma encomenda estar completa
	1º user faz uma encomenda a uma loja
	-> é sugerido ao user um Transporte
	2º 	a)o utilizador nao aceita o tranporte mas ainda quer fazer a encomenda
		->  volta ao paco anterior
		b) o utilizador nao aceita o tranporte e quer deisstir da encomenda
		->  a encomenda é movida para as recusadas
		c)o utilizador aceita o transporte e a encomenda move para "
		->a encomenda é registada na loja como "pendente"
	3º a loja diz que a encomenda esta pronta
	->a encomenda é registada no transporte como"pendente"
	4º o transporte faz a encomenda
	-> a encomenda no transporte/loja/user passa de pendente para entregue
	-> o user faz a avaliacao da encomenda
	
	para isto precisamos de ter um "RegEncomndas" para cada utilzador/voluntario/loja
	
	*/
	
	//--------- User ---------
	
	public boolean fazEncomenda(Encomenda enc)
	{
		
		return false;
	}
	
	
	

}
