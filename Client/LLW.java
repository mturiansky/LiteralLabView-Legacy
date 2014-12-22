import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LLW {
	JFrame gui;
	JLabel label1;
	JLabel label2;
	JTextField text1;
	JTextField text2;
	JButton start;
	Timer time;
	int duration;
	int interval;

	public LLW() {
		System.out.println("[*] Creating gui");

		gui = new JFrame();
		gui.setTitle("LiteralLabView");
		gui.setSize(300,150);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);

		gui.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		label1 = new JLabel("Duration (s):", SwingConstants.CENTER);
		c.insets = new Insets(5,10,5,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gui.add(label1, c);

		text1 = new JTextField("86400");
		text1.setPreferredSize(new Dimension(100, 20));
		c.gridx = 1;
		gui.add(text1, c);

		label2 = new JLabel("Post Interval (s):", SwingConstants.CENTER);
		c.insets = new Insets(5,10,5,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		gui.add(label2, c);

		text2 = new JTextField("300");
		text2.setPreferredSize(new Dimension(100, 20));
		c.gridx = 1;
		gui.add(text2, c);

		start = new JButton("Run");
		c.insets = new Insets(5,50,5,50);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		gui.add(start, c);

		gui.setVisible(true);

		System.out.println("[+] Gui created");

		start.addActionListener(new Start());
	}

	private class Start implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("[*] Starting timer");
			duration = Integer.parseInt(text1.getText());
			interval = Integer.parseInt(text2.getText());
			time = new Timer(interval*1000, new Poster());
			time.start();
		}

		private class Poster implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (duration >= 0) {
					PhotoCapture pc = new PhotoCapture();
					pc.takeScreenShot();
					pc.takeCameraShot();
					PostCreator p = new PostCreator("http://127.0.0.1:5000/upload");
					p.send();
					p.cleanup();
					duration -= interval;
				} else {
					System.out.println("[+] Post sequence complete");
					time.stop();
				}
			}
		}
	}

	public static void main(String args[]) {
		System.out.println("[+] Program booted up.");
		new LLW();
	}
}
