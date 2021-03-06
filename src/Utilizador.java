package src;
import java.io.Serializable;
import java.util.Objects;

public class Utilizador implements Serializable{
    private String iduser;
    private String name;
    private double gpsxuser;
    private double gpsyuser;
    private String email;
    private String pass;

    public Utilizador(){
        this.iduser=" ";
        this.name=" ";
        this.gpsxuser=0;
        this.gpsyuser=0;
        this.email="";
    }

    public Utilizador(String iduser,String name,double gpsxuser, double gpsyuser,String email, String pass){
        this.iduser=iduser;
        this.name=name;
        this.gpsxuser=gpsxuser;
        this.gpsyuser=gpsyuser;
        this.email=email;
        this.pass=pass;
    }
    


    public String getIdUser() {
        return this.iduser;
    }

    public String getName(){
        return this.name;
    }

    public double getGpsx() {
        return this.gpsxuser;
    }

    public double getGpsy() {
        return this.gpsyuser;
    }

    public void setIduser(String id) {
        this.iduser=id;
    }
  
    public void setName(String name){
        this.name=name;
    }
 
    public void setGpsxuser(double gpsx) {
        this.gpsxuser=gpsx;        
    }

    public void setGpsyuser(double gpsy) {
        this.gpsyuser=gpsy;
    }
    
	public String getEmail()
    {
    	return this.email;
    }
    	
    
    public Utilizador clone(){
        return new Utilizador(	this.iduser,
								this.name,
								this.gpsxuser,
								this.gpsyuser,
								this.email,
								this.pass);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizador)) return false;
        Utilizador that = (Utilizador) o;
        return Double.compare(that.getGpsx(), this.getGpsx()) == 0 &&
                Double.compare(that.getGpsy(), this.getGpsy()) == 0 &&
                Objects.equals(this.getIdUser(), that.getIdUser()) &&
                Objects.equals(this.getName(), that.getName());
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "iduser='" + iduser + '\'' +
                ", name='" + name + '\'' +
                ", gpsxuser=" + gpsxuser +
                ", gpsyuser=" + gpsyuser +
                ", email=" + email +
                ",pass=" + pass +
                '}';
    }

	public boolean logIn(String pass) {
		return this.pass.equals(pass);
	}

}
