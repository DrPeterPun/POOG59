import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Encomendas {
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
		this.encomendas.put(enc.getCodUt(),enc);
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
}
