package eg.edu.alexu.csd.oop.draw.cs62_67.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 业务自用的展示json model
 */
public class PreviewJson {
    public List<PreviewShape> previewShapes;

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final double DEFAULT_RATE =  0.15d;
    public static final double DEFAULT_SCORE =  0.9d;


    public PreviewJson (List<Shape> shapes){
        this.previewShapes = new ArrayList<>();
        if (shapes!=null &&!shapes.isEmpty()) {
            for (Shape shape : shapes) {
                double pointX = shape.getPosition().getX();
                double pointY = shape.getPosition().getY();
                Double xAxis = shape.getProperties().get("xAxis");
                Double yAxis = shape.getProperties().get("yAxis");
                double maxPointX = 0.0;
                double maxPointY = 0.0;

                if (Objects.nonNull(yAxis)) {
                    maxPointX = pointX + yAxis;
                }
                if (Objects.nonNull(xAxis)) {
                    maxPointY = pointY + xAxis;
                }

                PreviewShape previewShape = new PreviewShape();
                previewShape.setRate(DEFAULT_RATE);
                previewShape.setSocre(DEFAULT_SCORE);
                previewShape.setLabel(shape.getLabel());
                previewShape.setLocation(Stream.of(pointX,pointY,maxPointX,maxPointY).collect(Collectors.toList()));

                this.previewShapes.add(previewShape);
            }
        }
    }

    public PreviewJson (String json) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, PreviewShape.class);
            this.previewShapes = objectMapper.readValue(json,javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String asPrettyJsonText() {
        try {
           return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.previewShapes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Shape> parse() {
        List<PreviewShape> previewShapes = this.previewShapes;
        ArrayList<Shape> result = new ArrayList<>();
        if (previewShapes!=null && !previewShapes.isEmpty()) {
            for (PreviewShape preShape : previewShapes) {
                List<Double> location = preShape.getLocation();
                String label = preShape.getLabel();
                //only support rectangle
                Shape shape = new Rectangle();
                int pointX = location.get(0).intValue();
                int pointY = location.get(1).intValue();
                int maxPointX = location.get(2).intValue();
                int maxPointY = location.get(3).intValue();

                Map<String, Double> props = new LinkedHashMap<>();
                props.put("yAxis", (double) (maxPointX - pointX));
                props.put("xAxis", (double) (maxPointY - pointY));

                props.put("stroke",(double) 3.0f);

                shape.setPosition(new Point(pointX, pointY));
                shape.setLabel(label);
                shape.setProperties(props);
                result.add(shape);
            }
        }

        return result;
    }

    public static class PreviewShape {
        private double socre;
        private double rate;
        private String label;
        private List<Double> location;

        public double getSocre() {
            return socre;
        }

        public void setSocre(double socre) {
            this.socre = socre;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public List<Double> getLocation() {
            return location;
        }

        public void setLocation(List<Double> location) {
            this.location = location;
        }
    }
}
