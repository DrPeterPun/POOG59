import java.util.Objects;

public class Voluntario {
    private String id;
    private String email;
    private String pass;
    private double gpsx;
    private double gpsy;
    private float raio;

    public Voluntario (){
        this.id=" ";
        this.email=" ";
        this.pass=" ";
        this.gpsx=0;
        this.gpsy=0;
        this.raio=0;
    }

    public Voluntario(String id, String email,String pass, double gpsx,double gpsy, float raio){
        this.id= id;
        this.email=email;
        this.pass=pass;
        this.gpsx=gpsx;
        this.gpsy=gpsy;
        this.raio=raio;
    }

    public String getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPass(){
        return this.pass;
    }

    public double getGpsx(){
        return this.gpsx;
    }

    public double getGpsy(){
        return this.gpsy;
    }

    public float getRaio(){
        return this.raio;
    }


    public Voluntario(Voluntario t){
        this.id=t.getId();
        this.email=t.getEmail();
        this.pass=t.getPass();
        this.gpsx=t.getGpsx();
        this.gpsy=t.getGpsy();
        this.raio=t.getRaio();
    }

    public Voluntario clone(){
        return new Voluntario(this);
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", gpsx=" + gpsx +
                ", gpsy=" + gpsy +
                ", raio=" + raio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return Double.compare(that.getGpsx(), getGpsx()) == 0 &&
                Double.compare(that.getGpsy(), getGpsy()) == 0 &&
                Float.compare(that.getRaio(), getRaio()) == 0 &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPass(), that.getPass());
    }
}
