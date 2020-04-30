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

   public Encomenda(String codEnc, String codUt, String codL,Double peso, ArrayList<LinhadeEncomenda> encomendas){
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

   public ArrayList<LinhadeEncomenda> getEncomendas() {
      return encomendas;
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
}
