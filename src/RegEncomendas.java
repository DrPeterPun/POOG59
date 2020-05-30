package src;

import java.util.ArrayList;
import java.util.List;

public class RegEncomendas {
	//Aqui em vez de Encomendas usei List<Encomenda> porque depois nas outras classes não conseguia fazer filter para filtrar as encomendas das lojas e dos utilizadores
	private ArrayList<Encomenda> encAberta;//(o utilizador decide fazer esta encomenda e ja aceitou a transportadora)
	private ArrayList<Encomenda> encAceite;// no caso de o transportes er feito por uma empresa vem logo para aqui e nao para as abertas, se for por um voluntario ele precisa de aceitar
	private ArrayList<Encomenda> encRecusada;// o voluntario recusou a encomenda
	private ArrayList<Encomenda> encPronta;// a Loja diz que a encomenda esta cpronta
	private ArrayList<Encomenda> encPorAvaliar;// a encomenda ja foi entrgue mas ainda nao foi avaliada pelo user
	private ArrayList<Encomenda> encCompleta;// encomenda foi completada
	
	public RegEncomendas() {
		this.encAberta= new ArrayList<Encomenda>();
		this.encAceite=  new ArrayList<Encomenda>();
		this.encRecusada= new ArrayList<Encomenda>();
		this.encPronta= new ArrayList<Encomenda>();
		this.encPorAvaliar= new ArrayList<Encomenda>();
		this.encCompleta= new ArrayList<Encomenda>();
	}
	
	public RegEncomendas(ArrayList<Encomenda> enca,ArrayList<Encomenda> encac,ArrayList<Encomenda> encr,ArrayList<Encomenda> encp,ArrayList<Encomenda>encpa,ArrayList<Encomenda> encc) {
		this.encAberta= new ArrayList<Encomenda>(enca);
		this.encAceite=  new ArrayList<Encomenda>(encac);
		this.encRecusada= new ArrayList<Encomenda>(encr);
		this.encPronta= new ArrayList<Encomenda>(encp);
		this.encPorAvaliar= new ArrayList<Encomenda>(encpa);
		this.encCompleta= new ArrayList<Encomenda>(encc);
	}
	

	public List<Encomenda> getEncAberta() {
		return encAberta;
	}
	
	public List<Encomenda> getEncAceite() {
		return encAceite;
	}
	
	public List<Encomenda> getEncRecusada() {
		return encRecusada;
	}
	
	public List<Encomenda> getEncPronta() {
		return encPronta;
	}
	
	public List<Encomenda> getEncPorAvaliar() {
		return encPorAvaliar;
	}
	
	public List<Encomenda> getEncCompleta() {
		return encCompleta;
	}

	
	public RegEncomendas clone()
	{
		return new RegEncomendas(	this.encAberta,
									this.encAceite,
									this.encRecusada,
									this.encPronta,
									this.encPorAvaliar,
									this.encCompleta);	
	}
	

	//(o utilizador decide fazer esta encomenda e ja aceitou a transportadora)
	public void addEnc(Encomenda a) {
		this.encAberta.add(a.clone());
	}
	
	// no caso de o transportes ser feito por uma empresa vem logo para aqui e nao para as abertas, se for por um voluntario ele precisa de aceitar
	public void encAceite(Encomenda a) {
		this.encAberta.remove(a);
		this.encAceite.add(a.clone());
	}
	
	// o voluntario recusou a encomenda
	public void encRecusada(Encomenda a) {
		this.encAberta.remove(a);
		this.encRecusada.add(a.clone());
	}
	
	// a Loja diz que a encomenda esta cpronta
	public void encPronta(Encomenda a){
		this.encAceite.remove(a);
		this.encPronta.add(a.clone());
	}
	
	// a encomenda ja foi entrgue mas ainda nao foi avaliada pelo user
	public void encPorAvaliar(Encomenda a){
		this.encPronta.remove(a);
		this.encPorAvaliar.add(a.clone());
	}
	
	// encomenda foi completada
	public void encCompleta(Encomenda a){
		this.encPorAvaliar.remove(a);
		this.encCompleta.add(a.clone());
	}
	

}
