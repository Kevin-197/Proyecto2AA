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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
 
public class Fractal extends JFrame {
 
    public Fractal() {
        super("Fractal Tree");
        setBounds(100, 100, 1600, 1200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        
        double ag = angulo*((int)(ramas/2));
        for (int i = 0; i < (ramas); i++) {
            
            drawTree(g, x2, y2, angle + ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
            
            if(ramas%2 != 0){
               ag = ag - (angulo); 
            }
            else{
               ag = ag - (angulo*2); 
            }
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        drawTree(g, 800, 1000, -90, 4, 30, 14, 35, 15, 30, 3);
    }
 
    public static void main(String[] args) {
        new Fractal().setVisible(true);
    }
}

