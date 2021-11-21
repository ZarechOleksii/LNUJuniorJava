import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class ModalFrame extends Dialog {
	private static final long serialVersionUID = -2616885257208539741L;
	public MainFrame mainframe;
	public Label nameLabel = new Label("Name:");
	public Label surnameLabel = new Label("Surname:");
	public Label addressLabel = new Label("Address:");
	public Label phoneLabel = new Label("Phone number:");
	public TextField nameField = new TextField();
	public TextField surnameField = new TextField();
	public TextField addressField = new TextField();
	public TextField phoneField = new TextField();
	public Button submit = new Button("Submit");
	
	public ModalFrame(MainFrame mainframe) {
		super(mainframe, "Add contact...", Dialog.ModalityType.DOCUMENT_MODAL);
		this.mainframe = mainframe;
		mainframe.status.setText("Adding contact...");
		nameField.setPreferredSize(new Dimension(500, 20));
		nameField.setMaximumSize(new Dimension(500, 20));
		nameLabel.setPreferredSize(new Dimension(500, 20));
		nameLabel.setMaximumSize(new Dimension(500, 20));
		surnameField.setPreferredSize(new Dimension(500, 20));
		surnameField.setMaximumSize(new Dimension(500, 20));
		surnameLabel.setPreferredSize(new Dimension(500, 20));
		surnameLabel.setMaximumSize(new Dimension(500, 20));
		addressField.setPreferredSize(new Dimension(500, 20));
		addressField.setMaximumSize(new Dimension(500, 20));
		addressLabel.setPreferredSize(new Dimension(500, 20));
		addressLabel.setMaximumSize(new Dimension(500, 20));
		phoneField.setPreferredSize(new Dimension(500, 20));
		phoneField.setMaximumSize(new Dimension(500, 20));
		phoneLabel.setPreferredSize(new Dimension(500, 20));
		phoneLabel.setMaximumSize(new Dimension(500, 20));
		submit.setPreferredSize(new Dimension(500, 50));
		submit.setMaximumSize(new Dimension(500, 50));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateContact(mainframe.addresses.rowCont);
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setMinimumSize(new Dimension(400, 330));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(nameLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(nameField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(surnameLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(surnameField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(addressLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(addressField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(phoneLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(phoneField);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(submit);
		this.setVisible(true);
	}
	
	public ModalFrame(MainFrame mainframe, Contact contact) {
		super(mainframe, "Edit contact...", Dialog.ModalityType.DOCUMENT_MODAL);
		mainframe.status.setText("Editing contact...");
		this.mainframe = mainframe;
		nameField.setText(contact.Name);
		nameField.setPreferredSize(new Dimension(500, 20));
		nameField.setMaximumSize(new Dimension(500, 20));
		nameLabel.setPreferredSize(new Dimension(500, 20));
		nameLabel.setMaximumSize(new Dimension(500, 20));
		surnameField.setText(contact.Surname);
		surnameField.setPreferredSize(new Dimension(500, 20));
		surnameField.setMaximumSize(new Dimension(500, 20));
		surnameLabel.setPreferredSize(new Dimension(500, 20));
		surnameLabel.setMaximumSize(new Dimension(500, 20));
		addressField.setText(contact.Address);
		addressField.setPreferredSize(new Dimension(500, 20));
		addressField.setMaximumSize(new Dimension(500, 20));
		addressLabel.setPreferredSize(new Dimension(500, 20));
		addressLabel.setMaximumSize(new Dimension(500, 20));
		phoneField.setText(contact.PhoneNumber);
		phoneField.setPreferredSize(new Dimension(500, 20));
		phoneField.setMaximumSize(new Dimension(500, 20));
		phoneLabel.setPreferredSize(new Dimension(500, 20));
		phoneLabel.setMaximumSize(new Dimension(500, 20));
		submit.setPreferredSize(new Dimension(500, 50));
		submit.setMaximumSize(new Dimension(500, 50));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditContact(mainframe.addresses.rowCont.GetSelected());
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setMinimumSize(new Dimension(400, 330));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(nameLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(nameField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(surnameLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(surnameField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(addressLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(addressField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(phoneLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(phoneField);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(submit);
		this.setVisible(true);
	}
	
	public void CreateContact(RowContainer rowCont)
	{
		Contact toAdd = new Contact(nameField.getText(), surnameField.getText(), addressField.getText(), phoneField.getText());
		rowCont.contacts.add(toAdd);
		rowCont.unfiltered.add(toAdd);
		rowCont.Populate();
		mainframe.status.setText("Contact added!");
		this.dispose();
	}
	
	public void EditContact(Contact contact)
	{
		if (contact != null)
		{
			contact.Name = nameField.getText();
			contact.Surname = surnameField.getText();
			contact.Address = addressField.getText();
			contact.PhoneNumber = phoneField.getText();
			mainframe.addresses.rowCont.Populate();
			mainframe.status.setText("Contact edited!");
			this.dispose();
		}
	}
	
}
