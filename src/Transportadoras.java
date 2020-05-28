package src;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Transportadoras {
	 private Map<String,TransporteInterface> transportadoras;
	 
	 public  Transportadoras() {
		 this.transportadoras = new TreeMap<String, TransporteInterface>();
	 }
	 
	 public Transportadoras(Map<String,TransporteInterface> transportadoras){
	        this.transportadoras =new TreeMap<>();
	        for(TransporteInterface l: transportadoras.values()) {
	            this.transportadoras.put(l.clone().getCodigo(),l.clone());
	        }
	 }
	 
	 public boolean addTransportadora(TransporteInterface transp) {
		 //this.transportadoras.put(transp.getCodigo() ,transp );
		 //return true;
		 
		 
		 if(!(existeTtransportadora(transp.getCodigo())))
		 {
			 this.transportadoras.put(transp.getCodigo(),transp.clone());
			 return true;
		 }
		 else return false; 
		 
	 }
	 
	 public boolean existeTtransportadora(String codigo) {
	        boolean r=false;
	        if(this.transportadoras.containsKey(codigo)) r=true;
	        return r;
	 }
	 
	 @SuppressWarnings("unchecked")
	 public TransporteInterface detMaisBarato(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso)
	 {
		 Set<Map.Entry<String, TransporteInterface>> set = transportadoras.entrySet();
		Map.Entry<String, TransporteInterface> maisBarato= (Entry<String, TransporteInterface>) set.toArray()[0];
		 
		 for(Entry<String, TransporteInterface> ti:set)
		 {
			if(maisBarato.getValue().detPreco(gpsxu,gpsyu,gpsxl,gpsyl,peso)>ti.getValue().detPreco(gpsxu,gpsyu,gpsxl,gpsyl,peso))
			{
				maisBarato=ti;
			}
		 }
			 
		return maisBarato.getValue().clone();
	 }
	 
	 public TransporteInterface detMaisPerto(double gpsxu, double gpsyu,double gpsxl, double gpsyl, double peso)
	 {
		 Set<Map.Entry<String, TransporteInterface>> set = transportadoras.entrySet();
		Map.Entry<String, TransporteInterface> maisBarato= (Entry<String, TransporteInterface>) set.toArray()[0];
		 
		 for(Entry<String, TransporteInterface> ti:set)
		 {
			if(maisBarato.getValue().detDist(gpsxu,gpsyu,gpsxl,gpsyl,peso)>ti.getValue().detDist(gpsxu,gpsyu,gpsxl,gpsyl,peso))
			{
				maisBarato=ti;
			}
		 }
			 
		return maisBarato.getValue().clone();
	 }
}
