package Teste;

import src.*;
import Reader.*;
import Model.*;


public class Teste {
	public static void main(String[] args) {
		// pass = 123 email = userid@user
		Utilizadores utilizadores = new Utilizadores();
		//this.empresasT = new EmpresasT();
		Encomendas encomendas = new Encomendas();
		Lojas lojas = new Lojas();
		//this.voluntarios = new Voluntarios();
		Transportadoras transp = new Transportadoras();
		
		
		Parser parser = new Parser(utilizadores,encomendas,lojas, transp);
		parser.parse();
		Model modelo = new Model(utilizadores,transp, lojas,encomendas);
		
		modelo.logInUser("u7user","123");
		
		//modelo.printutilizadores();
		
		Encomenda enc = new Encomenda("enc7","u7","l29",2d);
		modelo.fazEncomenda(enc);
		modelo.printEncs("");
		modelo.avancaEstado(enc);
		modelo.printEncs("");
		//modelo.printUser();
		
		
	}	
}
