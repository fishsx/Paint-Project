package eg.edu.alexu.csd.oop.draw.cs62_67.view;

import javafx.scene.control.ScrollPane;

import javax.swing.*;
import java.awt.*;

public class JsonPreview extends JFrame {

	public JsonPreview(String context) {
		setResizable(false);
		setTitle("Json Preview");
		setSize(500, 400);
		setBackground(new Color(245, 246, 247));

		JTextArea content = new JTextArea(context);

		JScrollPane jScrollPane = new JScrollPane(content);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		setLocation(300, 100);
		setVisible(true);


	}

}
