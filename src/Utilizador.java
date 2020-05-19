package src;
import java.util.Objects;

public class Utilizador {
    private String iduser;
    private String name;
    private double gpsxuser;
    private double gpsyuser;

    public Utilizador(){
        this.iduser=" ";
        this.name=" ";
        this.gpsxuser=0;
        this.gpsyuser=0;
    }

    public Utilizador(String iduser,String name,double gpsxuser, double gpsyuser){
        this.iduser=iduser;
        this.name=name;
        this.gpsxuser=gpsxuser;
        this.gpsyuser=gpsyuser;
    }

    public String getIduser() {
        return this.iduser;
    }

    public String getName(){
        return this.name;
    }

    public double getGpsxuser() {
        return this.gpsxuser;
    }

    public double getGpsyuser() {
        return this.gpsyuser;
    }

    //sets
    // metudo private por seguranca (mudar este tripo de informacao deve ser mais restrito imo)
    public void setIduser(String id) {
        this.iduser=id;
    }
    // metudo private por seguranca (mudar este tripo de informacao deve ser mais restrito imo)
    public void setName(String name){
        this.name=name;
    }
 
    public void setGpsxuser(double gpsx) {
        this.gpsxuser=gpsx;        
    }

    public void setGpsyuser(double gpsy) {
        this.gpsyuser=gpsy;
    }

   /* public Map<String,String> getEmailpass() {
        return this.emailpass.values().stream().collect(Collectors.toMap;
*/
    public Utilizador(Utilizador t){
        this.iduser=getIduser();
        this.name=getName();
        this.gpsxuser=getGpsxuser();
        this.gpsyuser=getGpsyuser();
    }


    public Utilizador clone(){
        return new Utilizador(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizador)) return false;
        Utilizador that = (Utilizador) o;
        return Double.compare(that.getGpsxuser(), getGpsxuser()) == 0 &&
                Double.compare(that.getGpsyuser(), getGpsyuser()) == 0 &&
                Objects.equals(getIduser(), that.getIduser()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "iduser='" + iduser + '\'' +
                ", name='" + name + '\'' +
                ", gpsxuser=" + gpsxuser +
                ", gpsyuser=" + gpsyuser +
                '}';
    }
}
