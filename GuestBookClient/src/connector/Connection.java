package connector;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import management.ManagementServiceRemote;

public class Connection{

	public ManagementServiceRemote getConnection() {
			Properties jndiProperties = new Properties();
			String jndiname = 
					"GuestBook/Implementation!management.ManagementServiceRemote";
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://linusnyren.ddns.net:8080");
			jndiProperties.put("jboss.naming.client.ejb.context", true);
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "Test");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "password");

//			String jndiname = 
//					"GuestBookServerApplication/Implementation!management.ManagementServiceRemote";
//		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//		jndiProperties.put("jboss.naming.client.ejb.context", true);

		Context jndi;
		try {
			jndi = new InitialContext(jndiProperties);
			ManagementServiceRemote service = (ManagementServiceRemote) jndi.lookup(jndiname);
			return service;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}

	}
}
