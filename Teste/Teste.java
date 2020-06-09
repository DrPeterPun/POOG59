package Teste;

import src.*;
import Reader.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import Model.*;


public class Teste {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
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

		
		//modelo.logInUser("u7user","123");
		
		//modelo.printutilizadores();
		
		Encomenda enc = new Encomenda("e0007","u7","l29",2d, new Date() );
		Encomenda enc2 = new Encomenda("e0008","u7","l29",2d, new Date() );
		
		modelo.fazEncomenda(enc);
		modelo.printEncs("");
		modelo.avancaEstado(enc);
		modelo.printEncs("");
		//modelo.printUser();
		//Controlador ct = new Controlador();
		//ct.saveToFile("testeSave");
		//System.out.println("??");
		//ct.loadFromFile("testeSave");
		//modelo.saveToFile("logSave");
		//System.out.println(modelo.faturadoEnc("t1"));
		//System.out.println(modelo.showUtMaisUtiliza());
		//System.out.println(modelo.showEmpMaisKms());
		modelo.saveToFile("logfile");
		
		modelo.printEncs("");
		modelo.avancaEstado(enc);
		modelo.printEncs("");
		modelo.fazEncomenda(enc2);
		modelo.printEncs("");
		//////////////////////////////////////////
		modelo=modelo.loadFromFile("logfile");
		
		modelo.printEncs("");
	}
	
		
}	
