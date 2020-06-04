package src;

import java.io.IOException;
import java.util.ArrayList;

import Model.Model;
import Viewer.Input;
import Viewer.Viewer;

public class Controller {
    
    private Model app;
    
    public void menuInit() {
       Viewer.prints("********Bem-vindo********");
       this.app=new Model();
       menuEscolha();
    }
    
    public void menuEscolha (){
        Viewer.menuPrincipal();
        int choice= Input.lerInt();
        while (choice!=5) {
            if (choice == 1)
                menuUser();
            else if (choice == 2) menuVol();
            else if (choice == 3) menuTransp();
            else if (choice == 4) menuLoja();
            else {
                choice = Input.lerInt();
            }
        }
        Viewer.prints("Volte sempre \n");
        try{ Runtime.getRuntime().exec("taskkill /F /IM process.exe");}
        catch (IOException e) {
            Viewer.prints("");
        }
    }
    
    public void menuUser() {
        Viewer.MenuRL();
        int choice= Input.lerInt();
        while (choice!=3) {
            if (choice == 1) {
                Viewer.prints("Digite um email \n");
                String email=Input.lerString();
                if(app.getUsers().existeUser(email)) { //acho q esta linha aqui não respeita o encapsulamento
                	Viewer.prints("Email já usado \n");
                	menuUser();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Input.lerString();
                    Viewer.prints("Digite o seu nome \n");
                    String name=Input.lerString();
                    Viewer.prints("Digite um nickname \n");
                    String nickname=Input.lerString();
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Input.lerDouble();
                    Double y=Input.lerDouble();;
                    app.RegistaUser(new Utilizador(nickname,name,x,y,email,pass));
                    Viewer.prints("Registado com sucesso \n");
                    menuUser();
                }
            }
            else if (choice == 2) { 
            	Viewer.prints("Email: \n");
                String email=Input.lerString();
                Viewer.prints("Pass \n");
                String pass=Input.lerString();
                //app.logInUser(email, pass);
                menuAppUser(email);
                }
            else if (choice == 3) menuEscolha();
            else {
                choice = Input.lerInt();
            }
        }
        Viewer.prints("Opção inválida \n");
    }
    
    public void menuAppUser(String email) {
    	Viewer.printMenuUser();
    	 int choice= Input.lerInt();
         while (choice!=3) {
             if (choice == 1) app.printEncs(app.getUsers().getCodigo(email));//acho q esta linha aqui não respeita o encapsulamento
             else if (choice == 2) menuFazerEncomenda(email);
             else if (choice == 3) menuEscolha();
             else {
                 choice = Input.lerInt();
             }
         }
         Viewer.prints("Opção inválida \n");
    }
    
    public void menuFazerEncomenda(String email){
    	Viewer.prints("Deseja escolher Loja por raio de distância? S/N \n");
    	String choice= Input.lerString();
    	if(choice.equals('S')) {
    	Viewer.prints("Qual é o raio que deseja? \n");
    	double raio= Input.lerInt();
    	lojasMP(raio,email);//mostra a lista das lojas mais perto
    	}
    	else {todasLojas();} //mostra a lista de lojas todas
    	Viewer.prints("Escolha a loja que deseja(nome) \n");
    	String loja= Input.lerString();
    	Viewer.prints("Modo de envio: \n");
    	//Função que apresenta a tranportadora mais perto da loja e apresenta o preço
    	Viewer.printMenuAceitar();
    	int escolha= Input.lerInt();
    	while (escolha!=3) {
            if (escolha == 1); //função que imprime as encomendas da transportadora
            else if (escolha == 2) ;//função que aceita a encomenda e adiciona ao respetivo registo de encomenda
            else if (escolha == 3) {
            	Viewer.prints("Modo de envio: \n");
            	//função que determina o voluntário mais perto da loja que escolheu
            	Viewer.prints("Deseja ver o histórico do voluntário? S/N \n");
            	String aceita= Input.lerString();
            	if(aceita.equals('S')) {;} //função que mostra o histórico do voluntário e adiciona a encomenda no respetivo registo de encomenda
            	else {;} //função que adiciona a encomenda no respetivo registo de encomenda
            }
            else {
            	escolha = Input.lerInt();
            }
        }
        Viewer.prints("Opção inválida \n");
    	
    }
    
    public void menuVol() {
        Viewer.MenuRL();
        Viewer.MenuRL();
        int choice= Input.lerInt();
        while (choice!=3) {
            if (choice == 1) {
                Viewer.prints("Digite um email \n");
                String email=Input.lerString();
                if(app.getVolts().existeV(email)) { //acho q esta linha aqui não respeita o encapsulamento, nem sei se valerá a pena ter esta linha visto q a função no model já verifica isso
                	Viewer.prints("Email já usado \n");
                	menuUser();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Input.lerString();
                    Viewer.prints("Digite o seu nome \n");
                    String name=Input.lerString();
                    Viewer.prints("Digite um nickname \n");
                    String nickname=Input.lerString();
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Input.lerDouble();
                    Double y=Input.lerDouble();
                    Viewer.prints("Digite um raio \n");
                    Double raio=Input.lerDouble();
                    app.RegistaVoluntario(new Voluntario(nickname,name,x,y,raio,email,pass));
                }
            }
            else if (choice == 2) { 
            	Viewer.prints("Email: \n");
                String email=Input.lerString();
                Viewer.prints("Pass \n");
                String pass=Input.lerString();
                //app.logInVol(email, pass);
                //menuAppUser(email);
                }
            else if (choice == 3) menuEscolha();
            else {
                choice = Input.lerInt();
            }
        }
        Viewer.prints("Opção inválida");
    }
        
    public void menuAppVol(String email) {
    	Viewer.printMenuVol();
    	 int choice= Input.lerInt();
         while (choice!=3) {
        	 if(choice ==1) app.printEncs(app.getVolts().getCodigoV(email));
         else if (choice == 2) ; //mostrar encomendas pendentes e aceitar encomendas
         else if (choice == 3) menuEscolha();
         else {
             choice = Input.lerInt();
         }
     }
     Viewer.prints("Opção inválida \n");
    }
    
    public void menuTransp() {
        Viewer.MenuRL();
        int choice= Input.lerInt();
        while (choice!=3) {
            if (choice == 1) {
                Viewer.prints("Digite um email \n");
                String email=Input.lerString();
                if(app.getUsers().existeUser(email)) { //acho q esta linha aqui não respeita o encapsulamento
                	Viewer.prints("Email já usado \n");
                	menuTransp();
                }
                else {
                    Viewer.prints("Digite uma pass \n");
                    String pass=Input.lerString();
                    Viewer.prints("Digite o nome da empresa \n");
                    String name=Input.lerString();
                    Viewer.prints("Digite um nickname para a empresa(será o seu código) \n");
                    String nickname=Input.lerString();
                    Viewer.prints("Digite o nif da sua empresa \n");
                    int nif=Input.lerInt();
                    Viewer.prints("Digite as suas coordenada GPS \n");
                    Double x=Input.lerDouble();
                    Double y=Input.lerDouble();
                    Viewer.prints("Digite um raio \n");
                    Double raio=Input.lerDouble();
                    Viewer.prints("Qual o preço que a sua empresa aplica às encomendas? \n");
                    Double preco=Input.lerDouble();
                    //app.RegistaEmpresa(new EmpresaT(nickname,name,nif,raio,preco,x,y,email,pass,new ArrayList<>()));
                    Viewer.prints("Registado com sucesso \n");
                    menuTransp();
                }
            }
            else if (choice == 2) { 
            	Viewer.prints("Email: \n");
                String email=Input.lerString();
                Viewer.prints("Pass \n");
                String pass=Input.lerString();
                //app.logInUser(email, pass);
                menuAppTransp(email);
                }
            else if (choice == 3) menuEscolha();
            else {
                choice = Input.lerInt();
            }
        }
        Viewer.prints("Opção inválida \n");
    }
    
    public void menuAppTransp(String email) {
    	
    }
    
    public void menuLoja() {
        Viewer.MenuRL();
    }
    
    
    public void lojasMP(double raio, String email) {
    	app.getLojasMPerto(raio,app.getUti(email)).forEach(x-> Viewer.prints("Loja: " + x));
    }
    
    public void todasLojas() {
    	app.getTodasLojas().forEach(x-> Viewer.prints("Loja: " + x));
    }
}
