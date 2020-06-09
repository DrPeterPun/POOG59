package src;

import java.io.Serializable;

public class LinhadeEncomenda implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = -8473626387159279097L;
	private String codProd;
   private String descricao;
   private double quant;
   private double valorUnit;

   	public LinhadeEncomenda(){
      this.codProd=" ";
      this.descricao=" ";
      this.quant=0;
      this.valorUnit=0;
   }

   public LinhadeEncomenda(String codProd, String descricao, double quant, double valorUnit){
      this.codProd=codProd;
      this.descricao=descricao;
      this.quant=quant;
      this.valorUnit=valorUnit;
   }
   public String getCodProd() {
      return codProd;
   }

   public String getDescricao() {
      return descricao;
   }

   public double getQuant() {
      return quant;
   }

   public double getValorUnit() {
      return valorUnit;
   }
   
   @Override
   public String toString() {
       return  "codProd=" + this.codProd+
               " descricao=" + this.descricao+
               " quant=" + this.quant+
               " valorUnit=" + this.valorUnit +
               '}';
   }

   public LinhadeEncomenda(LinhadeEncomenda l){
      this.codProd=l.getCodProd();
      this.descricao=l.getDescricao();
      this.quant=l.getQuant();
      this.valorUnit=l.getValorUnit();
   }
}
