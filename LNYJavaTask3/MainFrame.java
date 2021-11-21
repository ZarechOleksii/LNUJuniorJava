import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;


public class MainFrame extends Frame {
	
	private static final long serialVersionUID = 2067366012079577280L;
	
	public Label status = new Label("Waiting...");
	public Addresses addresses = new Addresses(status);
	public ButtonContainer buttons;
	public MainFrame self;
	public String FilePath;
	public String FileName; 
	
	public MainFrame() {
		setSize(600, 600);
		setMinimumSize(new Dimension(400, 200));
		setLayout(new BorderLayout());
		setMenuBar(new MenuBar());
		setTitle("Address Book");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		status.setBackground(Color.black);
		status.setForeground(Color.white);
		add(status, BorderLayout.SOUTH);
		
		add(addresses, BorderLayout.CENTER);
		
		setMenuBar(CreateMenuBar());
		
		//SetAddresses("src/task3.txt");
		buttons = new ButtonContainer(this);
		add(buttons, BorderLayout.NORTH);
		
		self = this;
		
		setVisible(true);
	}
	
	public MenuBar CreateMenuBar()
	{
		MenuBar toReturn = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem openFile = new MenuItem("Open file");
		MenuItem saveFile = new MenuItem("Save");
		saveFile.setEnabled(false);
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog = new FileDialog(self);
				fileDialog.setFile("*.txt");
				fileDialog.setVisible(true);
				String filename = fileDialog.getFile();
				if (filename != null)
				{
					try {
						SetAddresses(fileDialog.getDirectory() + filename);
						status.setText("Opened " + filename);
						buttons.toFilter.setText("");
						addresses.columnsCont.DeselectAll();
						FilePath = fileDialog.getDirectory();
						FileName = filename;
						saveFile.setEnabled(true);
						buttons.SetupButtons();
					}
					catch (Exception ee) {
						status.setText("Failed to open " + filename + ". Wrong format");
					}
				}
					
			}	
		});
		file.add(openFile);
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath + FileName));
					writer.write(addresses.rowCont.GetData());
					writer.close();
					status.setText("Saved to " + FileName);
				}
				catch (Exception ee) {
					status.setText("Failed to save!");
				}
			}	
		});
		file.add(saveFile);
		MenuItem saveAs = new MenuItem("Save as");
		saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog = new FileDialog(self, "Save as...");
				fileDialog.setFile("data.txt");
				fileDialog.setVisible(true);
				String filename = fileDialog.getFile();
				if (filename != null)
				{
					try {
						FileName = filename;
						FilePath = fileDialog.getDirectory();
						BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath + FileName));
						writer.write(addresses.rowCont.GetData());
						writer.close();
						status.setText("Saved to " + FileName);
						saveFile.setEnabled(true);
					}
					catch (Exception ee) {
						status.setText("Failed to open " + filename + ". Wrong format");
					}
				}
			}	
		});
		file.add(saveAs);
		MenuItem exit = new MenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}	
		});
		file.add(exit);
		toReturn.add(file);
		
		
		return toReturn;
	}
	
	public void SetAddresses(String filename) {
		
		File toOpen = new File(filename);
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(toOpen));
			String line;
			
			ArrayList<Contact> contacts = new ArrayList<Contact>();
			
			while((line = br.readLine()) != null) 
			{
				if (line.trim().length() != 0)
				{
					String[] params = line.split(", ");
					contacts.add(new Contact(params[0], params[1], params[2], params[3]));
				}
			}
			
			addresses.ProvideContacts(contacts);
			br.close();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
	}
}