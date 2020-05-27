package src;

import java.util.ArrayList;
import java.util.List;

public class RegEncomendas {
	//Aqui em vez de Encomendas usei List<Encomenda> porque depois nas outras classes não conseguia fazer filter para filtrar as encomendas das lojas e dos utilizadores
	private List<Encomenda> encAceites;
	private List<Encomenda> encRecusadas;
	private List<Encomenda> encTerminadas;
	private List<Encomenda> encPendentes;
	
	public RegEncomendas() {
	 this.encAceites= new ArrayList();
	 this.encRecusadas= new ArrayList();
	 this.encTerminadas= new ArrayList();
	 this.encPendentes= new ArrayList();
	}

	public List<Encomenda> getEncAceites() {
		return encAceites;
	}

	public void setEncAceites(List<Encomenda> encAceites) {
		this.encAceites = encAceites;
	}

	public List<Encomenda> getEncRecusadas() {
		return encRecusadas;
	}

	public void setEncRecusadas(List<Encomenda> encRecusadas) {
		this.encRecusadas = encRecusadas;
	}

	public List<Encomenda> getEncTerminadas() {
		return encTerminadas;
	}

	public void setEncTerminadas(List<Encomenda> encTerminadas) {
		this.encTerminadas = encTerminadas;
	}

	public List<Encomenda>  getEncPendentes() {
		return encPendentes;
	}

	public void setEncPendentes(List<Encomenda> encPendentes) {
		this.encPendentes = encPendentes;
	}
	
	//Quando é adicionada uma encomenda, acrescent à lista de encomendas pendentes
	public void addEnc(Encomenda a) {
		this.encPendentes.add(a.clone());
	}
	
	//Quando uma encomenda é aceite, é removida das pendentes e adicionada às aceites
	public void encAceite(Encomenda a) {
		this.encPendentes.remove(a);
		this.encAceites.add(a.clone());
	}
	
	//Quando uma encomenda é recusada,sai das pendentes e vai para a lista de recusadas
	public void encRecusada(Encomenda a) {
		this.encPendentes.remove(a);
		this.encRecusadas.add(a.clone());
	}
	
	//Quando já foi entregue, sai das aceites e vai para as terminadas
	public void encTerminada(Encomenda a){
		this.encTerminadas.add(a.clone());
		this.encAceites.remove(a);
	}

}
