package Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import src.*;


public class Model { //Criei esta classe, n√£o sei se vai ser o model, mas a ideia √© ser um tipo de SGV como fizemos em LI3
	private Utilizadores users;
	private Voluntarios volts;
	private EmpresasT empresas;
	private Transportadoras transportadoras;
	private Lojas lojas;
	private RegEncomendas regEnc;
	
	private Optional<Utilizador> currentUser;
	private Optional<EmpresaT> currentEmpresa;
	private Optional<Voluntario> currentVol;
	private Optional<Loja> currentLoja;
	

	public Model() {
		this.users= new Utilizadores();
		this.volts= new Voluntarios();
		this.empresas= new EmpresasT();
		this.transportadoras = new Transportadoras();
		this.lojas= new Lojas();
		this.regEnc= new RegEncomendas();
		this.currentUser = Optional.empty();
		this.currentEmpresa =Optional.empty();
		this.currentVol = Optional.empty();
		this.currentLoja = Optional.empty();
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
	
	public boolean RegistaEmpresa(EmpresaT empresa) {
		if(this.empresas.addEmpresa(empresa))
		{
			//diz que foi concluido com sucesso
			return true;
		}
		else {
			//diz que nao deu
			return false;
		}
	}
	
	public boolean RegistaTransportadora(TransporteInterface transportadora) {
		if(this.transportadoras.addTransportadora(transportadora))
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
		return  this.empresas.logIn(email, pass);
	}
	
	public boolean logInLoja(String email, String pass)
	{
		return this.lojas.logIn(email, pass);
	}
	
	//Inserir uma encomenda de uma loja, por parte do utilizador(N√£o testei)
	/*public void inserirEnc (Encomenda b){
	 
	}*/
	
	
	

    public void addEnc(Encomenda a) {
		this.users.addEnc(a);
		this.transportadoras.addEnc(a);
		this.lojas.addEnc(a);
	}
	
	// no caso de o transportes ser feito por uma empresa vem logo para aqui e nao para as abertas, se for por um voluntario ele precisa de aceitar
	public void encAceite(Encomenda a) {
		this.users.encAceite(a);
		this.transportadoras.encAceite(a);
		this.lojas.encAceite(a);
	}
	
	// o voluntario recusou a encomenda
	public void encRecusada(Encomenda a) {
		this.users.encRecusada(a);
		this.transportadoras.encRecusada(a);
		this.lojas.encRecusada(a);
	}
	
	// a Loja diz que a encomenda esta cpronta
	public void encPronta(Encomenda a){
		this.users.encPronta(a);
		this.transportadoras.encPronta(a);
		this.lojas.encPronta(a);
	}
	
	// a encomenda ja foi entrgue mas ainda nao foi avaliada pelo user
	public void encPorAvaliar(Encomenda a){
		this.users.encPorAvaliar(a);
		this.transportadoras.encPorAvaliar(a);
		this.lojas.encPorAvaliar(a);
	}
	
	// encomenda foi completada
	public void encCompleta(Encomenda a){
		this.users.encCompleta(a);
		this.transportadoras.encCompleta(a);
		this.lojas.encCompleta(a);
	}
	
	
	//--------- User ---------
	
	/*
	Processo ate uma encomenda estar completa
	1∫ user faz uma encomenda a uma loja
	-> È sugerido ao user um Transporte
	2∫ 	a)o utilizador nao aceita o tranporte mas ainda quer fazer a encomenda
		->  volta ao paco anterior
		b) o utilizador nao aceita o tranporte e quer deisstir da encomenda
		->  a encomenda È movida para as recusadas
		c)o utilizador aceita o transporte e a encomenda move para "
		->a encomenda È registada na loja como "pendente"
	3∫ a loja diz que a encomenda esta pronta
	->a encomenda È registada no transporte como"pendente"
	4∫ o transporte faz a encomenda
	-> a encomenda no transporte/loja/user passa de pendente para entregue
	-> o user faz a avaliacao da encomenda
	
	para isto precisamos de ter um "RegEncomndas" para cada utilzador/voluntario/loja
	
	*/
	
	@SuppressWarnings("unused")
	public boolean fazEncomenda(Encomenda enc)
	{
		boolean done =  false; 
		boolean querOutra = false;
		while(!done)
		{
			//perguntar a view se quer prioritizar por mais barato ou mais perto e wtv
			int pol=0; // maisperto =0; mais barato =1
			Loja loja = this.lojas.getLoja(enc.getCodL()).get();
			Utilizador user = this.currentUser.get();
			TransporteInterface transportador;
			if(pol==0)
			{
				transportador = this.transportadoras.detMaisPerto(user.getGpsx(),user.getGpsy(),loja.getGpsx(),loja.getGpsy(),enc.getPeso());
			}
			else
			{
				transportador = this.transportadoras.detMaisBarato(user.getGpsx(),user.getGpsy(),loja.getGpsx(),loja.getGpsy(),enc.getPeso());	
			}
		
			if (transportador instanceof EmpresaT)
			{
				//Pergunta ao user se esta contente com a escolhe, se estiver done=1 se nao nao faz nada e volta ao inicio
				if(true)//confirmar a escolhe, //se nao confir mar volta ao inicio
				{
					this.encAceite(enc);
					done = true;
				}
				else 
				{
					//cena a perguntar se quer outra transportadora
					querOutra = false;
					done=!querOutra;
				}
			}
			else
			{
				this.addEnc(enc);
				done = true;
			}
		}
		
		
		return done;// se done for 0 È pq nao acabou a encomenda
	}
	
	public void rateTransp()
	{
		for(int i=0;i<1;i++)// para todas as encomendas por avaliar pergunta se quer avaliar
		{
			if(true)//quer avaliar?
			{
				//pede a avaliacao ao user 
				//insere a avaliacao no transportador
				//move a encomende de "por avaliar" para "terminada"
			}
		}
	}
	
	//--------- Voluntario---------
	public void preparaAceitarEnc()
	{
		for(int i=0;i<1;i++)// para todas as encomendas por aceitar pergunta se quer aceitar
		{
			//pergunta ao voluntario se aceita a encomenda
			//se sim move a encomenda de "aberta" para "aceite" 
		}
	}
	
	//--------- Voluntario // EmpresaT---------
	public void enviarEnc()
	{
		boolean enviou=false;
		for(int i=0;i<1;i++)// para todas as encomendas aceites pergunta se quer enviar a enc
		{
			//pergunta ao voluntario//empresa se quer enviar a encomenda
			
			if(enviou)//quis enviar a encomenda
			{
				return;
			}
		}
	}
	
	//--------- Loja ---------
	public void preparaEnc()
	{
		for(int i=0;i<1;i++)// para todas as encomenda spoir sinalizar pergunta se quer siznalizar
		{
			//pergunta a loja se quer por esta encomendas como pronta para ser entregue
		}
	}
}
