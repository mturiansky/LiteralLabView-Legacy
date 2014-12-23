import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LLW {
	JFrame gui;
	GridBagConstraints c;
	JLabel label1, label2, label3, label4, label5, name;
	JTextField proj_name, text1, text2;
	JButton start;
	JProgressBar bar;
	Timer time;
	int duration, interval, prog;
	String savedname;

	public LLW() {
		System.out.println("[*] Creating gui");

		gui = new JFrame();
		gui.setTitle("LiteralLabView");
		gui.setSize(400,175);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);

		gui.setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		name = new JLabel("Project Name:", SwingConstants.CENTER);
		c.insets = new Insets(5,10,5,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gui.add(name, c);

		proj_name = new JTextField("");
		proj_name.setPreferredSize(new Dimension(150,20));
		c.gridx = 1;
		gui.add(proj_name, c);

		label1 = new JLabel("Duration (s):", SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 1;
		gui.add(label1, c);

		text1 = new JTextField("86400");
		text1.setPreferredSize(new Dimension(150, 20));
		c.gridx = 1;
		gui.add(text1, c);

		label2 = new JLabel("Post Interval (s):", SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 2;
		gui.add(label2, c);

		text2 = new JTextField("300");
		text2.setPreferredSize(new Dimension(150, 20));
		c.gridx = 1;
		gui.add(text2, c);

		start = new JButton("Run");
		c.insets = new Insets(5,50,5,50);
		c.gridx = 0;
		c.gridy = 3;
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
			savedname = proj_name.getText();

			gui.remove(proj_name);
			gui.remove(text1);
			gui.remove(text2);

			label5 = new JLabel(savedname, SwingConstants.CENTER);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5,10,5,10);
			c.gridwidth = 1;
			c.gridx = 1;
			c.gridy = 0;
			gui.add(label5, c);

			label3 = new JLabel(Integer.toString(duration), SwingConstants.CENTER);
			c.gridy = 1;
			gui.add(label3, c);

			label4 = new JLabel(Integer.toString(interval), SwingConstants.CENTER);
			c.gridy = 2;
			gui.add(label4, c);

			start.removeActionListener(this);
			gui.remove(start);

			bar = new JProgressBar(SwingConstants.HORIZONTAL, 0, duration/interval);
			bar.setPreferredSize(new Dimension(350, 20));
			bar.setStringPainted(true);
			prog = 0;
			bar.setValue(prog);
			c.insets = new Insets(5,0,5,0);
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 2;
			gui.add(bar, c);

			gui.validate();
			gui.repaint();

			time = new Timer(interval*1000, new Poster());
			time.start();
		}

		private class Poster implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (duration > 0) {
					PhotoCapture pc = new PhotoCapture();
					pc.takeScreenShot();
					pc.takeCameraShot();
					PostCreator p = new PostCreator("http://127.0.0.1:5000/upload", savedname);
					p.send();
					p.cleanup();
					duration -= interval;
					bar.setValue(++prog);
				}

				if (duration < interval) {
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
