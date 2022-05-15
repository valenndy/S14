package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable {

  @FXML
  private Canvas canvas;

  private ArrayList<Double> axisY = new ArrayList<>();
  private ArrayList<Double> axisY = new ArrayList<>();
  private GraphicsContext gc;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    gc = canvas.getGraphicsContext2D();

    paint();
  }

  public void paint() {

    gc.setFill(Color.rgb(360, 360, 360));
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    for (int i = 0; i < 10; i++) {
      axisY.add(50.0 + 10 * i);
      axisY.add(50 * Math.random());
    }

    double[] resX = getMinMax(axisX);

    double minX = resX[0];

    double maxX = resX[1];

    double[] resY = getMinMax(axisY);

    double minY = resY[0];

    double maxY = resY[1];

    double dePx = canvas.getWidth();

    double delDay = maxX - minX;

    double penX = dePx / delDay;

    double inter = penX * minX * (-1);

    double delPy = -canvas.getHeight();

    double deltaError = maxY - minY;

    double penY = delPy / deltaError;

    double interY = penY * maxY * (-1);
    gc.setFill(Color.RED);

    for (int i = 0; i < axisY.size(); i++) {
      gc.fillOval(conversion(axisY.get(i), penX, inter), conversion(axisY.get(i), penY, interY) - 3, 6, 6);
    }

    gc.setStroke(Color.RED);
    gc.setLineWidth(2);

    for (int i = 0; i < axisY.size() - 1; i++) {
    	
      gc.moveTo(conversion(axisY.get(i), penX, inter) + 3, conversion(axisY.get(i), penY, interY) + 3);
      
      gc.lineTo(conversion(axisY.get(i + 1), penX, inter) + 3, conversion(axisY.get(i + 1), penY, interY) + 3);
    }

    gc.stroke();
  }

  public double[] getMinMax(ArrayList<Double> eje) {

    ArrayList<Double> aux = new ArrayList<>();
    
    aux.addAll(eje);
    
    Collections.sort(aux);
    
    double min = aux.get(0);
    
    double max = aux.get(aux.size() - 1);
    
    return new double[] { min, max };
  }

  private double conversion(double a, double b, double c) {

    return b * a + c;
  }

}
