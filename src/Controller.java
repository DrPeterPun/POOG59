package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import Model.Model;
import Reader.Parser;
import Viewer.Input;
import Viewer.Viewer;

public class Controller {
    
    private Model app;
    
    public void menuInit() {
    	// pass = 123 email = "userid"+user para todos os users, para lojas o email � o id+loja; empresas id+emp; vols id+vol
    	Utilizadores utilizadores = new Utilizadores();
    	Encomendas encomendas = new Encomendas();
    	Lojas lojas = new Lojas();
    	Transportadoras transp = new Transportadoras();
    	Parser parser = new Parser(utilizadores,encomendas,lojas, transp);
    	parser.parse();
    	
       Viewer.prints("********Bem-vindo********");
       this.app=new Model(utilizadores,transp, lojas,encomendas);
       menuEscolha();
    }
    
    public void menuEscolha (){
        Viewer.menuPrincipal();
        int choice;
        do{ choice = Viewer.choiceI();
            switch(choice) {
            case 1: menuUser();
            case 2: menuVol();
            case 3: menuTransp();
            case 4: menuLoja();
            case 5: printTopUsers();
            case 6: printTopTransp();
            case 7: menuFaturacao();
            case 8:
                try {
                    saveToFile("logs.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                    Viewer.prints("Não foi possível gravar");
                }
                menuEscolha();
            case 9:
              try {
                  this.app = loadFromFile("logs.txt");
              } catch (IOException e) {
                  Viewer.prints("Não foi possível ler");
              } catch (ClassNotFoundException e) {
                  Viewer.prints("Erro");
              } catch (ClassCastException e) {
                  Viewer.prints("Erro!");
              }
            menuEscolha();;
            case 10:Viewer.prints("Volte sempre \n");
            menuEscolha();
            break;
            default:
           }
        }
        while (choice!=0); 
        
        try{ Runtime.getRuntime().exec("taskkill /F /IM process.exe");}
        catch (IOException e) {
            Viewer.prints("");
        }
    }
    
    public void menuFaturacao() {
    	Viewer.prints("Digite o nome da transportadora que deseja ver. \n");
    	String transp = Viewer.choiceS();
    	Viewer.prints("Digite a data inicial que deseja ver.\n (1)Ano (2)Mês (3)Dia");
    	int anoi = Viewer.choiceI();
    	int mesi = Viewer.choiceI();
    	int diai= Viewer.choiceI();
    	Viewer.prints("Digite a data final que deseja ver.\n (1)Ano (2)Mês (3)Dia");
    	int anof = Viewer.choiceI();
    	int mesf = Viewer.choiceI();
    	int diaf= Viewer.choiceI();
    	@SuppressWarnings("deprecation")
		Date inicial = new Date(anoi,mesi,diai);
    	@SuppressWarnings("deprecation")
		Date f = new Date(anof,mesf,diaf);
    	printTFat(app.getTranspPN(transp),inicial,f);
    	
    }
    
    public void menuUser() {
        Viewer.MenuRL();
        int choice;
        do {
        	choice = Viewer.choiceI();
            switch(choice) {
            case 1: {
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getUsers().existeUser(email)) { //acho q esta linha aqui não respeita o encapsulamento
                	Viewer.prints("Email já usado \n");
                	menuUser();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Viewer.choiceS();
                    Viewer.prints("Digite o seu nome \n");
                    String name=Viewer.choiceS();
                    Viewer.prints("Digite um nickname \n");
                    String nickname=Viewer.choiceS();
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Viewer.choiceD();
                    Double y=Viewer.choiceD();
                    app.RegistaUser(new Utilizador(nickname,name,x,y,email,pass));
                    Viewer.prints("Registado com sucesso \n");
                    menuUser();
                }
            }
            case 2: { 
            	Viewer.prints("Email: \n");
                String email=Viewer.choiceS();
                Viewer.prints("Pass \n");
                String pass=Viewer.choiceS();
                app.logInUser(email, pass);
                menuAppUser();
                }
            case 3:  menuEscolha();
            
         }
        } while (choice!=0);
        //Viewer.prints("Opção inválida \n"); 
     
    }
    
    public void menuAppUser() {
    	Viewer.printMenuUser();
    	 int choice;
    	 do {choice = Viewer.choiceI();
        switch(choice){
        case 1: app.showEncUser(app.getCurrentUser());
        case 2: menuFazerEncomenda();
        case 3: app.rateTransp();
        case 4: app.logOut();
        case 5: menuEscolha();
         }
    }
    	 while (choice!=3) ;
         //Viewer.prints("Opção inválida \n");
    }
    
    public void menuFazerEncomenda(){
    	Viewer.prints("Deseja escolher Loja por raio de distância? S/N \n");
    	String choice= Input.lerString();
    	if(choice.equals('S')) {
    	Viewer.prints("Qual é o raio que deseja? \n");
    	double raio= Viewer.choiceD();
    	lojasMP(raio,app.getCurrentUser());//mostra a lista das lojas mais perto
    	}
    	else {todasLojas();} //mostra a lista de lojas todas
    	Viewer.prints("Escolha a loja que deseja(nome) \n");
    	String loja= Viewer.choiceS();
    	Encomenda a = new Encomenda(app.getCurrentUser().getIdUser(),app.getLojaPN(loja).getCodigoL());
    	boolean n = true;
    	while (n) {
    		Viewer.prints("Qual o código de produto que deseja comprar? \n");
        	String cod= Viewer.choiceS();
        	Viewer.prints("Qual a sua descrição? \n");
        	String des= Viewer.choiceS();
        	Viewer.prints("Que quantidade deseja comprar? \n");
        	Double qnt= Viewer.choiceD();
        	Viewer.prints("Qual o seu valor? \n");
        	Double valor= Viewer.choiceD();
        	LinhadeEncomenda lenc = new LinhadeEncomenda(cod,des,qnt,valor);
        	a.addLinhaEncomenda(lenc);
        	Viewer.prints("Quer encomendar mais alguma coisa? S/N");
        	String enc=Viewer.choiceS();
        	if(enc.equals("S")) n= true;
        	else n=false;
    	}
    	app.fazEncomenda(a);
  }
    
    public void menuVol() {
        Viewer.MenuRL();
        Viewer.MenuRL();
        int choice;
        do {choice = Viewer.choiceI();
        switch(choice) {
        case 1: {
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getTransp().getVol(email)!=null) { //acho q esta linha aqui não respeita o encapsulamento, nem sei se valerá a pena ter esta linha visto q a função no model já verifica isso
                	Viewer.prints("Email já usado \n");
                	menuUser();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Viewer.choiceS();
                    Viewer.prints("Digite o seu nome \n");
                    String name=Viewer.choiceS();
                    Viewer.prints("Digite um nickname \n");
                    String nickname=Viewer.choiceS();;
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Viewer.choiceD();
                    Double y=Viewer.choiceD();
                    Viewer.prints("Digite um raio \n");
                    Double raio=Viewer.choiceD();
                    app.RegistaVoluntario(new Voluntario(nickname,name,x,y,raio,email,pass));
                }
            }
        case 2:{ 
            	Viewer.prints("Email: \n");
                String email=Viewer.choiceS();
                Viewer.prints("Pass \n");
                String pass=Viewer.choiceS();
                app.logInVol(email, pass);
                menuAppVol();
                }
        case 3: menuEscolha();
        }
        }
        while (choice!=0);
       // Viewer.prints("Opção inválida");
    }
        
    public void menuAppVol() {
    	Viewer.printMenuVolTransp();
    	 int choice;
    	 do {choice = Viewer.choiceI();
         switch(choice) {
         case 1: app.printEncs(app.getCurrentVol().getCodigo());// imprimir as encomendas
         case 2: app.preparaAceitarEnc(); 
         case 3: app.enviarEnc(0);
         case 4: app.logOut();
         case 5: menuEscolha();
         }
     }while (choice!=0);
     //Viewer.prints("Opção inválida \n");
    }
    
    public void menuTransp() {
        Viewer.MenuRL();
        int choice;
        do {choice = Viewer.choiceI();
        switch(choice){
        case 1: {
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getUsers().existeUser(email)) { //acho q esta linha aqui não respeita o encapsulamento
                	Viewer.prints("Email já usado \n");
                	menuTransp();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Viewer.choiceS();
                    Viewer.prints("Digite o nome da empresa \n");
                    String name=Viewer.choiceS();
                    Viewer.prints("Digite um nickname para a empresa(será o seu código) \n");
                    String nickname=Viewer.choiceS();
                    Viewer.prints("Digite o nif da sua empresa \n");
                    int nif=Viewer.choiceI();
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Viewer.choiceD();
                    Double y=Viewer.choiceD();
                    Viewer.prints("Digite um raio \n");
                    Double raio=Viewer.choiceD();
                    Viewer.prints("Qual o preço que a sua empresa aplica às encomendas? \n");
                    Double preco=Viewer.choiceD();
                    app.RegistaEmpresa(new EmpresaT(nickname,name,nif,raio,preco,x,y,email,pass,new ArrayList<>()));
                    Viewer.prints("Registado com sucesso \n");
                    menuTransp();
                }
            }
        case 2: { 
            	Viewer.prints("Email: \n");
                String email=Viewer.choiceS();
                Viewer.prints("Pass \n");
                String pass=Viewer.choiceS();
                app.logInEmp(email, pass);
                menuAppTransp();
                }
        case 3: menuEscolha();
           
        }
      }
        while (choice!=0) ;
        //Viewer.prints("Opção inválida \n");
    }
    
    public void menuAppTransp() {
    	Viewer.printMenuVolTransp();
    	int choice;
    	do { choice= Viewer.choiceI();
    	switch (choice) {
    	case 1: app.printEncs(app.getCurrentEmp().getCodigo());//ver histórico de encomendas das transportadoras
    	case 2: app.preparaAceitarEnc();//ver encomendas pendentes e aceita las se quiser
    	case 3: app.enviarEnc(1);
    	case 4: app.logOut();
    	case 5: menuEscolha();
    	}
    	} while(choice!=0);
    	//Viewer.prints("Opção inválida \n");
    }
    
    public void menuLoja() {
        Viewer.MenuRL();
        int choice;
        do {choice = Viewer.choiceI();
        switch(choice){
        case 1: {
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getUsers().existeUser(email)) { //acho q esta linha aqui não respeita o encapsulamento
                	Viewer.prints("Email já usado \n");
                	menuTransp();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Viewer.choiceS();
                    Viewer.prints("Digite o nome da loja \n");
                    String name=Viewer.choiceS();
                    Viewer.prints("Digite um nickname para a loja(será o seu código) \n");
                    String nickname=Viewer.choiceS();
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Viewer.choiceD();
                    Double y=Viewer.choiceD();
                    app.RegistaLoja(new Loja(nickname,name,email,pass,x,y));
                    Viewer.prints("Registado com sucesso \n");
                    menuTransp();
                }
            }
        case 2:{ 
        	Viewer.prints("Email: \n");
            String email=Viewer.choiceS();
            Viewer.prints("Pass \n");
            String pass=Viewer.choiceS();
            app.logInLoja(email, pass);
            menuAppLoja();
            }
    case 3: menuEscolha();
    }
        } while(choice!=0);
       // Viewer.prints("Opção inválida \n");
    }
    
    public void menuAppLoja() {
    	Viewer.printMenuLoja();
    	int choice;
    	do { choice= Viewer.choiceI();
    	switch (choice) {
    	case 1: app.printEncs(app.getCurrentLoja().getCodigoL());;//ver histórico de encomendas das lojas
    	case 2: app.preparaEnc();;//ver encomendas pendentes e aceita las se quiser
    	case 4: app.logOut();
    	case 5: menuEscolha();
    	}
    	} while(choice!=0);
    	//Viewer.prints("Opção inválida \n");
    }
    
    public void lojasMP(double raio, Utilizador a ) {
    	app.getLojasMPerto(raio,app.getCurrentUser()).forEach(x-> Viewer.prints("Loja: " + x));
    }
    
    public void todasLojas() {
    	app.getTodasLojas().forEach(x-> Viewer.prints("Loja: " + x));
    }
    
    /*public void transpMP(double limit, Loja a) {
    	Map<String,Double> b = app.getTranspMP(limit, a);
    	for(String s: b.keySet()) {
    		for(Double d: b.values()) {
    			Viewer.prints("Transportadora: " + s + "Preço: " + d);
    		}
    	}
    }
    
    public void todasTransp(Loja a) {
    	Map<String,Double> b = app.getTransp(a);
    	for(String s: b.keySet()) {
    		for(Double d: b.values()) {
    			Viewer.prints("Transportadora: " + s + "Preço: " + d);
    		}
    	}
    }
    */
    public void printTopUsers() {
    	app.showUtMaisUtiliza().stream().forEach(x-> Viewer.prints("User: " + x));
    }
    
    public void printTopTransp() {
    	app.showEmpMaisKms().stream().forEach(x-> Viewer.prints("Transportadora: " + x));
    }
    
    public void printTFat(String CodEmp, Date dStrt, Date dEnd) {
    	Viewer.prints("Total faturado: " + app.faturadoEnc(CodEmp, dStrt, dEnd));
    	
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
        
        
    public Model loadFromFile (String file) throws IOException, ClassNotFoundException, ClassCastException
    {
        FileInputStream fi = new FileInputStream(new File(file));
        ObjectInputStream oi = new ObjectInputStream(fi);
        
        Model modelo = (Model) oi.readObject();
        oi.close();
        fi.close();
        return modelo;
    }
}
