package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import src.*;


public class Model implements Serializable { //Criei esta classe, não sei se vai ser o model, mas a ideia é ser um tipo de SGV como fizemos em LI3
	/**
	 * 
	 */
	private static final long serialVersionUID = -8208583672689808386L;
	private Utilizadores users;
	private Voluntarios volts;
	//private EmpresasT empresas;
	private Transportadoras transportadoras;
	private Lojas lojas;
	private Encomendas encomendas;
	
	private Optional<Utilizador> currentUser;
	private Optional<EmpresaT> currentEmp;
	private Optional<Voluntario> currentVol;
	private Optional<Loja> currentLoja;
	

	public Model() {
		this.users= new Utilizadores();
		this.volts= new Voluntarios();
		//this.empresas= new EmpresasT();
		this.transportadoras = new Transportadoras();
		this.lojas= new Lojas();
		this.currentUser = Optional.empty();
		this.currentEmp =Optional.empty();
		this.currentVol = Optional.empty();
		this.currentLoja = Optional.empty();
	}	
	
	public Model(Utilizadores utilizadores, Transportadoras transp, Lojas lojas2, Encomendas encomendas2) {
		this.users= utilizadores;
		this.volts= new Voluntarios();
		//this.empresas= new EmpresasT();
		this.transportadoras = transp;
		this.lojas= lojas2;
		this.encomendas =encomendas2;
		this.currentUser = Optional.empty();
		this.currentEmp =Optional.empty();
		this.currentVol = Optional.empty();
		this.currentLoja = Optional.empty();
	}
	
	
	
	//--------- Global --------- 
	
	public Utilizadores getUsers() {
		return users;
	}

	public void setUsers(Utilizadores users) {
		this.users = users;
	}

	public Voluntarios getVolts() {
		return volts;
	}

	public void setVolts(Voluntarios volts) {
		this.volts = volts;
	}

	public void printutilizadores()
	{
		System.out.println(this.users.toString());
	}
	
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
		if(this.transportadoras.addTransportadora(volt))
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
		if(this.transportadoras.addTransportadora(empresa))
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
	
	public void logOut()
	{
		this.currentEmp = Optional.empty();
		this.currentVol = Optional.empty();
		this.currentUser = Optional.empty();
		this.currentLoja= Optional.empty();
	}
	
	public boolean logInUser(String email, String pass)
	{
		if(this.users.logIn(email, pass))
		{
			this.logOut();
			this.currentUser = this.users.getUser(email);
			return true;
		}
		return false;
	}
	
	public boolean logInVol(String email, String pass)
	{
		if(this.transportadoras.logIn(email, pass))
		{
			this.logOut();
			this.currentVol = this.transportadoras.getVol(email);
			return true;
		}
		return false;
	}
	
	public boolean logInEmp(String email, String pass)
	{
		if(this.transportadoras.logIn(email, pass))
		{
			this.logOut();
			this.currentEmp = this.transportadoras.getEmp(email);
			return true;
		}
		return false;
	}
	
	public boolean logInLoja(String email, String pass)
	{
		if(this.lojas.logIn(email, pass))
		{
			this.logOut();
			this.currentLoja = this.lojas.getLoja(email);
			return true;
		}
		return false;
	}
	
	
	public void printEncs(String id)
	{
		// "" faz com que imprima todas as encs
		ArrayList<String> lista = new ArrayList<String>();
		for(Entry<String, Encomenda> enc: this.encomendas.getEncomendas().entrySet())
		{
			if(enc.getValue().getCodL().compareTo(id)==0 || enc.getValue().getCodT().compareTo(id)==0 || enc.getValue().getCodUt().compareTo(id)==0 || id.compareTo("")==0 )
			{
				lista.add(enc.getValue().getCodEnc() + " " + enc.getValue().getEstado());
			}
		}
		System.out.println(lista);
	}
	
	public void saveToFile (String file) throws IOException
	{
	   	FileOutputStream f = new FileOutputStream(new File(file));
		ObjectOutputStream o = new ObjectOutputStream(f);

		// Write objects to file
		o.writeObject(this);
		f.close();
		o.close();
	}
		
		
	public Model loadFromFile (String file) throws IOException, ClassNotFoundException
	{
		FileInputStream fi = new FileInputStream(new File(file));
		ObjectInputStream oi = new ObjectInputStream(fi);
		Model modelo = (Model) oi.readObject();
		oi.close();
		fi.close();
		return modelo;
	}
	
	
	//Inserir uma encomenda de uma loja, por parte do utilizador(Não testei)
	/*public void inserirEnc (Encomenda b){
	 
	}*/
	
	
	
/*
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
	}*/
	
	
	//--------- User ---------
	
	/*
	Processo ate uma encomenda estar completa
	1� user faz uma encomenda a uma loja
	-> � sugerido ao user um Transporte
	2� 	a)o utilizador nao aceita o tranporte mas ainda quer fazer a encomenda
		->  volta ao paco anterior
		b) o utilizador nao aceita o tranporte e quer deisstir da encomenda
		->  a encomenda � movida para as recusadas
		c)o utilizador aceita o transporte e a encomenda move para "
		->a encomenda � registada na loja como "pendente"
	3� a loja diz que a encomenda esta pronta
	->a encomenda � registada no transporte como"pendente"
	4� o transporte faz a encomenda
	-> a encomenda no transporte/loja/user passa de pendente para entregue
	-> o user faz a avaliacao da encomenda	
	*/
	
	public void getLojasMPerto(int range) {
		
	}
	
	
	
	
	
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
			if(pol==0) //determina que transportadora � proposta ao user
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
					enc.setCodT(transportador.getCodigo());
					this.encomendas.addEncomenda(enc);
					this.encomendas.setToAberto(enc);
					done = true;
				}
				else 
				{
					//cena a perguntar se quer outra transportadora
					querOutra = false;
					done=!querOutra;
				}
			}
			else//ser o transpoerte for feito por um voluntario
			{
				//this.addEnc(enc);
				this.encomendas.addEncomenda(enc);
				this.encomendas.setToAberto(enc);
				this.encomendas.avancaEstado(enc);
				done = true;
			}
		}
		
		
		return done;// se done for 0 � pq nao acabou a encomenda
	}
	
	public void rateTransp()
	{
		for(Entry<String, Encomenda> enc: this.encomendas.getEncomendas().entrySet())// para todas as encomendas por avaliar pergunta se quer avaliar
		{
			if( enc.getValue().getCodUt().compareTo(this.currentUser.get().getIdUser()) == 0 && (enc.getValue().getEstado()==3) )//quer avaliar?
			{
				//pede a avaliacao ao user 
				//insere a avaliacao no transportador
				this.encomendas.avancaEstado(enc.getValue());
				//move a encomende de "por avaliar" para "terminada"
			}
		}
	}
	
	public List<Encomenda> showEncUser(Utilizador user)
	{
		TreeMap<Date,Encomenda> tree = new TreeMap<Date,Encomenda>();
		Map<String, Encomenda> encs = this.encomendas.getEncomendas();
		
		for(Map.Entry<String, Encomenda> entry : encs.entrySet())
		{
			if(entry.getValue().getCodUt().compareTo(user.getIdUser()) ==0)
			{
				tree.put(entry.getValue().getDate(), entry.getValue());
			}
		}
		List<Encomenda> list = new ArrayList<Encomenda>(encs.values());
		return list;
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
	
	
	//--------- EmpresaT---------
	
	public long faturadoEnc(String CodEmp)
	{
		EmpresaT emp = this.transportadoras.getEmpById(CodEmp);
		Map<String, Encomenda> encs = this.encomendas.getEncomendas();
		long total=0;
		for(Map.Entry<String, Encomenda> entry : encs.entrySet())
		{
			if(entry.getValue().getCodT().compareTo(emp.getCodigo())==0)
			{
				Utilizador ut= this.users.getUtilizadores().get(entry.getValue().getCodUt());
				Loja lj = this.lojas.getLojas().get(entry.getValue().getCodL());
				total+=emp.detPreco(ut.getGpsx(), ut.getGpsy(), lj.getGpsx(), lj.getGpsy(), entry.getValue().getPeso());
			}
		}
		return total;
	}
	
	//--------- Loja ---------
	public void preparaEnc()
	{
		for(int i=0;i<1;i++)// para todas as encomenda spoir sinalizar pergunta se quer siznalizar
		{
			//pergunta a loja se quer por esta encomendas como pronta para ser entregue
		}
	}
	
	//--------- Encomendas ---------
	
	public void setToAberto(Encomenda e)
	{
	   this.encomendas.setToAberto(e);
	}
	
	public void avancaEstado(Encomenda e)
	{
		this.encomendas.avancaEstado(e);
	}
	   
	public void recusarEnc(Encomenda e)
	{
		this.encomendas.recusarEnc(e);
	}
}
