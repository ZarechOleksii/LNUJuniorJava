import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JScrollPane;

public class Addresses extends Container {
	private static final long serialVersionUID = -2827914209103260748L;
	
	public RowContainer rowCont;
	public ColumnsContainer columnsCont;
	
	public Addresses(Label status) {
		setLayout(new BorderLayout());
		rowCont = new RowContainer();
		columnsCont = new ColumnsContainer(status);
		columnsCont.SetRows(rowCont);
		this.removeAll();
		this.add(columnsCont, BorderLayout.NORTH);
		this.add(new JScrollPane(rowCont), BorderLayout.CENTER);
	}
	
	public void ProvideContacts(ArrayList<Contact> contacts)
	{
		rowCont.contacts = new ArrayList<Contact>(contacts);
		rowCont.unfiltered = new ArrayList<Contact>(contacts);
		rowCont.Populate();
		columnsCont.DeselectAll();
		this.revalidate();
	}
}