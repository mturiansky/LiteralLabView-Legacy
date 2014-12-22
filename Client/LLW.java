import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LLW {
	public LLW() {
		JFrame gui = new JFrame();
		gui.setTitle("LiteralLabView");
		gui.setSize(300,150);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);

		gui.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel label1 = new JLabel("Duration (s):", SwingConstants.CENTER);
		c.insets = new Insets(5,10,5,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gui.add(label1, c);

		JTextField text1 = new JTextField("86400");
		text1.setPreferredSize(new Dimension(100, 20));
		c.gridx = 1;
		gui.add(text1, c);

		JLabel label2 = new JLabel("Post Interval (s):", SwingConstants.CENTER);
		c.insets = new Insets(5,10,5,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		gui.add(label2, c);

		JTextField text2 = new JTextField("300");
		text2.setPreferredSize(new Dimension(100, 20));
		c.gridx = 1;
		gui.add(text2, c);

		JButton start = new JButton("Run");
		c.insets = new Insets(5,50,5,50);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		gui.add(start, c);

		gui.setVisible(true);
	}

	public static void main(String args[]) {
		System.out.println("[+] Program booted up.");
		new LLW();
	}
}
