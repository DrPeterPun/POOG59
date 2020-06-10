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
		this.currentUser = new Utilizador();
		this.currentEmp = new EmpresaT();
		this.currentVol = new Voluntario();
		this.currentLoja = new Loja();
	}
	
	public boolean logInUser(String email, String pass)
	{
		if(this.users.logIn(email, pass))
		{
			this.logOut();
			try
			{
				this.currentUser = this.users.getUser(email).get();
			}
			catch(NoSuchElementException e)
			{
				//chama a viewr para dizer uq nao existe esse login
			}
			
			return true;
		}
		return false;
	}
	
	public boolean logInVol(String email, String pass)
	{
		if(this.transportadoras.logIn(email, pass))
		{
			this.logOut();
			try
			{
				this.currentVol = this.transportadoras.getVol(email).get();
			}
			catch(NoSuchElementException e)
			{
				//chama a viewr para dizer uq nao existe esse login
			}
			
			return true;
		}
		return false;
	}
	
	public boolean logInEmp(String email, String pass)
	{
		if(this.transportadoras.logIn(email, pass))
		{
			this.logOut();
			
			try
			{
				this.currentEmp = this.transportadoras.getEmp(email).get();
			}
			catch(NoSuchElementException e)
			{
				//chama a viewr para dizer uq nao existe esse login
			}
			return true;
		}
		return false;
	}
	
	public boolean logInLoja(String email, String pass)
	{
		if(this.lojas.logIn(email, pass))
		{
			this.logOut();
			
			try
			{
				this.currentLoja = this.lojas.getLoja(email).get();
			}
			catch(NoSuchElementException e)
			{
				//chama a viewr para dizer uq nao existe esse login
			}
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
	
	//Método que lista as lojas que se encontram a um determinado range do utilizador
	public List<String> getLojasMPerto (double range,Utilizador a) {
		List<String> b = new ArrayList<>();
		this.lojas.getLojas().values().stream().filter(x-> (x.detDistL(a.getGpsx(), a.getGpsy())<=range)).forEach(x-> b.add(x.getNomeL()));
		return b;
	}
	
	public List<String> getTodasLojas(){
		List<String> a = new ArrayList<>();
		this.lojas.getLojas().values().forEach(x->a.add(x.getNomeL()));
		return a;
	}
	
	
	public Loja getLojaPN(String nome) {
		return this.lojas.procurarLojaNome(nome);
	}
	
	
	public Map<String,Double> getTranspMP(double limit, Loja a){
		Double ux= this.currentUser.getGpsx();
		Double uy= this.currentUser.getGpsy();
		Double lx= a.getGpsx();
		Double ly= a.getGpsy();
		Map<String,Double> b = new TreeMap<>();
		this.empresas.getValues().stream().filter(x-> (x.detDist(ux, uy, lx, ly)<=limit)).forEach(x->b.put(x.getNome(),x.detPreco(ux, uy, lx, ly)));
		return b;
		}
	
	public Map<String,Double> getTransp(Loja a){
		Map<String,Double> b = new TreeMap<>();
		Double ux= this.currentUser.getGpsx();
		Double uy= this.currentUser.getGpsy();
		Double lx= a.getGpsx();
		Double ly= a.getGpsy();
		this.empresas.getValues().forEach(x-> b.put(x.getNome(),x.detPreco(ux, uy, lx, ly)));
		return b;
	}
	
	public String getVoluntarioMP(Loja a) {
		Double ux= this.currentUser.getGpsx();
		Double uy= this.currentUser.getGpsy();
		Double lx= a.getGpsx();
		Double ly= a.getGpsy();
		String s=" ";
		Double menor=50.2;
		for(Voluntario b: this.volts.getValues()) {
			if(b.detDist(ux,uy,lx,ly)<menor) {
				menor=b.detDist(ux,uy,lx,ly);
				s=b.getNome();
			}
		}
		return s;
		
	}
	
	public String getTranspPN(String nome) {
		String s = "";
		for(EmpresaT a: this.empresas.getValues()) {
			if(a.getNome().equals(nome)) s= a.getCodigo();
		}
		return s;
	}
	
	public boolean fazEncomenda(Encomenda enc)	
	{
		enc.calculaPeso();
	    boolean done =  false; 
		boolean querOutra = false;
		while(!done)
		{
			//perguntar a view se quer prioritizar por mais barato ou mais perto e wtv
			Viewer.prints("Deseja priorizar por: \n (1)Mais bataro \n (2)Mais perto \n  " );
			int pol=Viewer.choiceI(); // maisperto =0; mais barato =1
			Loja loja = this.lojas.getLoja(enc.getCodL()).get();
			
			Utilizador user = this.currentUser;
			TransporteInterface transportador;
			if(pol==0) //determina que transportadora � proposta ao user
			{
				transportador = this.transportadoras.detMaisPerto(user.getGpsx(),user.getGpsy(),loja.getGpsx(),loja.getGpsy());
			}
			else
			{
				transportador = this.transportadoras.detMaisBarato(user.getGpsx(),user.getGpsy(),loja.getGpsx(),loja.getGpsy());	
			}
		
			if (transportador instanceof EmpresaT)
			{
				//Pergunta ao user se esta contente com a escolhe, se estiver done=1 se nao nao faz nada e volta ao inicio
				Viewer.prints("Deseja prosseguir com a encomenda? S/N");
				String escolha=Viewer.choiceS();
				if(escolha.equals("S"))//confirmar a escolhe, //se nao confir mar volta ao inicio
				{
					enc.setCodT(transportador.getCodigo()); // encomenda � feita para uma empesa
					this.encomendas.addEncomenda(enc);
					this.encomendas.setToAberto(enc);
					this.encomendas.avancaEstado(enc);// avanca logo para ceite pq as empresas nao podem recusar
					done = true;
				}
				else 
				{
					//cena a perguntar se quer outra transportadora
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
	
	// funcao que permite ao utilizador atual avaliar as seuas encomenda spor avaliar
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
	public void enviarEnc(int c) // 0 para voluntario; 1 para empresa
	{
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
					total+=emp.detPreco(ut.getGpsx(), ut.getGpsy(), lj.getGpsx(), lj.getGpsy());
				}
			}
				
		}
		return total;
	}
	
	//--------- Loja ---------
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
