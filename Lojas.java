import java.util.ArrayList;

public class Lojas {
    private ArrayList<Loja> lojas;

    public Lojas(){
        this.lojas=new ArrayList<>();
    }

    public Lojas(ArrayList<Loja> lojas){
        this.lojas=new ArrayList<>();
        for(Loja l: lojas)
            this.lojas.add(l.clone());
    }

    public ArrayList<Loja> getLojas() {
        return this.lojas;
    }

    public Lojas(Lojas l){
        this.lojas=l.getLojas();
    }

    public Lojas clone(){
        return new Lojas(this);
    }
}
