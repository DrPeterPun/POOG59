package Viewer;

public class Viewer {
     
    public static void  menuPrincipal(){     
        System.out.println("1)Sou um utilizador");
        System.out.println("2)Sou um voluntário");
        System.out.println("3)Sou uma transportadora");
        System.out.println("4)Sou uma loja");
        System.out.println("5)Sair.\n");
        }
    
    public static void MenuRL() {
        System.out.println("1)Registar-me");
        System.out.println("2)Login");
        System.out.println("3)Voltar ao menu");
        
    }
    
    public static void prints(String a){
        System.out.println(a);
    }
    
    public static void printMenuUser(){
    	 System.out.println("1)Ver histórico de encomendas");
         System.out.println("2)Fazer encomenda");
         System.out.println("3)Voltar ao menu");
    	}
    
    public static void printMenuAceitar() {
    	System.out.println("1)Ver histórico de transportadora");
        System.out.println("2)Aceitar transporte");
        System.out.println("3)Recusar transporte");
    }
    
    public static void printMenuVol(){
   	 System.out.println("1)Ver histórico de encomendas");
     System.out.println("2)Ver encomendas pendentes");
     System.out.println("3)Voltar ao menu");
   	}
    
}
