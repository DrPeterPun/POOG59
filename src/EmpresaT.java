package src;
import java.util.Objects;

public class EmpresaT implements TransporteInterface {
    private String codigoEmpresa;
    private String nomeEmpresa;
    private double nif;
    private double raio;
    private int precokm;
    private double gpsx;
    private double gpsy;
    private Encomendas encT;
    private String email;
    private String pass;

    public EmpresaT(){
        this.codigoEmpresa=" ";
        this.nomeEmpresa=" ";
        this.nif=0;
        this.raio=0;
        this.precokm=0;
        this.gpsx=0;
        this.gpsx=0;
        this.encT= new Encomendas();
        this.email="";
        this.pass="";
    }

    public EmpresaT(String codigoEmpresa, String nomeEmpresa, double nif, double raio,int precokm,double gpsx,double gpsy, String email, String pass){
        this.codigoEmpresa=codigoEmpresa;
        this.nomeEmpresa=nomeEmpresa;
        this.nif=nif;
        this.raio=raio;
        this.precokm=precokm;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
        this.email=email;
        this.pass=pass;
    }
       
 	public Encomendas getEncT() {
		return encT;
	}

	public void setEncT(Encomendas encT) {
		this.encT = encT;
	}

	public String getCodigo(){
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

    public String getEmail()
    {
    	return this.email;
    }
    
    public String getPass()
    {
    	return this.pass;
    }
    public EmpresaT(EmpresaT t){
        this.codigoEmpresa=t.getCodigo();
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
    
    public void addEncT(Encomenda a) {
    	this.encT.addEncomenda(a.clone());
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


	public boolean logIn(String pass) {
		return this.pass==pass;
	}
	
	public double detPreco(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso)
	{
		double preco;

		preco = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
		preco=preco*this.precokm;
		if(peso>2)
			preco=preco*1.5;
		return preco;
		
	}
	
	public double detDist(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso)
	{

		double dist = Math.sqrt(Math.pow((this.gpsx - gpsxl), 2) + Math.pow((this.gpsy - gpsyl), 2)) + Math.sqrt(Math.pow((gpsxu - gpsxl), 2) + Math.pow((gpsyu - gpsyl), 2));
		return dist;
		
	}
}
