public class LinhadeEncomenda {
   private String codProd;
   private String descricao;
   private int quant;
   private String voluntarios;

   public LinhadeEncomenda(){
      this.codProd=" ";
      this.descricao=" ";
      this.quant=0;
      this.voluntarios=" ";
   }

   public LinhadeEncomenda(String codProd, String descricao, int quant, String voluntarios){
      this.codProd=codProd;
      this.descricao=descricao;
      this.quant=quant;
      this.voluntarios=voluntarios;
   }
   public String getCodProd() {
      return codProd;
   }

   public String getDescricao() {
      return descricao;
   }

   public int getQuant() {
      return quant;
   }

   public String getVoluntarios() {
      return voluntarios;
   }

   public LinhadeEncomenda(LinhadeEncomenda l){
      this.codProd=l.getCodProd();
      this.descricao=l.getDescricao();
      this.quant=l.getQuant();
      this.voluntarios=l.getVoluntarios();
   }
}
