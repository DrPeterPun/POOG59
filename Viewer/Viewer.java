package Viewer;

public class Viewer {
     
	public static void  menuPrincipal(){     
        System.out.println("1)Sou um utilizador");
        System.out.println("2)Sou um voluntário");
        System.out.println("3)Sou uma transportadora");
        System.out.println("4)Sou uma loja");
        System.out.println("5)Top 10 utilizadores que mais usam a app");
        System.out.println("6)Top 10 transportadoras com mais KM's");
        System.out.println("7)Ver total faturado de uma determinada empresa");
        System.out.println("8)Gravar estado");
        System.out.println("9)Carregar estado");
        System.out.println("10)Sair.\n");
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
    
    public static void printMenuVolTransp(){
   	 System.out.println("1)Ver histórico de encomendas");
     System.out.println("2)Ver encomendas pendentes");
     System.out.println("3)Voltar ao menu");
   	}
    
    
    public static String choiceS() {
    	String s = Input.lerString();
    	return s;
    }
    
    public static int choiceI() {
    	int i = Input.lerInt();
    	return i;
    }
    
    public static Double choiceD() {
    	Double d = Input.lerDouble();
    	return d;
    }
    
    public static int choiceRating() {
    	int i = Input.lerRating();
    	return i;
    }
    
}
