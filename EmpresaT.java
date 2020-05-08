import java.util.Objects;

public class EmpresaT {
    private String codigo;
    private String nomeEmpresa;
    private int nif;
    private int raio;
    private int precokm;
    private String passE;
    private String emailE;


    public EmpresaT(){
        this.codigo=" ";
        this.nomeEmpresa=" ";
        this.nif=0;
        this.raio=0;
        this.precokm=0;
        this.passE=" ";
        this.emailE=" ";
    }

    public EmpresaT(String codigo, String nomeEmpresa, int nif, int raio,int precokm,String passE,String emailE){
        this.codigo=codigo;
        this.nomeEmpresa=nomeEmpresa;
        this.nif=nif;
        this.raio=raio;
        this.precokm=precokm;
        this.passE=passE;
        this.emailE=emailE;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNomeEmpresa(){
        return this.nomeEmpresa;
    }

    public int getNif(){
        return this.nif;
    }

    public int getRaio(){
        return this.raio;
    }

    public int getPrecokm(){
        return this.precokm;
    }

    public String getPassE(){
        return this.passE;
    }

    public String getEmailE(){
        return this.emailE;
    }

    public EmpresaT(EmpresaT t){
        this.codigo=t.getCodigo();
        this.nomeEmpresa=t.getNomeEmpresa();
        this.nif=t.getNif();
        this.raio=t.getRaio();
        this.precokm=t.getPrecokm();
        this.passE=t.getPassE();
        this.emailE=t.getEmailE();
    }

    public EmpresaT clone(){
        return new EmpresaT(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpresaT)) return false;
        EmpresaT empresaT = (EmpresaT) o;
        return getNif() == empresaT.getNif() &&
                getRaio() == empresaT.getRaio() &&
                getPrecokm() == empresaT.getPrecokm() &&
                Objects.equals(getCodigo(), empresaT.getCodigo()) &&
                Objects.equals(getNomeEmpresa(), empresaT.getNomeEmpresa()) &&
                Objects.equals(getPassE(), empresaT.getPassE()) &&
                Objects.equals(getEmailE(), empresaT.getEmailE());
    }

    @Override
    public String toString() {
        return "EmpresaT{" +
                "codigo='" + codigo + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", nif=" + nif +
                ", raio=" + raio +
                ", precokm=" + precokm +
                ", passE='" + passE + '\'' +
                ", emailE='" + emailE + '\'' +
                '}';
    }
}
