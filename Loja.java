import java.util.ArrayList;

public class Loja {
    private String codigoL;
    private String nomeL;
    private String emailL;
    private String passL;

    public Loja(){
        this.codigoL=" ";
        this.nomeL=" ";
        this.emailL=" ";
        this.passL=" ";
    }

    public Loja(String codigoL, String nomeL, String emailL, String passL){
        this.codigoL=codigoL;
        this.nomeL=nomeL;
        this.emailL=emailL;
        this.passL=passL;
    }

    public String getCodigoL() {
        return codigoL;
    }

    public String getNomeL() {
        return nomeL;
    }

    public String getEmailL() {
        return emailL;
    }

    public String getPassL() {
        return passL;
    }

    public Loja(Loja l){
        this.codigoL=getCodigoL();
        this.nomeL=getNomeL();
        this.emailL=getEmailL();
        this.passL=getPassL();
    }

    public Loja clone(){
        return new Loja(this);
    }
}
