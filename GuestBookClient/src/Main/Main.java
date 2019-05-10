package Main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import connector.Connection;
import domain.Note;
import management.ManagementServiceRemote;

public class Main{
	static JTextField authorText;
	static JTextArea messageText;
	static ManagementServiceRemote service = new Connection().getConnection();
	public static void main(String[] args) {
		getUI();
	}
	public static void getUI() {
	       JFrame frame = new JFrame("Management");
	       frame.setLayout(new BorderLayout());
	       JPanel panelFrame = new JPanel();
	       panelFrame.setLayout(new BorderLayout());
	       panelFrame.add(getNotesPanel(), BorderLayout.CENTER);
	       panelFrame.add(getContributionPanel(), BorderLayout.NORTH);
	       frame.add(panelFrame);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(800,600);
	       
	       frame.setVisible(true);
	}
	
	private static JPanel getContributionPanel() {
		JPanel conPanel = new JPanel();
		JTextField conAuthor = new JTextField(10);
		JLabel conAuthorLabel = new JLabel("Author");
		JTextArea conMessage = new JTextArea(3, 20);
		JLabel conMessageLabel = new JLabel("Message");
		JButton conButton = new JButton("Contribute");
		conPanel.add(conAuthorLabel); conPanel.add(conAuthor);
		conPanel.add(conMessageLabel); conPanel.add(conMessage);
		conPanel.add(conButton);
		conButton.addActionListener(e -> contribute(conAuthor.getText(), conMessage.getText()));
		
		return conPanel;
	}
	private static void contribute(String author, String message) {
		Note note = new Note();
		note.setAuthor(author);
		note.setMessage(message);
		service.insertNewNote(note);
		JOptionPane.showMessageDialog(null, "Thank You!");
	}
	private static JPanel getNotesPanel() {
		JPanel panel = new JPanel();
	       for (int i = 0; i < service.getAllNotes().size(); i++) {
	       JButton button = new JButton(service.getAllNotes().get(i).getAuthor()+" skrev: " +service.getAllNotes().get(i).getMessage());
	       Note note = service.getAllNotes().get(i);
	       button.addActionListener(e -> showMenu(note));
	       JPanel ui = new JPanel();
	       ui.setBorder(BorderFactory.createTitledBorder(null, "GuestBook Note id: " +service.getAllNotes().get(i).getId(), TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,12), Color.black));
	       ui.add(button);
	       panel.add(ui);
	       }   
		return panel;
	}
	private static void showMenu(Note note) {
		JFrame frame = new JFrame("Admins Menu");
	       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	       frame.setSize(400,400);
	       frame.setVisible(true);
	       JPanel panelframe = new JPanel();
	       panelframe.setLayout(new BorderLayout());
	       
	    JLabel label = new JLabel("Make up your mind");
	    label.setFont(new Font("times new roman",Font.PLAIN,30));
		panelframe.add(getOptions(note), BorderLayout.SOUTH);
		panelframe.add(editPane(note), BorderLayout.CENTER);
		panelframe.add(label, BorderLayout.NORTH);
		frame.add(panelframe);
	}
	private static JPanel editPane(Note note) {
		JPanel panel = new JPanel();
		authorText = new JTextField(note.getAuthor(), 10);
		JLabel author = new JLabel("Author");
		panel.add(author); panel.add(authorText);
		messageText = new JTextArea(note.getMessage(), 3, 20);
		JLabel message = new JLabel("Message");
		panel.add(message); panel.add(messageText);
		return panel;
	}
	private static JPanel getOptions(Note note) {
		JPanel panel = new JPanel();
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(e -> deleteNote(note));
		JButton editAuthor = new JButton("Edit author");
		editAuthor.addActionListener(e -> editAuthor(note));
		JButton editMessage = new JButton("Edit message");
		editMessage.addActionListener(e -> editMessage(note));
		panel.add(delete); panel.add(editMessage); panel.add(editAuthor);
		return panel;
	}
	private static void editMessage(Note note) {
		Note tempNote = note;
		tempNote.setMessage(messageText.getText());
		service.update(tempNote);
		JOptionPane.showMessageDialog(null, "Updated");
	}
	private static void editAuthor(Note note) {
		Note tempNote = note;
		tempNote.setAuthor(authorText.getText());
		service.update(tempNote);
		JOptionPane.showMessageDialog(null, "Updated");
	}
	private static void deleteNote(Note note) {
		service.delete(note);
		JOptionPane.showMessageDialog(null, "Deleted");
	}
}
