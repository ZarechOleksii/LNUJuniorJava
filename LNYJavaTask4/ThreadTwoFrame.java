import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;

public class ThreadTwoFrame extends Frame implements Runnable {
	private static final long serialVersionUID = 4764527772689658593L;
	
	ThreadTwoContainer labels;
	public boolean doing = true;
	
	public int Period = 2000;
	
	public ThreadTwoFrame() {
		Button stopButton = new Button("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doing = false;
			}
		});
		
		Button startButton = new Button("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doing = true;
			}
		});
		
		Label currentPeriod = new Label(Integer.toString(Period));
		
		Button fasterButton = new Button(">>");
		fasterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Period != 1000)
				{
					Period -= 100;
					currentPeriod.setText(Integer.toString(Period));
				}
			}
		});
		
		Button slowerButton = new Button("<<");
		slowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Period != 3000)
				{
					Period += 100;
					currentPeriod.setText(Integer.toString(Period));
				}
			}
		});
		slowerButton.setBounds(10, 110, 25, 30);
		add(slowerButton);
		
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(400, 400));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setTitle("Calcs");
		Panel top = new Panel();
		top.add(startButton);
		top.add(stopButton);
		top.add(slowerButton);
		top.add(currentPeriod);
		top.add(fasterButton);
		
		
		this.add(top, BorderLayout.NORTH);
	}
	
	@Override
	public void run() {
		labels = new ThreadTwoContainer();
		this.add(new JScrollPane(labels), BorderLayout.CENTER);
		setVisible(true);
		try {
			while(true)
			{
				if (doing)
				{
					labels.Populate();
					revalidate();
				}
				Thread.sleep(Period);
			}
		} catch (InterruptedException e) {
			System.out.println("aboba");
			e.printStackTrace();
		}
	}
}
