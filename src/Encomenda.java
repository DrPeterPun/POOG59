import java.util.ArrayList;

public class Encomenda {
   private String codEnc;
   private String codUt;
   private String codL;
   private double peso;
   private ArrayList<LinhadeEncomenda> encomendas;

   public Encomenda(){
      this.codEnc=" ";
      this.codUt=" ";
      this.codL=" ";
      this.peso=0;
      this.encomendas=new ArrayList<>();
   }

   @SuppressWarnings("unchecked")
public Encomenda(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas){
      this.codEnc=codEnc;
      this.codUt=codUt;
      this.codL=codL;
      this.peso=peso;
      this.encomendas=new ArrayList<>();
      this.encomendas=(ArrayList<LinhadeEncomenda>)encomendas.clone();
   }
   
public Encomenda(String codEnc, String codUt, String codL,Double peso){
      this.codEnc=codEnc;
      this.codUt=codUt;
      this.codL=codL;
      this.peso=peso;
      this.encomendas=new ArrayList<>();
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

   public double getPeso() {
      return peso;
   }

   @SuppressWarnings("unchecked")
   public ArrayList<LinhadeEncomenda> getEncomendas() {
      return (ArrayList<LinhadeEncomenda>)encomendas.clone();
   }

   public Encomenda(Encomenda t){
      this.codEnc=getCodEnc();
      this.codUt=getCodUt();
      this.codL=getCodL();
      this.peso=getPeso();
      this.encomendas=getEncomendas();
   }

   public Encomenda clone(){
      return new Encomenda(this);
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
}
