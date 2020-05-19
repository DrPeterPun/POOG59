package src;
import java.util.Objects;

public class Loja {
    private String codigoL;
    private String nomeL;

    public Loja(){
        this.codigoL=" ";
        this.nomeL=" ";
    }

    public Loja(String codigoL, String nomeL){
        this.codigoL=codigoL;
        this.nomeL=nomeL;
    }

    public String getCodigoL() {
        return codigoL;
    }

    public String getNomeL() {
        return nomeL;
    }

    public Loja(Loja l){
        this.codigoL=getCodigoL();
        this.nomeL=getNomeL();
    }

    public Loja clone(){
        return new Loja(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loja)) return false;
        Loja loja = (Loja) o;
        return Objects.equals(getCodigoL(), loja.getCodigoL()) &&
                Objects.equals(getNomeL(), loja.getNomeL());
    }

    @Override
    public String toString() {
        return "Loja{" +
                "codigoL='" + codigoL + '\'' +
                ", nomeL='" + nomeL + '\'' +
                '}';
    }
}
