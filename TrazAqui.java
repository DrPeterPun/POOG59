import java.util.ArrayList;
import java.util.Scanner;

public class TrazAqui {
    public static void main(String[] args) {
    /*    System.out.println("Bem-vindo ao Traz-Aqui");
        System.out.println("Deseja \n (1)Registar-se \n (2)Fazer login");
        int c;
        Scanner entrada = new Scanner(System.in);
        c= entrada.nextInt();
        switch(c){
            case 1: 
                System.out.println("Novo Registo:");
                Scanner a = new Scanner(System.in);
                System.out.println("Enter username");

                String userName = a.nextLine();
                System.out.println("Username is: " + userName);
                
                // acxo que nao é preciso definir scanner mais vezes e se pode usar sempre o mesmo mas wtv
                Scanner b = new Scanner(System.in);
                System.out.println("Enter password");

                String password = b.nextLine();
                System.out.println("Password is: " + password);
                
            case 2:

                Scanner d = new Scanner(System.in);
                System.out.println("Enter username");
                

                String userName2 = d.nextLine();
                System.out.println("Username is: " + userName2);
                // verificar se existe o usernadme?

                Scanner e = new Scanner(System.in);
                System.out.println("Enter password");
                // verificar se a pass esta correta?
                //...
                
            default:
                System.out.println("Invalid");
                break;        
        }   */
        Utilizadores listaV = new Utilizadores();
        // criacao de voluntarios individuais e insercao em "Voluntarios" 

        Utilizador w = new Utilizador("1","catarina","skjne","kknjej",0,0);
        Utilizador g = new Utilizador("2","Pedro","dfggrr","kijsbehe",4,5);
        Utilizador l = new Utilizador("3","Joao","jfrnfir","dlkmfkn",7,8);

        listaV.addUtilizadores(w);
        listaV.addUtilizadores(g);
        listaV.addUtilizadores(l);

       // System.out.println("Lista:" + listaV);

        Scanner a = new Scanner(System.in);
        System.out.println("Enter username");
        String userName = a.nextLine();

        Scanner b = new Scanner(System.in);
        System.out.println("Enter password");
        String password = b.nextLine();

        if(!(listaV.loginUV(userName,password)))
        System.out.println("email ou pass incorretos");
        else System.out.println("bem-vindo");



        // criacao de uma encomenda
        Encomenda enc = new Encomenda();

        // criacao da lista de lojas
        Lojas lojas = new Lojas();

        // criacao da lista de utilizadores
        Utilizadores util = new Utilizadores();

        // criaccao da lista de empresas
        EmpresasT transp = new EmpresasT();
    }
}
