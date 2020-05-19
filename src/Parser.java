package src;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
	private Utilizadores utilizadores;
	private EmpresasT empresasT;
	private Encomendas encomendas;
	private Lojas lojas;
	private Voluntarios voluntarios;
	
	public Parser() {
		this.utilizadores = new Utilizadores();
		this.empresasT = new EmpresasT();
		this.encomendas = new Encomendas();
		this.lojas = new Lojas();
		this.voluntarios = new Voluntarios();
	}
		
	public void parse() {
		int ui,vi,ei,li,enci,aceitei;
		ui=vi=ei=li=enci=aceitei=0;
		
		sortAll(".");
		List<String> linhas = lerFicheiro("log.txt"); // path para o ficheiro
		
		System.out.println("\n foram lidas: " + linhas.size() + " linhas \n");
		String[] linhaPartida;
		for(String linha : linhas) {
			linhaPartida = linha.split(":",2);
			switch(linhaPartida[0]) {
			case "Utilizador":
				Utilizador u=parseUtilizador(linhaPartida[1]);
				this.utilizadores.addUtilizador(u);
				ui++;
				break;
			case "Voluntario":
				Voluntario v= parseVoluntario(linhaPartida[1]);
				this.voluntarios.addVoluntario(v);
				vi++;
				break;
			case "Transportadora":
				EmpresaT e= parseEmpresa(linhaPartida[1]);
				this.empresasT.addEmpresa(e);
				ei++;
				break;
			case "Loja":
				Loja l= parseLoja(linhaPartida[1]);
				this.lojas.addLoja(l);
				li++;
				break;
			case "Encomenda":
				Encomenda enc = parseEncomenda(linhaPartida[1]);
				this.encomendas.addEncomenda(enc);
				enci++;
				break;
			case "Aceite":
				String aceite = linhaPartida[1];
				aceitei++;
				break;
			default:
				//o que fazer quando a linha nao  valida, provavelmente nao faz nada
				break;
			}// switch(linhaPartida[0])
		}//for(String linha : linhas) 
		System.out.printf("utilizadores:" + ui +" voluntarios:" + vi + " empresasT:" + ei+ " lojas:" + li +" encomendas:" + enci + " aceites:" + aceitei); 
	}
	

	public List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { System.out.println(exc.getMessage() + "ficheiro nao foi loaded"); }
        return lines;
	}
	
	public Utilizador parseUtilizador(String input){
        String[] campos = input.split(",");
        String nome = campos[0]; 
        String codUtilizador = campos[1];
        double gpsx = Double.parseDouble(campos[2]);
        double gpsy = Double.parseDouble(campos[3]);
        Utilizador u = new Utilizador(nome,codUtilizador,gpsx,gpsy);
        return u;
	}
	
	public Voluntario parseVoluntario(String input) {
		String[] campos = input.split(",");
		String codVol = campos[0];
		String nome = campos[1];
		double gpsx = Double.parseDouble(campos[2]);
		double gpsy = Double.parseDouble(campos[3]);
		double raio = Double.parseDouble(campos[4]);
		
		return new Voluntario(codVol,nome,gpsx,gpsy,raio);
	}
	
	public Encomenda parseEncomenda(String input) {
		String[] campos = input.split(",");
		String codEnc=campos[0];
		String codUt=campos[1];
		String codLoja=campos[2];
		double peso=Double.parseDouble(campos[3]);
		
		Encomenda enc = new Encomenda(codEnc,codUt,codLoja,peso);
		int n;
		n=(campos.length-4)/4;
		for(int i=1; i<n;i++)
		{
			LinhadeEncomenda le = parseLinhaEncomenda(campos[i*4],campos[i*4+1],campos[i*4+2],campos[i*4+3]);
			enc.addLinhaEncomenda(le);
		}
		return enc;
	}
	
	public LinhadeEncomenda parseLinhaEncomenda(String cp, String ds, String qt, String vu) {
		String codProd=cp;
		String descricao=ds;
		double quant=Double.parseDouble(qt);
		double valorUnit=Double.parseDouble(vu);
		return new LinhadeEncomenda(codProd,descricao,quant,valorUnit);
	}
	
	private EmpresaT parseEmpresa(String input) {
		String[] campos = input.split(",");
		String codigoEmpresa=campos[0];
		String nomeEmpresa=campos[1];
		double gpsx=Double.parseDouble(campos[2]);
		double gpsy=Double.parseDouble(campos[3]);
		int nif=Integer.parseInt(campos[4]);
		double raio=Double.parseDouble(campos[5]);
		double precokm=Double.parseDouble(campos[6]);
		
		return new EmpresaT(codigoEmpresa,nomeEmpresa,gpsx,gpsy,nif,raio,precokm);
	}
	
	private Loja parseLoja(String input) {
		String[] campos = input.split(",");
		String id=campos[0];
		String nome=campos[1];
		return new Loja(id,nome);
	}
	
	
	/// TESTE ///
	private static void sortAll(String dirName) {
        File directory = new File(dirName);
        File[] filesArray = directory.listFiles();
        //sort all files
        Arrays.sort(filesArray);
        //print the sorted values
        for (File file : filesArray) {
            if (file.isFile()) {
            	System.out.println("File : " + file.getName());
            } else if (file.isDirectory()) {
            	System.out.println("Directory : " + file.getName());
            } else {
                System.out.println("Unknown : " + file.getName());
            }
        }
    }
}
