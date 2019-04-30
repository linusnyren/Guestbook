package connector;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import management.ManagementServiceRemote;

public class Connection{
	static String jndiname = 
	"GuestBookServerApplication/Implementation!management.ManagementServiceRemote";
	public ManagementServiceRemote getConnection() {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi;
		try {
			jndi = new InitialContext(jndiProperties);
			ManagementServiceRemote service = (ManagementServiceRemote) jndi.lookup(jndiname);
			return service;
		} catch (NamingException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
