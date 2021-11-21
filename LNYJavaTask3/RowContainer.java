import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RowContainer extends Container {
	private static final long serialVersionUID = -611312099305729814L;
	
	public ArrayList<Contact> unfiltered;
	public ArrayList<Contact> contacts;
	public ArrayList<ArrayList<Label>> labels = new ArrayList<ArrayList<Label>>();
	public int Selected = -1;
	
	public RowContainer() {
		
		this.setLayout(new GridLayout(0, 4, 0, 5));
		this.unfiltered = new ArrayList<Contact>();
		this.contacts = new ArrayList<Contact>();
	}
	
	public void SelectRow(int rowNum)
	{
		ArrayList<Label> row = labels.get(rowNum);
		for(Label label: row)
		{
			label.setBackground(Color.ORANGE);
		}
	}
	
	public void Deselect(int rowNum) 
	{
		if (rowNum != -1)
		{
			ArrayList<Label> row = labels.get(rowNum);
			for(Label label: row)
			{
				label.setBackground(Color.LIGHT_GRAY);
			}
		}
	}
	
	public void Sort(String parameter)
	{
		Stream<Contact> sortedFiltered;
		Stream<Contact> sortedUnfiltered;
		if(parameter.equals("Address"))
		{
			sortedFiltered = contacts.stream().sorted((one, two) -> one.Address.toUpperCase().compareTo(two.Address.toUpperCase()));
			sortedUnfiltered = unfiltered.stream().sorted((one, two) -> one.Address.toUpperCase().compareTo(two.Address.toUpperCase()));
		}
		else if(parameter.equals("Name"))
		{
			sortedFiltered = contacts.stream().sorted((one, two) -> one.Name.toUpperCase().compareTo(two.Name.toUpperCase()));
			sortedUnfiltered = unfiltered.stream().sorted((one, two) -> one.Name.toUpperCase().compareTo(two.Name.toUpperCase()));
		}
		else if(parameter.equals("Surname"))
		{
			sortedFiltered = contacts.stream().sorted((one, two) -> one.Surname.toUpperCase().compareTo(two.Surname.toUpperCase()));
			sortedUnfiltered = unfiltered.stream().sorted((one, two) -> one.Surname.toUpperCase().compareTo(two.Surname.toUpperCase()));
		}
		else
		{
			sortedFiltered = contacts.stream().sorted((one, two) -> one.PhoneNumber.toUpperCase().compareTo(two.PhoneNumber.toUpperCase()));
			sortedUnfiltered = unfiltered.stream().sorted((one, two) -> one.PhoneNumber.toUpperCase().compareTo(two.PhoneNumber.toUpperCase()));
		}
		unfiltered = new ArrayList<Contact>(sortedUnfiltered.collect(Collectors.toList()));
		contacts = new ArrayList<Contact>(sortedFiltered.collect(Collectors.toList()));
		Populate();
	}
	
	public void Populate() {
		labels.clear();
		this.removeAll();
		for(Contact contact: contacts)
		{
			String[] columns = new String[] { contact.Name, contact.Surname, contact.Address, contact.PhoneNumber };
			ArrayList<Label> row = new ArrayList<Label>();
			
			for (String column: columns) {
				Label colLabel = new Label(column, Label.CENTER);
				colLabel.setBackground(Color.LIGHT_GRAY);
				colLabel.setPreferredSize(new Dimension(-1, 100));
				colLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						for (int i = 0; i < labels.size(); i++)
						{
							for (int j = 0; j < labels.get(i).size(); j++)
							{
								if (labels.get(i).get(j) == e.getComponent())
								{
									Deselect(Selected);
									Selected = i;
									SelectRow(Selected);
								}
							}
						}
					}
				});
				this.add(colLabel);
				row.add(colLabel);
			}
			labels.add(row);
		}
		if (labels.size() != 0)
		{
			Selected = 0;
			SelectRow(Selected);
		}
		else
		{
			Selected = -1;
		}
		this.revalidate();
	}
	
	public void deleteSelected() {
		if (Selected != -1)
		{
			Deselect(Selected);
			Contact toRemove = contacts.remove(Selected);
			unfiltered.remove(toRemove);
			this.Populate();
		}
	}
	
	public Contact GetSelected()
	{
		if (Selected != -1)
		{
			return contacts.get(Selected);
		}
		else
		{
			return null;
		}
	}

	public void Filter(String filter)
	{
		String toSearch = filter.toUpperCase();
		contacts = new ArrayList<Contact>(unfiltered.stream().filter((v) -> v.Address.toUpperCase().contains(toSearch) 
				|| v.Name.toUpperCase().contains(toSearch)
				|| v.Surname.toUpperCase().contains(toSearch)
				|| v.PhoneNumber.toUpperCase().contains(toSearch)).collect(Collectors.toList()));
		Deselect(Selected);
		this.Populate();
	}
	
	public String GetData()
	{
		String toReturn = "";
		for (int i = 0; i < contacts.size(); i++)
		{
			toReturn += contacts.get(i).toString();
			if (i + 1 != contacts.size())
				toReturn += "\n";
		}
		return toReturn;
	}
}
