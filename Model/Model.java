package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Viewer.Viewer;
import src.*;


public class Model implements Serializable { //Criei esta classe, não sei se vai ser o model, mas a ideia é ser um tipo de SGV como fizemos em LI3
	/**
	 * 
	 */
	private static final long serialVersionUID = -8208583672689808386L;
	private Utilizadores users;

	private Voluntarios volts;
	private EmpresasT empresas;
	private Transportadoras transportadoras;
	private Lojas lojas;
	private Encomendas encomendas;
	
	private Utilizador currentUser;
	private EmpresaT currentEmp;
	private Voluntario currentVol;
	private Loja currentLoja;
	

	public Model() {
		this.users= new Utilizadores();
		//this.volts= new Voluntarios();
		//this.empresas= new EmpresasT();
		this.transportadoras = new Transportadoras();
		this.lojas= new Lojas();
		this.currentUser = new Utilizador();
		this.currentEmp = new EmpresaT();
		this.currentVol = new Voluntario();
		this.currentLoja = new Loja();
	}	
	
	public Model(Utilizadores utilizadores, Transportadoras transp, Lojas lojas2, Encomendas encomendas2) {
		this.users= utilizadores;
		//this.volts= new Voluntarios();
		//this.empresas= new EmpresasT();
		this.transportadoras = transp;
		this.lojas= lojas2;
		this.encomendas =encomendas2;
		this.currentUser = new Utilizador();
		this.currentEmp = new EmpresaT();
		this.currentVol = new Voluntario();
		this.currentLoja = new Loja();
		preencheEncs();
	}
	
	
	
	//--------- Global --------- 
	
     
	
	public void printutilizadores()
	{
		System.out.println(this.users.toString());
	}
	
	/*public Utilizador getUti(String email) {
		return this.users.getUti(email);
	}*/
	
	public Utilizadores getUsers() {
		return users.clone();
	}

	public void setUsers(Utilizadores users) {
		this.users = users;
	}
	
	public Transportadoras getTransp()
	{
		return transportadoras.clone();
	}

	/*public Voluntarios getVolts() {
		return volts;
	}

	public void setVolts(Voluntarios volts) {
		this.volts = volts;
	}*/

	public Utilizador getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Utilizador currentUser) {
		this.currentUser = currentUser;
	}

	public EmpresaT getCurrentEmp() {
		return currentEmp;
	}

	public void setCurrentEmp(EmpresaT currentEmp) {
		this.currentEmp = currentEmp;
	}

	public Voluntario getCurrentVol() {
		return currentVol;
	}

	public void setCurrentVol(Voluntario currentVol) {
		this.currentVol = currentVol;
	}

	public Loja getCurrentLoja() {
		return currentLoja;
	}

	public void setCurrentLoja(Loja currentLoja) {
		this.currentLoja = currentLoja;
	}
    
	/**Método que regista um user
	 * @param user Um Utilizador*/
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
	
	/**Método que regista uma transportadora seja ela uma empresa ou um voluntário
	 * @param transportadora Uma transportadora*/
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
	
	/**Método que regista uma loja
	 * @param loja Uma Loja*/
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
	
	/**Método que faz o logout da pessoa que está a utilizar a app*/
	public void logOut()
	{
		this.currentUser = new Utilizador();
		this.currentEmp = new EmpresaT();
		this.currentVol = new Voluntario();
		this.currentLoja = new Loja();
	}
	
	/**Método que faz o logIn de um user
	 * @param email Um email 
	 * @param pass Uma pass*/
	public boolean logInUser(String email, String pass)
	{ return this.users.logIn(email, pass);
		
	}
	
	/**Método que faz o logIn de um voluntário
	 * @param email Um email 
	 * @param pass Uma pass*/
	public boolean logInVol(String email, String pass)
	{
		return this.transportadoras.logIn(email, pass);

	}
	
	/**Método que faz o logIn de uma empresa transportadora
	 * @param email Um email 
	 * @param pass Uma pass*/
	public boolean logInEmp(String email, String pass)
	{
		return this.transportadoras.logIn(email, pass);
	}
	
	/**Método que faz o logIn de uma loja
	 * @param Um email e uma pass*/
	public boolean logInLoja(String email, String pass)
	{
		return this.lojas.logIn(email, pass);
	}
	
	/**Função que imprime o histórico de encomendas da pessoa que está a utiliza a app
	 * @param id Um código(seja de um user, de um voluntário, de uma empresa transportadora ou de uma loja)*/
	public void printEncs(String id)
	{
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
	
	
	public void preencheEncs()
	{
		this.encomendas.preencheEncs(this.transportadoras);
	}
	
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
	
	/**Método que lista as lojas que se encontram a um determinado range do utilizador
	 * @param range Um double que determina a que distância o user quer que estaja a loja 
	 * @param a Um utilizador*/
	public List<String> getLojasMPerto (double range,Utilizador a) {
		List<String> b = new ArrayList<>();
		this.lojas.getLojas().values().stream().filter(x-> (x.detDistL(a.getGpsx(), a.getGpsy())<=range)).forEach(x-> b.add(x.getNomeL()));
		return b;
	}
	
	/**Método que determina a lista de todas as lojas possiveis*/
	public List<String> getTodasLojas(){
		List<String> a = new ArrayList<>();
		this.lojas.getLojas().values().forEach(x->a.add(x.getNomeL()));
		return a;
	}
	
	/**Método que procura uma loja pelo nome
	 * @param nome O nome de uma loja*/
	public Loja getLojaPN(String nome) {
		return this.lojas.procurarLojaNome(nome);
	}
	
	
	/**Método que procura uma empresa transportadora pelo nome e devolve o seu código
	 * @param nome Nome da empresa*/
	public String getTranspPN(String nome) {
		String s = "";
		for(EmpresaT a: this.empresas.getValues()) {
			if(a.getNome().equals(nome)) s= a.getCodigo();
		}
		return s;
	}
	
	/**Método que faz o processo de encomendar uma encomenda
	 * @param enc Uma encomenda*/
	public boolean fazEncomenda(Encomenda enc)	
	{
		enc.calculaPeso();
	    boolean done =  false; 
		boolean querOutra = false;
		while(!done)
		{
			Viewer.prints("Deseja priorizar por: \n (1)Mais bataro \n (2)Mais perto \n  " );
			int pol=Viewer.choiceI(); 
			Loja loja = this.lojas.getLoja(enc.getCodL()).get();
			
			Utilizador user = this.currentUser;
			TransporteInterface transportador;
			if(pol==0) 
			{
				transportador = this.transportadoras.detMaisPerto(user.getGpsx(),user.getGpsy(),loja.getGpsx(),loja.getGpsy());
			}
			else
			{
				transportador = this.transportadoras.detMaisBarato(user.getGpsx(),user.getGpsy(),loja.getGpsx(),loja.getGpsy(),enc.getPeso());	
			}
		
			if (transportador instanceof EmpresaT)
			{
				Viewer.prints("Deseja prosseguir com a encomenda? S/N");
				String escolha=Viewer.choiceS();
				if(escolha.equals("S"))
				{
					enc.setCodT(transportador.getCodigo());
					this.encomendas.addEncomenda(enc);
					this.encomendas.setToAberto(enc);
					this.encomendas.avancaEstado(enc);
					done = true;
				}
				else 
				{
					Viewer.prints("Quer escolher outra transportadora? S/N");
					String c=Viewer.choiceS();
					if(c.equals("F")) {
					querOutra = false;
					done=!querOutra;}
				}
			}
			else//ser o transpoerte for feito por um voluntario
			{
				this.encomendas.addEncomenda(enc);
				this.encomendas.setToAberto(enc);
				//this.encomendas.avancaEstado(enc); //como os  voluntarios tem de aceitar as encomendas esta fica so em aberto
				done = true;
			}
		}
		
		
		return done;// se done for 0 � pq nao acabou a encomenda
	}
	
	/** Método que permite ao utilizador atual avaliar as suas encomendas por avaliar*/
	public void rateTransp()
	{
		for(Entry<String, Encomenda> enc: this.encomendas.getEncomendas().entrySet())// para todas as encomendas por avaliar pergunta se quer avaliar
		{
			if( enc.getValue().getCodUt().compareTo(this.currentUser.getIdUser()) == 0 && (enc.getValue().getEstado()==3) )//quer avaliar?
			{

				Viewer.prints("Como quer avaliar o transporte da encomenda:j" + enc.toString() );
            	int rt=Viewer.choiceRating();
				this.encomendas.avancaEstado(enc.getValue());
				this.transportadoras.rateTransp(enc.getValue().getCodT(), rt);
				this.encomendas.avancaEstado(enc.getValue());
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
		List<Encomenda> list = new ArrayList<Encomenda>(tree.values());
		return list;
	}
	
	/**Método que lista os 10 utilizadores que mais usaram a app*/
	public List<String> showUtMaisUtiliza()
	{
		List<String> lu = new ArrayList<String>();
		List<Integer> encs = new ArrayList<Integer>();
		List<String> utf = new  ArrayList<String>();
		for(Encomenda enc: encomendas.getEncomendas().values())// para todas as encomendas
		{
			int i=0;
			boolean encontrou = false;
			for(String ut:lu)//para todos os utilizadores no "lu"
			{
				if(enc.getCodUt().compareTo(ut)==0)// se o utilizador foi o ut da encomenda
				{
					encs.set(i, encs.get(i)+1);
					encontrou=true;
				}
				i++;
			}
			if(!encontrou)//se nao encontrou o user
			{
				lu.add(enc.getCodUt());
				encs.add(1);
			}
		}// a este ponto ja temos um parelelo de nr encomendas e utilizadores( cada par ocupa o mesmo indice nas listas respetivas)
		int n =10;
		if(lu.size()<n)
			n=lu.size();
		
		for(int i=0;i<n;i++)
		{
			int ix=getMax(encs);
			utf.add(lu.get(ix));
			lu.remove(ix);
			encs.remove(ix);
		}
		return utf;
	}
	
	/**Método que lista as 10 empresas transportadoras que mais kms fizeram*/
	public List<String> showEmpMaisKms()
	{
		List<String> transp = new ArrayList<String>();
		List<Integer> encs = new ArrayList<Integer>();
		List<String> tmu = new  ArrayList<String>();
		for(Encomenda enc: encomendas.getEncomendas().values())// para todas as encomendas
		{
			int i=0;
			boolean encontrou = false;
			for(String emp:transp)//para todos os utilizadores no "lu"
			{
				if(enc.getCodT().compareTo(emp)==0 && (this.transportadoras.getTransp().get(enc.getCodT()) instanceof EmpresaT) )// se transportou uma enc e se � de facto uma empresa( e nao um voluntario)
				{
					encs.set(i, encs.get(i)+1);
					encontrou=true;
				}
				i++;
			}
			if(!encontrou)//se nao encontrou o user
			{
				transp.add(enc.getCodT());
				encs.add(1);
			}
		}// a este poznto ja temos um parelelo de nr encomendas e utilizadores( cada par ocupa o mesmo indice nas listas respetivas)
		int n =10;
		if(transp.size()<n)
			n=transp.size();
		
		for(int i=0;i<n;i++)
		{
			int ix=getMax(encs);
			tmu.add(transp.get(ix));
			transp.remove(ix);
			encs.remove(ix);
		}
		return tmu;
	}
	// aux
	public Integer getMax(List<Integer> lista)
	{
		int max=0;
		int indice=0;
		int i=0;
		for(Integer n:lista)
		{
			if(n>max)
			{
				max=n;
				indice=i;
			}
			i++;
		}
		return indice;
	}
	
	
	//--------- Voluntario---------
	/**Método que faz com que um voluntário aceite a encomenda*/
	public void AceitarEnc()
	{
		for(Entry<String, Encomenda> enc: this.encomendas.getEncomendas().entrySet())// para todas as encomendas por avaliar pergunta se quer avaliar
		{
			if(enc.getValue().getCodT().compareTo(this.currentVol.getCodigo()) == 0 && (enc.getValue().getEstado()==0) )
			{

				Viewer.prints("Quer aceitar a encomenda :" + enc.toString() );
            	String rt=Viewer.choiceS();
            	if(rt.compareTo("S")==0)
            	{
            		this.encomendas.avancaEstado(enc.getValue());
            	} 
            	else if(rt.compareTo("N")==0) 
            	{
            		this.encomendas.recusarEnc(enc.getValue());
              	}
            	
			}
		}
	}
	
	//--------- Voluntario // EmpresaT---------
	/**Método que faz com que um voluntário ou uma empresa transportadora envie uma encomenda
	 * @param int Um inteiro, que se este for 0, estamos a trabalhar com voluntários, e se for 1, estamos a trabalhar com empresas transportadoras*/
	public void enviarEnc(int c) {
		String cod;
		if(c==0)
		{
			cod = this.currentVol.getCodigo();
		}
		else
		{
			cod = this.currentEmp.getCodigo();
		}
		
		for(Entry<String, Encomenda> enc: this.encomendas.getEncomendas().entrySet())// para todas as encomendas por avaliar pergunta se quer avaliar
		{
			if( enc.getValue().getCodT().compareTo(cod) == 0 && (enc.getValue().getEstado()==2) )
			{
				Viewer.prints("Quer enviar a encomenda :" + enc.toString() );
            	String rt=Viewer.choiceS();
            	if(rt.compareTo("S")==0);
            	{
            		this.encomendas.avancaEstado(enc.getValue());
            	}
			}
		}
	}
	
	
	//--------- EmpresaT---------
	/**Método que determina o total faturado por uma determinha empresa transportadora num determinado período de tempo
	 * @param CodEmo Um código de empresa
     * @param dStrt Uma data inicial 
     * @param dEnd Uma data final*/
	public long faturadoEnc(String CodEmp, Date dStrt, Date dEnd)
	{
		EmpresaT emp = this.transportadoras.getEmpById(CodEmp);
		Map<String, Encomenda> encs = this.encomendas.getEncomendas();
		long total=0;
		for(Map.Entry<String, Encomenda> entry : encs.entrySet())
		{
			if(entry.getValue().getCodT().compareTo(emp.getCodigo())==0)
			{
				if(entry.getValue().getDate().after(dStrt) && entry.getValue().getDate().after(dEnd))			
				{
					Utilizador ut= this.users.getUtilizadores().get(entry.getValue().getCodUt());
					Loja lj = this.lojas.getLojas().get(entry.getValue().getCodL());
					total+=emp.detPreco(ut.getGpsx(), ut.getGpsy(), lj.getGpsx(), lj.getGpsy(),entry.getValue().getPeso());
				}
			}
				
		}
		return total;
	}
	
	//--------- Loja ---------
	/**Método que prepara uma encomenda para ser entrega por parte de uma loja*/
	public void preparaEnc()
	{
		for(Entry<String, Encomenda> enc: this.encomendas.getEncomendas().entrySet())// para todas as encomendas por avaliar pergunta se quer avaliar
		{
			if(enc.getValue().getCodL().compareTo(this.currentLoja.getCodigoL()) == 0 && (enc.getValue().getEstado()==1) )
			{

				Viewer.prints("Quer enviar a encomenda :" + enc.toString() );
            	String rt=Viewer.choiceS();
            	if(rt.compareTo("S")==0);
            	{
            		this.encomendas.avancaEstado(enc.getValue());
            	}
			}
		}
	}
	
    public void aceitaEnc(String cod) {
        
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
	
	public void setToAberto(String e)
	{
	   this.encomendas.setToAberto(e);
	}
	
	public void avancaEstado(String e)
	{
		this.encomendas.avancaEstado(e);
	}
	   
	public void recusarEnc(String e)
	{
		this.encomendas.recusarEnc(e);
	}
	
	//--------- Estado ---------
	
	public void savestate()
	{

	    int i;
	    File out = new File("SS");

	    ObjectOutputStream output = null;

	    try{
	        output = new ObjectOutputStream(new FileOutputStream("SS"));
	            output.writeObject(this);

	    }catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (output != null) {
	                output.flush();
	                output.close();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	
}
