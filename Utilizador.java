import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utilizador {
    private String iduser;
    private String name;
    private String passU;
    private String emailU;
    private double gpsxuser;
    private double gpsyuser;
    private Map<String,String> emailpass; //um map que guarda o email e a pass para que seja possível verificar se o email corresponde à pass qnd se faz login
                                          //falta inicializar no metodo parametrizado, que não sei bem como fazer

    public Utilizador(){
        this.iduser=" ";
        this.name=" ";
        this.passU=" ";
        this.emailU=" ";
        this.gpsxuser=0;
        this.gpsyuser=0;
        this.emailpass=new HashMap<>();
    }

    public Utilizador(String iduser,String name, String passU, String emailU, double gpsxuser, double gpsyuser){//,Map<String,String> emailpass){
        this.iduser=iduser;
        this.name=name;
        this.passU=passU;
        this.emailU=emailU;
        this.gpsxuser=gpsxuser;
        this.gpsyuser=gpsyuser;


    }

    public String getIduser() {
        return this.iduser;
    }

    public String getName(){return this.name;}

    public String getPassU() {
        return this.passU;
    }

    public String getEmailU() {
        return this.emailU;
    }

    public double getGpsxuser() {
        return this.gpsxuser;
    }

    public double getGpsyuser() {
        return this.gpsyuser;
    }

   /* public Map<String,String> getEmailpass() {
        return this.emailpass.values().stream().collect(Collectors.toMap;
*/
    public Utilizador(Utilizador t){
        this.iduser=getIduser();
        this.passU=getPassU();
        this.emailU=getEmailU();
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
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPassU(), that.getPassU()) &&
                Objects.equals(getEmailU(), that.getEmailU());
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "iduser='" + iduser + '\'' +
                ", name='" + name + '\'' +
                ", passU='" + passU + '\'' +
                ", emailU='" + emailU + '\'' +
                ", gpsxuser=" + gpsxuser +
                ", gpsyuser=" + gpsyuser +
                '}';
    }
}
