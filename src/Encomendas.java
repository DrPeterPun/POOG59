package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Encomendas implements Serializable {
	//private List<Encomenda> encomendas;
	/*
	public Encomendas() {
		this.encomendas= new ArrayList<>();
	}
	
	/*public Encomendas(Encomenda enc) {
		List<Encomenda> encm = new ArrayList<>();
		encm.forEach(x->encm.add(enc));
	}
	
	public List<Encomenda> getEncomendas() {
		return encomendas;
	}

	public void setEncomendas(List<Encomenda> encomendas) {
		this.encomendas = encomendas;
	}
	
	
	public void addEncomenda(Encomenda enc){
		this.encomendas.add(enc.clone());
	}
    
	public void removeEncomenda(Encomenda enc) {
		this.encomendas.remove(enc.clone());
	}*/
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8442531638986925753L;
	private Map<String,Encomenda> encomendas;
	
	public Encomendas()
	{
		this.encomendas = new TreeMap<>();
	}
	
	public Encomendas(Encomendas enc)
	{
		Encomendas enc2 = enc.clone();
		for(Entry<String, Encomenda> encomenda: enc2.getEncomendas().entrySet())
		{
			this.encomendas.put( encomenda.getKey(), encomenda.getValue());
		}
	}
	
	public void addEncomenda(Encomenda enc)
	{
		this.encomendas.put(enc.getCodEnc(),enc);
	}
	
	public Map<String,Encomenda> getEncomendas()
	{
		Map<String, Encomenda> m = new TreeMap<String, Encomenda>();
		for(Entry<String, Encomenda> encomenda: this.encomendas.entrySet())
		{
			m.put(encomenda.getKey(),encomenda.getValue());
		}
		return m;
	}
	
	public Encomendas clone() 
	{
		Encomendas e = new Encomendas();
		for(Entry<String, Encomenda> encomenda: this.encomendas.entrySet())
		{
			e.addEncomenda(encomenda.getValue());
		}
		return e;
	}
	
	public void setToAberto(Encomenda e)
	{
	   this.encomendas.get(e.getCodEnc()).setToAberta();
	}
	
	public void avancaEstado(Encomenda e)
	{
		this.encomendas.get(e.getCodEnc()).avancaEstado();
	}
	   
	public void recusarEnc(Encomenda e)
	{
		this.encomendas.get(e.getCodEnc()).recusarEnc();
	}
	
	public void setToAberto(String enc)
	{
	   this.encomendas.get(enc).setToAberta();
	}
	
	public void avancaEstado(String enc)
	{
		this.encomendas.get(enc).avancaEstado();
	}
	   
	public void recusarEnc(String enc)
	{
		this.encomendas.get(enc).recusarEnc();
	}
}
