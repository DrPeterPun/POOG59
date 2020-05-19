import java.util.Objects;

public class EmpresaT implements Transporte {
    private String codigoEmpresa;
    private String nomeEmpresa;
    private double nif;
    private double raio;
    private int precokm;
    private double gpsx;
    private double gpsy;


    public EmpresaT(){
        this.codigoEmpresa=" ";
        this.nomeEmpresa=" ";
        this.nif=0;
        this.raio=0;
        this.precokm=0;
        this.gpsx=0;
        this.gpsx=0;
    }

    public EmpresaT(String codigoEmpresa, String nomeEmpresa, double nif, double raio,int precokm,double gpsx,double gpsy){
        this.codigoEmpresa=codigoEmpresa;
        this.nomeEmpresa=nomeEmpresa;
        this.nif=nif;
        this.raio=raio;
        this.precokm=precokm;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
    }

    public String getCodigoEmpresa(){
        return this.codigoEmpresa;
    }

    public String getNomeEmpresa(){
        return this.nomeEmpresa;
    }

    public double getNif(){
        return this.nif;
    }

    public double getRaio(){
        return this.raio;
    }

    public int getPrecokm(){
        return this.precokm;
    }

    public double getGpsx(){
        return this.gpsx;
    }

    public double getGpsy(){
        return this.gpsy;
    }

    public EmpresaT(EmpresaT t){
        this.codigoEmpresa=t.getCodigoEmpresa();
        this.nomeEmpresa=t.getNomeEmpresa();
        this.nif=t.getNif();
        this.raio=t.getRaio();
        this.precokm=t.getPrecokm();
        this.gpsx=t.getGpsx();
        this.gpsy=t.getGpsy();
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
                Objects.equals(getCodigoEmpresa(), empresaT.getCodigoEmpresa()) &&
                Objects.equals(getNomeEmpresa(), empresaT.getNomeEmpresa()) &&
                Objects.equals(getGpsx(), empresaT.getGpsx()) &&
                Objects.equals(getGpsy(), empresaT.getGpsy());
    }

    @Override
    public String toString() {
        return "EmpresaT{" +
                "codigoEmpresa='" + codigoEmpresa + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", nif=" + nif +
                ", raio=" + raio +
                ", precokm=" + precokm +
                ", gpsx='" + gpsx + '\'' +
                ", gpsy='" + gpsy + '\'' +
                '}';
    }
}
