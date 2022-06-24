package eg.edu.alexu.csd.oop.draw.cs62_67.view;

import javax.swing.*;
import java.awt.*;

public class JsonPreview extends JFrame {

	public JsonPreview(String context) {
		setResizable(false);
		setTitle("Json Preview");
		setSize(600, 800);
		setBackground(new Color(245, 246, 247));

		JLabel content = new JLabel(context);
		content.setVerticalAlignment(SwingConstants.TOP);
		content.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(content, BorderLayout.CENTER);
		setLocation(300, 100);
		setVisible(true);


	}

}
