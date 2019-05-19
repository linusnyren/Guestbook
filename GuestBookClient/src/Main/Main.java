package Main;


import java.awt.BorderLayout;
import java.awt.Color;
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
	 static JPanel panelFrame, notes, conPanel;
	 static JFrame frame;
	static ManagementServiceRemote service = new Connection().getConnection();
	public static void main(String[] args) {

		frame = new JFrame("Management");
	       frame.setLayout(new BorderLayout());
	       panelFrame = new JPanel();
	       panelFrame.setLayout(new BorderLayout());
	       notes = getNotesPanel();
	       panelFrame.add(notes, BorderLayout.CENTER);
	       conPanel = getContributionPanel();
	       panelFrame.add(conPanel, BorderLayout.NORTH);
	       frame.add(panelFrame);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(800,600);
	       
	       frame.setVisible(true);}
	
	private static JPanel getContributionPanel() {
		JPanel conPanel = new JPanel();
		JTextField conAuthor = new JTextField(10);
		JLabel conAuthorLabel = new JLabel("Author");
		JTextArea conMessage = new JTextArea(3, 20);
		JLabel conMessageLabel = new JLabel("Message");
		JButton conButton = new JButton("Contribute");
		JButton updateButton = new JButton("Update");
		conPanel.add(conAuthorLabel); conPanel.add(conAuthor);
		conPanel.add(conMessageLabel); conPanel.add(conMessage);
		conPanel.add(conButton); conPanel.add(updateButton);
		updateButton.addActionListener(e -> updatePanel());
		conButton.addActionListener(e -> contribute(conAuthor.getText(), conMessage.getText()));
		
		return conPanel;
	}
	private static void contribute(String author, String message) {
		Note note = new Note();
		note.setAuthor(author);
		note.setMessage(message);
		service.insertNewNote(note);
	}
	private static void updatePanel() {
        frame.getContentPane().removeAll();
        frame.invalidate();
		frame.validate();
		frame.repaint();
	       notes = getNotesPanel();
	       panelFrame.add(notes, BorderLayout.CENTER);
	       conPanel = getContributionPanel();
	       panelFrame.add(conPanel, BorderLayout.NORTH);
	       frame.add(panelFrame);
        frame.revalidate();
		frame.repaint();
	}
	private static JPanel getNotesPanel() {
		JPanel panel = new JPanel();
		System.out.println("Fetching notes...");
	       for (int i = 0; i < service.getAllNotes().size(); i++) {
	       Note tempNote = service.getAllNotes().get(i);
	       JButton button = new JButton(tempNote.getAuthor()+" skrev: " +tempNote.getMessage());
	       button.addActionListener(e -> showMenu(tempNote));
	       JPanel ui = new JPanel();
	       ui.setBorder(BorderFactory.createTitledBorder(null, "GuestBook Note id: " +tempNote.getId(), TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,12), Color.black));
	       ui.add(button);
	       panel.add(ui);
	       } 
	     System.out.println("Notes were fetched!");
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
