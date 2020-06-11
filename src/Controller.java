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
    
    /**Menu que inicializa a app*/
    public void menuInit() {
    	
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
    
    /**Menu principal*/
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
                    voltarMenu();
                    break;
            case 6: printTopTransp();
                    voltarMenu();
                    break;
            case 7: menuFaturacao();
                    voltarMenu();
                    break;
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
            default: Viewer.prints("Opção inválida \n"); 
           }
        }
        while (choice!=0); 
        
        try{ Runtime.getRuntime().exec("taskkill /F /IM process.exe");}
        catch (IOException e) {
            Viewer.prints("");
        }
    }
    
    /**Método, que escolhido pelo utilizador, volta ao menu principal*/
    public void voltarMenu(){
        Viewer.prints("Y) Voltar para o menu principal.\n");
        String a = Viewer.choiceS();
        if (a.equals("y") || a.equals("Y")){
            menuEscolha();
        }
        else {
            Viewer.prints("Viewer.Input incorreto");
            voltarMenu();
        }
    }
    
    /**Menu auxiliar que ajuda na determinação do valor total faturado de uma determinada empresa transportadora*/
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
    
    /**Menu de um utilizador, onde ele pode fazer logIn, registar-se, fazer encomendas, entre outras coisas*/
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
                if(app.logInUser(email, pass)) menuAppUser();
                else Viewer.prints("Não existe registo");
                }
            case 3:  menuEscolha();
            default: Viewer.prints("Opção inválida \n"); 
            
         }
        } while (choice!=0);
         
     
    }
    
    /**Menu auxiliar do menu de utilizador quando um user faz logIn*/
    public void menuAppUser() {
    	Viewer.printMenuUser();
    	 int choice;
    	 do {choice = Viewer.choiceI();
        switch(choice){
        case 1: app.showEncUser(app.getCurrentUser()); break;
        case 2: menuFazerEncomenda(); break;
        case 3: app.rateTransp(); break;
        case 4: app.logOut(); menuEscolha(); break;
        default: Viewer.prints("Opção inválida \n"); 
         }
    }
    	 while (choice!=0) ;
        
    }
    
    /**Menu auxiliar dos menus do user, que faz encomendas*/
    public void menuFazerEncomenda(){
    	Viewer.prints("Deseja escolher Loja por raio de distância? S/N \n");
    	String choice= Input.lerString();
    	if(choice.equals('S')) {
    	Viewer.prints("Qual é o raio que deseja? \n");
    	double raio= Viewer.choiceD();
    	lojasMP(raio,app.getCurrentUser());
    	}
    	else {todasLojas();} 
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
    	menuUser();
  }
    /**Menu dos voluntários*/
    public void menuVol() {
        Viewer.MenuRL();
        int choice;
        do {choice = Viewer.choiceI();
        switch(choice) {
        case 1: 
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getTransp().existeTtransportadora(email)) { 
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
                    app.RegistaTransportadora(new Voluntario(nickname,name,x,y,raio,email,pass, new ArrayList<>()));
                }
            
        case 2:
            	Viewer.prints("Email: \n");
                String email1=Viewer.choiceS();
                Viewer.prints("Pass \n");
                String pass=Viewer.choiceS();
                if(app.logInVol(email1, pass)) menuAppUser();
                else Viewer.prints("Não existe registo");
                
                
        case 3: menuEscolha();
        default: Viewer.prints("Opção inválida \n"); 
        }
        }
        while (choice!=0);
    }
    
    /**Menu auxiliar do menu de voluntárioss, onde pode ver o seu histórico, aceitar encomendas, enviar encomendas, entre outras opções*/
    public void menuAppVol() {
    	Viewer.printMenuVolTransp();
    	 int choice;
    	 do {choice = Viewer.choiceI();
         switch(choice) {
         case 1: app.printEncs(app.getCurrentVol().getCodigo()); break;
         case 2: app.AceitarEnc(); break;
         case 3: app.enviarEnc(0); break;
         case 4: app.logOut(); menuEscolha(); break;
         default: Viewer.prints("Opção inválida \n"); 
         }
     }while (choice!=0);
    }
    
    /**Menu das empresas transportadoras*/
    public void menuTransp() {
        Viewer.MenuRL();
        int choice;
        do {choice = Viewer.choiceI();
        switch(choice){
        case 1: {
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getUsers().existeUser(email)) { 
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
                    app.RegistaTransportadora(new EmpresaT(nickname,name,nif,raio,preco,x,y,email,pass,new ArrayList<>()));
                    Viewer.prints("Registado com sucesso \n");
                    menuTransp();
                }
            }
        case 2: { 
            	Viewer.prints("Email: \n");
                String email=Viewer.choiceS();
                Viewer.prints("Pass \n");
                String pass=Viewer.choiceS();
                if(app.logInEmp(email, pass)) menuAppUser();
                else Viewer.prints("Não existe registo");
        }
        case 3: menuEscolha();
        default: Viewer.prints("Opção inválida \n"); 
           
        }
      }
        while (choice!=0) ;
    }
    
    /**Menu auxiliar das empresas transportadoras onde podem ver o seu histórico, entre outras opções*/
    public void menuAppTransp() {
    	Viewer.printMenuVolTransp();
    	int choice;
    	do { choice= Viewer.choiceI();
    	switch (choice) {
    	case 1: app.printEncs(app.getCurrentEmp().getCodigo()); break;
    	case 2: app.AceitarEnc(); break;
    	case 3: app.enviarEnc(1); break;
    	case 4: app.logOut(); menuEscolha(); break;
    	default: Viewer.prints("Opção inválida \n"); 
    	}
    	} while(choice!=0);
    }
    
    /**Menu de uma loja*/
    public void menuLoja() {
        Viewer.MenuRL();
        int choice;
        do {choice = Viewer.choiceI();
        switch(choice){
        case 1: {
                Viewer.prints("Digite um email \n");
                String email=Viewer.choiceS();
                if(app.getUsers().existeUser(email)) { 
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
                    menuLoja();
                }
            }
        case 2:{ 
        	Viewer.prints("Email: \n");
            String email=Viewer.choiceS();
            Viewer.prints("Pass \n");
            String pass=Viewer.choiceS();
            if(app.logInLoja(email, pass)) menuAppUser();
            else Viewer.prints("Não existe registo");
            }
    case 3: menuEscolha();
    default: Viewer.prints("Opção inválida \n"); 
    }
        } while(choice!=0);
    }
    
    /**Menu auxiliar do menu de lojas, onde podem ver o seu histórico, preparar encomendas, entre outros*/
    public void menuAppLoja() {
    	Viewer.printMenuLoja();
    	int choice;
    	do { choice= Viewer.choiceI();
    	switch (choice) {
    	case 1: app.printEncs(app.getCurrentLoja().getCodigoL()); break;
    	case 2: app.preparaEnc(); break;
    	case 3: app.logOut(); menuEscolha(); break;
    	default: Viewer.prints("Opção inválida \n"); 
    	}
    	} while(choice!=0);
    }
    
    /**Método que imprime as lojas mais perto de um utilizador
     * @param raio Raio dado pelo useer 
     * @param a Um utilizador*/
    public void lojasMP(double raio, Utilizador a ) {
    	app.getLojasMPerto(raio,app.getCurrentUser()).forEach(x-> Viewer.prints("Loja: " + x));
    }
    
    /**Método que imprime todas as lojas disponiveis na app*/
    public void todasLojas() {
    	app.getTodasLojas().forEach(x-> Viewer.prints("Loja: " + x));
    }
    
    /**Método que imprime os 10 utilizadores que mais utilizaram a app*/
    public void printTopUsers() {
    	app.showUtMaisUtiliza().stream().forEach(x-> Viewer.prints("User: " + x));
    }
    
    /**Método que imprime as 10 empresas transportadoras que mais kms fizeram*/
    public void printTopTransp() {
    	app.showEmpMaisKms().stream().forEach(x-> Viewer.prints("Transportadora: " + x));
    }
    
    /**Método que imprime o total faturado por uma determinada empresa transportadora
     * @param CodEmo Um código de empresa
     * @param dStrt Uma data inicial 
     * @param dEnd Uma data final*/
    public void printTFat(String CodEmp, Date dStrt, Date dEnd) {
    	Viewer.prints("Total faturado: " + app.faturadoEnc(CodEmp, dStrt, dEnd));
    	
    }
    
    /**Método que guarda o estado atual da app num ficheiro
     * @param file Nome do ficheiro*/
    public void saveToFile (String file) throws IOException
    {
        FileOutputStream f = new FileOutputStream(new File(file));
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(this);
        f.close();
        o.close();
    }
        
    /**Método que faz load do estado atual da app
     * @param file Nome do ficheiro*/     
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
