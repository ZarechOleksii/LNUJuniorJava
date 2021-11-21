import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ColumnsContainer extends Container {
	private static final long serialVersionUID = 3673451861710082989L;

	public ArrayList<Label> Labels;	
	public RowContainer rows;
	public Label Status;
	
	public ColumnsContainer(Label status) {
		this.setLayout(new GridLayout(1, 4));
		String[] columns = new String[] { "Name", "Surname", "Address", "Phone number" };
		Labels = new ArrayList<Label>();
		
		for (String column: columns) {
			Label colLabel = new Label(column, Label.CENTER);
			colLabel.setBackground(Color.CYAN);
			colLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					for (int i = 0; i < Labels.size(); i++)
					{
						if(e.getComponent() == Labels.get(i)) {
							Labels.get(i).setBackground(Color.YELLOW);
							Sort(Labels.get(i).getText());
						}
						else
						{
							Labels.get(i).setBackground(Color.CYAN);
						}
					}
				}
			});
			this.add(colLabel);
			Labels.add(colLabel);
		}
		Status = status;
	}
	public void SetRows(RowContainer container) {
		rows = container;
	}
	public void Sort(String parameter) {
		rows.Sort(parameter);
		Status.setText("Sorted by " + parameter);
	}
	public void DeselectAll() {
		for (int i = 0; i < Labels.size(); i++)
		{
			Labels.get(i).setBackground(Color.CYAN);
		}
	}
}
