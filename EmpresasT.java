import java.util.ArrayList;

public class EmpresasT {
    private ArrayList<EmpresaT> transportadoras;


    public EmpresasT(){
        this.transportadoras= new ArrayList<>();

    }

    public EmpresasT(ArrayList<EmpresaT> transportadoras) throws CloneNotSupportedException {
        this.transportadoras = new ArrayList<>();
        for (EmpresaT l : transportadoras)
            this.transportadoras.add(l.clone());

    }

    public ArrayList<EmpresaT> getTransportadoras(){
        return this.transportadoras;
    }

    public EmpresasT(EmpresasT t){
        this.transportadoras=getTransportadoras();
    }

    public EmpresasT clone(){
        return new EmpresasT(this);
    }


}
