package eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs62_67.model.MyShape;

public class Ellipse extends MyShape{
	private Map<String, Double> properties =  new HashMap<>();
	public static final String X_KEY = "xAxis";
	public static final String Y_KEY = "yAxis";

	public Ellipse() {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put("stroke",(double) 3.0f);
		this.properties.put(X_KEY, 0.0);
		this.properties.put(Y_KEY, 0.0);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
	}
	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
	}

	@Override
	//common
	public Map<String, Double> getProperties() {
		return this.properties;
	}

	@Override
	public void draw(Graphics canvas) {
		Graphics2D g2 = (Graphics2D) canvas;
		double stroke = properties.get("stroke");
		g2.setStroke(new BasicStroke((float) stroke));

		Point position = getPosition();

		double x = getProperties().get(X_KEY);
		double y = getProperties().get(Y_KEY);
		g2.setColor(getColor());
		g2.drawOval(position.x, position.y, (int)x, (int)y);
		g2.setColor(getFillColor());
		g2.fillOval(position.x, position.y, (int)x, (int)y);
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new Ellipse();
        clonedShape.setColor(this.getColor());
        clonedShape.setFillColor(this.getFillColor());
        clonedShape.setPosition(this.getPosition());
        Map<String, Double> newprop = new HashMap<String,Double>();
        for (Map.Entry s: this.properties.entrySet()){
            String key = (String) s.getKey(); 
            Double value = (Double) s.getValue();
        	newprop.put(key, value);
        }
        clonedShape.setProperties(newprop);
        return clonedShape;	
    }
}
