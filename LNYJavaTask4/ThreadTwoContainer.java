import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

public class ThreadTwoContainer extends Container{
	private static final long serialVersionUID = 4764527772689658593L;
	
	public ArrayList<Label> labels = new ArrayList<Label>();
	public int current = -1;

	public ThreadTwoContainer() {
		setLayout(new GridLayout(0, 1));
	}
	
	public void Populate() {
		current++;
		Label newLabel = new Label(Integer.toString(labels.size() + 1) 
				+ ". 2 to the power of " 
				+ current 
				+ ": " 
				+ Math.pow(2, current));
		labels.add(newLabel);
		this.add(newLabel);
	}
}
