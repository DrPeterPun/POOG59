import java.util.Map;
import java.util.HashMap;

public class LogIn {
	private Map<String,LogInData> login;
	
	public LogIn() {
		this.login = new HashMap<>();
	}
	
	public boolean register(String email, String pass, String userId) {
		// verificar se o email ja foi usado numa conta
		if(this.login.containsKey(email)) {
			System.out.printf("Esse email ja esta associado a uma conta");
			return false;
		}
		 
		// verificar se o id ja foi usado numa conta
		boolean existeid= false;
		for(Map.Entry<String,LogInData> elemento : this.login.entrySet()) {
			if(elemento.getValue().isUser(userId)) {
				existeid = true;
			}
		}
		if(existeid) {
			System.out.printf("Esse userId ja esta associado a uma conta");
			return false;
		}
		
		LogInData lgd = new LogInData(userId, pass);
		this.login.put(email,lgd);
		return true;
	}


	
	
	
}
