package src;

import java.util.Map;
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
	 
}
