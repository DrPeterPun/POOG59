package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Encomenda implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 7784133114745763116L;
	private String codEnc;
   private String codUt;
   private String codL;
   private String codT;
   private double peso;
   private ArrayList<LinhadeEncomenda> encomendas;
   private int estado;// -1 = rejeitado -> 0,1,2,3,4 = Aberta,Aceite,Pronta,PorAvaliar,Completa
   private Date data;

   public Encomenda(){
      this.codEnc=" ";
      this.codUt=" ";
      this.codL=" ";
      this.codT=" ";
      this.peso=0;
      this.encomendas=new ArrayList<LinhadeEncomenda>();
      this.estado = 0;
      this.data = new Date();
   }
   
   public Encomenda(String codUt, String codLoja){
	      this.codEnc=" ";
	      this.codUt=codUt;
	      this.codL=codLoja;
	      this.codT=" ";
	      this.peso=0;
	      this.encomendas=new ArrayList<LinhadeEncomenda>();
	      this.estado = 0;
	      this.data = new Date();
	   }

   public Encomenda(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas,int estado){
      this.codEnc=codEnc;
      this.codUt=codUt;
      this.codL=codL;
      this.codT=" ";
      this.peso=peso;
      this.encomendas=new ArrayList<LinhadeEncomenda>(encomendas);
      this.estado=estado;
   }
   //comleto para o clone
   public Encomenda(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas, String codT,int estado, Date data){
	      this.codEnc=codEnc;
	      this.codUt=codUt;
	      this.codL=codL;
	      this.codT=codT;
	      this.peso=peso;
	      this.encomendas=new ArrayList<LinhadeEncomenda>(encomendas);
	      this.estado=estado;
	      this.data=data;
	   }
   
   public Encomenda(String codEnc, String codUt, String codL,Double peso,Date data){
	   this.codEnc=codEnc;
	   this.codUt=codUt;
	   this.codL=codL;
	   this.peso=peso;
	   this.encomendas=new ArrayList<LinhadeEncomenda>();
	   this.codT="";
	   this.estado=0;
	   this.data = data;
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
	   if(this.codT==" ")
	   {
		   this.codT=codT;
		   return true;
	   }
	   return false;
   }
   
   public void setCodEnc(String codEnc)
   {
	   this.codEnc=codEnc;
   }
   
   
   public int getEstado()
   {
	   return estado;
   }
   
   public void setEstado(int i)
   {
	   if(i>=-1 && i<=4)
	   {
		   this.estado=i;
	   }
   }
   
   public void setPeso(double peso)
   {
	   this.peso=peso;
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
    		  				this.estado,
    		  				this.data);
   }

   public void addLinhaEncomenda(LinhadeEncomenda le)
   {
      LinhadeEncomenda le1 = new LinhadeEncomenda(le);
      this.encomendas.add(le1);
   }

   public void addLinhaEncomenda(String codProd, String descricao, double quant, double valor)
   {
      LinhadeEncomenda l = new LinhadeEncomenda(codProd, descricao, quant, valor);
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

   public Date getDate() {
	   return this.data;
   }
   
   public String toString() {
       String s = new String();
       s = "Encomenda nº: " + this.codEnc + " feita em: " + this.data + " \n ";
       for(LinhadeEncomenda le: this.encomendas)
       {
    	   s.concat(le.toString());
       }
       return s;
   }
   
   public void calculaPeso()
   {
	   if(this.peso!=0) {return;}
	   Random r = new Random();
	   
	   for(LinhadeEncomenda le: this.encomendas)
	   {
		   double rv = 0 + (10 - 0) * r.nextDouble();
		   this.peso+=rv*le.getQuant();
	   }
   }


   
   
}
