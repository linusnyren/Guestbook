import javax.naming.NamingException;

import connector.Connection;
import domain.Note;
import management.ManagementServiceRemote;


public class TestClient {

	public static void main(String[] args) throws NamingException {
		ManagementServiceRemote service = new Connection().getConnection();

		
		
//		Note test = new Note(JOptionPane.showInputDialog("Author"), JOptionPane.showInputDialog("Meddelande"));
//		service.insertNewNote(test);
		System.out.println(service.getAllNotes().size());


}
}