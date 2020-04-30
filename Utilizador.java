import java.util.ArrayList;

public class Utilizador {
    private String iduser;
    private String name;
    private String passU;
    private String emailU;
    private double gpsxuser;
    private double gpsyuser;

    public Utilizador(){
        this.iduser=" ";
        this.name=" ";
        this.passU=" ";
        this.emailU=" ";
        this.gpsxuser=0;
        this.gpsyuser=0;
    }

    public Utilizador(String iduser,String name, String passU,String emailU,double gpsxuser, double gpsyuser){
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
}
