import java.sql.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import management.ManagementServiceRemote;


public class TestClient {
	static String jndiname = 
	"GuestBookServerApplication/Implementation!management.ManagementServiceRemote";
	public static void main(String[] args) throws NamingException {

		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi = new InitialContext(jndiProperties);

		ManagementServiceRemote service = (ManagementServiceRemote) jndi.lookup(jndiname);
		
//		System.out.println(service.getAllNotes());
		System.out.println(service.searchByAuthor("LEmma"));


}
}