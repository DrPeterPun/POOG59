package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Model.*;
import Reader.Parser;
import Teste.Teste;
import src.Encomendas;
import src.Lojas;
import src.Transportadoras;
import src.Utilizadores;

public class Controlador implements Serializable{
	private Model modelo;
	
	public Controlador()
	{
		// pass = 123 email = userid@user
		Utilizadores utilizadores = new Utilizadores();
		//this.empresasT = new EmpresasT();
		Encomendas encomendas = new Encomendas();
		Lojas lojas = new Lojas();
		//this.voluntarios = new Voluntarios();
		Transportadoras transp = new Transportadoras();
		
		Parser parser = new Parser(utilizadores,encomendas,lojas, transp);
		parser.parse();
		this.modelo = new Model(utilizadores,transp, lojas,encomendas);
	}
	
	public void saveToFile (String file) throws IOException
    {
    	FileOutputStream f = new FileOutputStream(new File(file));
		ObjectOutputStream o = new ObjectOutputStream(f);

		// Write objects to file
		o.writeObject(this.modelo);
		f.close();
		o.close();
    }
	
	public void loadFromFile (String file) throws IOException, ClassNotFoundException
	{
		FileInputStream fi = new FileInputStream(new File(file));
		ObjectInputStream oi = new ObjectInputStream(fi);

		this.modelo = (Model) oi.readObject();
		oi.close();
		fi.close();
	}
}
