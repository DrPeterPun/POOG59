package src;
import java.util.ArrayList;

public class Encomenda {
   private String codEnc;
   private String codUt;
   private String codL;
   private String codT;
   private double peso;
   private ArrayList<LinhadeEncomenda> encomendas;
   private int estado;// -1 = rejeitado -> 0,1,2,3,4 = Aberta,Aceite,Pronta,PorAvaliar,Completa

   public Encomenda(){
      this.codEnc=" ";
      this.codUt=" ";
      this.codL=" ";
      this.codT="";
      this.peso=0;
      this.encomendas=new ArrayList<LinhadeEncomenda>();
      this.estado =0;
   }

   public Encomenda(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas,int estado){
      this.codEnc=codEnc;
      this.codUt=codUt;
      this.codL=codL;
      this.codT="";
      this.peso=peso;
      this.encomendas=new ArrayList<LinhadeEncomenda>(encomendas);
      this.estado=estado;
   }
   public Encomenda(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas, String codT,int estado){
	      this.codEnc=codEnc;
	      this.codUt=codUt;
	      this.codL=codL;
	      this.codT=codT;
	      this.peso=peso;
	      this.encomendas=new ArrayList<LinhadeEncomenda>(encomendas);
	      this.estado=estado;
	   }
   
public Encomenda(String codEnc, String codUt, String codL,Double peso){
      this.codEnc=codEnc;
      this.codUt=codUt;
      this.codL=codL;
      this.peso=peso;
      this.encomendas=new ArrayList<LinhadeEncomenda>();
      this.codT="";
      this.estado=0;
   }   

   public String getCodEnc() {
      return codEnc;
   }

   public String getCodUt() {
      return codUt;
   }

   public String getCodL() {
      return codL;
   }

   public String getCodT() {
      return codT;
   }

   public double getPeso() {
      return peso;
   }
   
   public boolean setCodT(String codT)
   {
	   if(this.codT=="")
	   {
		   this.codT=codT;
		   return true;
	   }
	   return false;
   }
   
   public int getEstado()
   {
	   return estado;
   }

   @SuppressWarnings("unchecked")
   public ArrayList<LinhadeEncomenda> getEncomendas() {
      return new ArrayList<LinhadeEncomenda>(this.encomendas);
   }


   public Encomenda clone(){
      return new Encomenda(	this.codEnc,
    		  				this.codUt,
    		  				this.codL,
    		  				this.peso,
    		  				this.encomendas,
    		  				this.codT,
    		  				this.estado);
   }

   public void addLinhaEncomenda(LinhadeEncomenda le)
   {
      LinhadeEncomenda le1 = new LinhadeEncomenda(le);
      this.encomendas.add(le1);
   }

   public void addLinhaEncomenda(String codProd, String descricao, double quant, double voluntarios)
   {
      LinhadeEncomenda l = new LinhadeEncomenda(codProd, descricao, quant, voluntarios);
      this.encomendas.add(l);
   }
  
   public void setToAberta()
   {
	   this.estado=0;
   }
   
   public void avancaEstado()
   {
	   if(this.estado>=0 && this.estado<=4)
		   this.estado++;
   }
   
   
   public void recusarEnc()
   {
	   if(this.estado==0)
		   this.estado=-1;
   }
   
}
