import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.Timer;

public class ThreadOneFrame extends Frame implements Runnable {
	
	private static final long serialVersionUID = -5553413851468962829L;
	private Color lineColor = Color.blue;
	private Random random = new Random();
	private float thickness = 1;
	
	public Timer timer;
	public int Points = 48;
	public int Now = 1;
	public int Period = 50;
	
	public Label other;
	
	public ThreadOneFrame() {
		Button stopButton = new Button("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});
		stopButton.setBounds(10, 50, 50, 30);
		add(stopButton);
		
		Button startButton = new Button("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
			}
		});
		startButton.setBounds(10, 80, 50, 30);
		add(startButton);
		
		Label currentPeriod = new Label(Integer.toString(Period));
		currentPeriod.setBounds(10, 140, 50, 30);
		add(currentPeriod);
		
		Button fasterButton = new Button(">>");
		fasterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Period != 10)
				{
					Period -= 10;
					timer.setDelay(Period);
					currentPeriod.setText(Integer.toString(Period));
				}
			}
		});
		fasterButton.setBounds(35, 110, 25, 30);
		add(fasterButton);
		
		Button slowerButton = new Button("<<");
		slowerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Period != 300)
				{
					Period += 10;
					timer.setDelay(Period);
					currentPeriod.setText(Integer.toString(Period));
				}
			}
		});
		slowerButton.setBounds(10, 110, 25, 30);
		add(slowerButton);
		
		setSize(500, 500);
		setMinimumSize(new Dimension(400, 400));
		setTitle("Parametric Equation");
		setLayout(null);
		

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				timer.stop();
				dispose();
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				repaint();
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				do {
					thickness = random.nextFloat() * 20;
				}
				while (thickness == 0);
				
				lineColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
				//repaint();
			}
		});
	}
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawLine(0, this.getSize().height / 2, this.getSize().width, this.getSize().height / 2);
		g2d.drawLine(this.getSize().width/ 2, 0, this.getSize().width / 2, this.getSize().height);
		
		g2d.translate(this.getSize().width / 2, this.getSize().height / 2);
		
		
		g2d.setColor(this.lineColor);
		
		g2d.setStroke(new BasicStroke(thickness, 1, 0));
		
		for (int i = 0; i < Now; i++) {
			double t1 = 4 * Math.PI / Points * i;
			double t2 = 4 * Math.PI / Points * (i + 1);
			
			int x1 = XFormula(t1);
			int x2 = XFormula(t2);
			int y1 = YFormula(t1);
			int y2 = YFormula(t2);
			
			g2d.drawLine(x1, y1, x2, y2);
		}
		synchronized(other)
		{
			other.notify();
		}
	}
	private int XFormula(double t) {
		double x = (Math.cos(t) + 2 * Math.cos(t * 0.5)) * getSize().width / 10;
		return (int)x;
	}
	private int YFormula(double t) {
		double y = (Math.sin(t) - 2 * Math.sin(t * 0.5)) * getSize().height / 10;
		return (int)y;
	}
	@Override
	public void run() {
		timer = new Timer(Period, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Points == Now)
				{
					Now = 1;
				}
				else
				{
					Now++;
				}
				repaint();
			}
		});
		setVisible(true);
		timer.start();
		
	}
}
