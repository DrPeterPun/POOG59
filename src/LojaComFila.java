package src;

public class LojaComFila extends Loja {
	private int pessoasNaFila;
	
	LojaComFila()
	{
		super();
		this.pessoasNaFila=0;
	}
	
	LojaComFila(String codigoL, String nomeL, String email , String pass ,double gpsx ,double gpsy, int fila){
        super(codigoL, nomeL, email , pass ,gpsx ,gpsy);
        this.pessoasNaFila=fila;
	}
	
	public int getFila()
	{
		return this.pessoasNaFila;
	}
}
