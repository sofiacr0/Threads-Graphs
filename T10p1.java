/*
Desarrollar una aplicación que muestre una gráfica de pastel generada a partir de las ventas de
los  4 departamentos de una empresa.
+ La gráfica se debe de actualizar cada 5 segundos.
+ Los valores de las ventas deben estar en el rango 100 - 1500 para cada departamento.
+ Cada segmento de la gráfica debe tener un color diferente.
+ Utilizar las clases de Java 2D (Graphics2D)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class T10p1 extends JComponent implements Runnable {
    Thread random;
    double d1ventas, rangoDep2, d2ventas, rangoDep3, d3ventas, rangoDep4, d4ventas;
    int d1v,d2v,d3v,d4v;
    public T10p1(){
        random = new Thread(this);
        random.start();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Ventas de 4 departamentos (gráfica de pastel)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(300, 300);

        prueba pie = new prueba();
        frame.add(pie);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        while(true){
            //valores de ventas aleatorias en un rango de 100 a 1500
            d1v = (100+(int)(Math.random()*((1500-100)+1)));
            d2v = (100+(int)(Math.random()*((1500-100)+1)));
            d3v = (100+(int)(Math.random()*((1500-100)+1)));
            d4v = (100+(int)(Math.random()*((1500-100)+1)));
            //rebanada 1 porcentaje
            d1ventas = (d1v*100)/1500;
            //rebanada 2 porcentaje
            d2ventas = (d2v*100)/1500;
            ////rebanada 3 porcentaje
            d3ventas = (d3v*100)/1500;
            //rebanada 4 porcentaje
            d4ventas = (d4v*100)/1500;
            //calcular inicio de rebanada
            rangoDep2 = (int) (0 + d1ventas);
            rangoDep3 = (int) (rangoDep2 + d2ventas);
            rangoDep4 = (int) (rangoDep3 + d3ventas);
            repaint();
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
        arc.setFrame(getWidth()/4,getHeight()/4,150,150);

        arc.setAngleStart(0);
        arc.setAngleExtent(d1ventas);
        g2.setColor(Color.green);
        g2.draw(arc);
        g2.fill(arc);

        arc.setAngleStart(rangoDep2);
        arc.setAngleExtent(d2ventas);
        g2.setColor(Color.blue);
        g2.draw(arc);
        g2.fill(arc);

        arc.setAngleStart(rangoDep3);
        arc.setAngleExtent(d3ventas);
        g2.setColor(Color.red);
        g2.draw(arc);
        g2.fill(arc);

        arc.setAngleStart(rangoDep4);
        arc.setAngleExtent(d4ventas);
        g2.setColor(Color.pink);
        g2.draw(arc);
        g2.fill(arc);
    }
}
