
public class LogInData {
	private String userId;
	private String password;
	
	public LogInData(String userId, String password){
		this.userId = userId;
		this.password = password;
	}
	
	public String isPassword(String password)
	{
		if (password==this.password) {
			return this.userId;
		}
		return "## password errada";/* no caso de a password estar errada devolve a string comecada por ##, 
		para ver se o que a a fucano devolve é um id ou uma mensagem a dizer que a pass esta errada basta ver se o
		primeiro character é ou nao #, deve haver maneiras melhores de fazer isto mas idk
		*/
	}
	
	public boolean isUser(String userId)
	{
		if (userId==this.userId) {
			return true;
		}
		return false;
	}
	
}
