import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ThreadThreeFrame extends Frame implements Runnable  {
	private static final long serialVersionUID = -8939504460147146609L;
	
	public Label toMove;
	public boolean move = true;
	public int x = 10;

	public ThreadThreeFrame() {
		Button stopButton = new Button("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move = false;
			}
		});
		stopButton.setBounds(60, 40, 50, 30);
		add(stopButton);
		
		Button startButton = new Button("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move = true;
			}
		});
		startButton.setBounds(10, 40, 50, 30);
		add(startButton);
		
		Label textLabel = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque erat ante, convallis quis blandit vitae, porta a felis. Cras vestibulum.");
		textLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textLabel.setSize(2000, 50);
		textLabel.setLocation(x, 70);
		add(textLabel);
		toMove = textLabel;
		
		setSize(400, 130);
		setResizable(false);
		setTitle("Line");
		setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	@Override
	public void run() {
		setVisible(true);
		try {
			while(true)
			{
				synchronized (toMove) {
					toMove.wait();
					if (move) {
						if (x < -1200)
							x = 400;
						else {
							x -= 3;
						}
						toMove.setLocation(x, 70);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
