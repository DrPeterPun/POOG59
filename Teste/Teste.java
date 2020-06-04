package Teste;

import src.*;
import Reader.*;
import Controlador.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import Model.*;


public class Teste {
	private static Model modelo;
	
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
		Model modelo2 = new Model(utilizadores,transp, lojas,encomendas);
		modelo = modelo2;
		
		modelo.logInUser("u7user","123");
		
		//modelo.printutilizadores();
		
		Encomenda enc = new Encomenda("e0007","u7","l29",2d, new Date() );
		
		modelo.fazEncomenda(enc);
		modelo.printEncs("u7");
		modelo.avancaEstado(enc);
		modelo.printEncs("u7");
		//modelo.printUser();
		//Controlador ct = new Controlador();
		//ct.saveToFile("testeSave");
		//System.out.println("??");
		//ct.loadFromFile("testeSave");
		//modelo.saveToFile("logSave");
		//System.out.println(modelo.faturadoEnc("t1"));
		System.out.println(modelo.showUtMaisUtiliza());
		System.out.println(modelo.showEmpMaisKms());
	}
	
	public static void saveToFile (String file) throws IOException
	{
	   	FileOutputStream f = new FileOutputStream(new File(file));
		ObjectOutputStream o = new ObjectOutputStream(f);

		// Write objects to file
		o.writeObject(Teste.modelo);
		f.close();
		o.close();
	}
		
		
	public void loadFromFile (String file) throws IOException, ClassNotFoundException
	{
		FileInputStream fi = new FileInputStream(new File(file));
		ObjectInputStream oi = new ObjectInputStream(fi);
		Teste.modelo = (Model) oi.readObject();
		oi.close();
		fi.close();
	}
		
}	
