import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonContainer extends Container {
	
	private static final long serialVersionUID = 7292289931726817369L;
	
	public Button addNew = new Button("Add new contact...");
	public Button editExisting = new Button("Edit selected...");
	public Button deleteExisting = new Button("Delete selected...");
	public TextField toFilter = new TextField();
	public Button filterButton = new Button("Filter");
	public MainFrame mainFrame;

	public ButtonContainer(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		this.setLayout(new GridLayout(0, 2));
		
		add(toFilter);
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.addresses.rowCont.Filter(toFilter.getText());	
				if (toFilter.getText().trim().length() > 0) {
					mainFrame.status.setText("Filtered \"" + toFilter.getText() + "\"");
				} 
				else {
					mainFrame.status.setText("Removed filter");
				}
				SetupButtons();
			}	
		});
		add(filterButton);
		
		addNew.setEnabled(true);
		addNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModalFrame(mainFrame);
				mainFrame.addresses.columnsCont.DeselectAll();
				SetupButtons();
			}	
		});
		add(addNew);
		
		editExisting.setEnabled(false);
		editExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModalFrame(mainFrame, mainFrame.addresses.rowCont.GetSelected());
				mainFrame.addresses.columnsCont.DeselectAll();
				SetupButtons();
			}
		});
		add(editExisting);
		
		deleteExisting.setEnabled(false);
		deleteExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.addresses.rowCont.deleteSelected();
				mainFrame.status.setText("Contact deleted");
				SetupButtons();
			}
		});
		add(deleteExisting);
		SetupButtons();
	}
	
	public void SetupButtons() {
		if (mainFrame.addresses.rowCont.contacts.isEmpty())
		{
			editExisting.setEnabled(false);
			deleteExisting.setEnabled(false);
		}
		else
		{
			editExisting.setEnabled(true);
			deleteExisting.setEnabled(true);
		}
	}
}
