/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractaltrees;

/**
 *
 * @author kevin
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
 
public class Fractal extends JComponent {

    private int nivel;
    private double decrecimientoL;
    private double longitud;
    private double decrecimientoD;
    private double diametro;
    private double angulo;
    private int ramas;

    public Fractal(int nivel, double decrecimientoL, double longitud, double decrecimientoD, double diametro, double angulo, int ramas) {
        this.nivel = nivel;
        this.decrecimientoL = decrecimientoL;
        this.longitud = longitud;
        this.decrecimientoD = decrecimientoD;
        this.diametro = diametro;
        this.angulo = angulo;
        this.ramas = ramas;
    }
    
    
    private void drawTree(Graphics g, int x1, int y1, double angle, int nivel, double decrecimientoL, double longitud, double decrecimientoD, double diametro, double angulo, int ramas) {
        Graphics2D g2 = (Graphics2D)g; 
        if (nivel <= 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * longitud * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * longitud * 10.0);
        
        diametro = diametro-((decrecimientoD*diametro) / 100);
        longitud = longitud-((decrecimientoL*longitud) / 100);
        g2.setStroke(new BasicStroke((float) diametro));
        g2.drawLine(x1, y1, x2, y2);
        
        if(ramas == 1){
            drawTree(g, x2, y2, angle, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
        }
        else{
            double ag = angulo;
            if(ramas%2 == 0){
                ag = angulo/2;
            }
            for (int i = 0; i < (ramas/2); i++){

                drawTree(g, x2, y2, angle + ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                drawTree(g, x2, y2, angle - ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                if(ramas%2 != 0){
                    drawTree(g, x2, y2, angle, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                }

                ag = ag + (angulo); 
            }
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        drawTree(g, 300, 580, -90, this.nivel, this.decrecimientoL, this.longitud, this.decrecimientoD, this.diametro, this.angulo, this.ramas);
    }
    /*
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame =new JFrame();
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Fractal generatedTree = new Fractal();
        frame.add(generatedTree);
        frame.setVisible(true); 
        
    }
    */
}

