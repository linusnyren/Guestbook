package management;

import javax.ejb.Remote;

@Remote //Remote läggs till för EJB remote metoder såsom JNDI
public interface ManagementServiceRemote extends ManagementService {

}
