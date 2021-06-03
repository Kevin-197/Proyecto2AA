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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
 
public class Fractal extends JComponent {

    private int nivel;
    private double[] decrecimientoL;
    private double longitud;
    private double[] decrecimientoD;
    private double diametro;
    private double[] angulo;
    private int[] ramas;
    private BufferedImage img = new BufferedImage(600,600,BufferedImage.TYPE_INT_ARGB);
    private Graphics2D imgG = img.createGraphics();
    

    public Fractal(int nivel, double[] decrecimientoL, double longitud, double[] decrecimientoD, double diametro, double[] angulo, int[] ramas) {
        this.nivel = nivel;
        this.decrecimientoL = decrecimientoL;
        this.longitud = longitud;
        this.decrecimientoD = decrecimientoD;
        this.diametro = diametro;
        this.angulo = angulo;
        this.ramas = ramas;
    }

    public int getNivel() {
        return nivel;
    }

    public double[] getDecrecimientoL() {
        return decrecimientoL;
    }

    public double getLongitud() {
        return longitud;
    }

    public double[] getDecrecimientoD() {
        return decrecimientoD;
    }

    public double getDiametro() {
        return diametro;
    }

    public double[] getAngulo() {
        return angulo;
    }

    public int[] getRamas() {
        return ramas;
    }

    public Graphics2D getImgG() {
        return imgG;
    }
    
    
    
    
    private void drawTree(Graphics g,Graphics2D g3, int x1, int y1, double angle, int nivel, double[] decrecimientoL, double longitud, double[] decrecimientoD, double diametro, double[] angulo, int[] ramas) {
        Graphics2D g2 = (Graphics2D)g; 
        if (nivel <= 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * longitud * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * longitud * 10.0);
        diametro = diametro-((((new Random()).nextInt((int) ((decrecimientoD[1]-decrecimientoD[0])+1))+decrecimientoD[0])*diametro) / 100);
        longitud = longitud-((((new Random()).nextInt((int) ((decrecimientoL[1]-decrecimientoL[0])+1))+decrecimientoL[0])*longitud) / 100);
        g3.setStroke(new BasicStroke((float) diametro));
        g3.drawLine(x1, y1, x2, y2);
        g2.setStroke(new BasicStroke((float) diametro));
        g2.drawLine(x1, y1, x2, y2);
        int Cramas = ((new Random()).nextInt((int) ((ramas[1]-ramas[0])+1))+ramas[0]);
        if(Cramas == 1){
            drawTree(g,g3, x2, y2, angle, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
        }
        else{
            double ag = ((new Random()).nextInt((int) ((angulo[1]-angulo[0])+1))+angulo[0]);
            if(Cramas%2 == 0){
                ag = ((new Random()).nextInt((int) ((angulo[1]-angulo[0])+1))+angulo[0])/2;
            }
            for (int i = 0; i < (Cramas/2); i++){

                drawTree(g,g3, x2, y2, angle + ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                drawTree(g,g3, x2, y2, angle - ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                if(Cramas%2 != 0){
                    drawTree(g,g3, x2, y2, angle, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                }

                ag = ag + (((new Random()).nextInt((int) ((angulo[1]-angulo[0])+1))+angulo[0])); 
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.BLACK);
        this.imgG.setColor(Color.WHITE);
        this.imgG.fillRect(0, 0, img.getWidth(), img.getHeight());
        this.imgG.setColor(Color.BLACK);
        drawTree(g,this.imgG, 300, 580, -90, this.nivel, this.decrecimientoL, this.longitud, this.decrecimientoD, this.diametro, this.angulo, this.ramas);
        this.imgG.dispose();
    }
    
    
    public double Fitness(String Url) throws IOException{
        int nota = 0;
        //BufferedImage image;
        //image = ImageIO.read(getClass().getResource(Url));
        BufferedImage image2;
        image2 = this.img;
        int[][] array2D = new int[image2.getWidth()][image2.getHeight()];
        for (int xPixel = 0; xPixel < image2.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image2.getHeight(); yPixel++) {
                int color = image2.getRGB(xPixel, yPixel);
                if(color==Color.WHITE.getRGB()){
                    //nota--;
                }else{
                    //System.out.println(Color.WHITE.getRGB());
                    nota++;
                }
            }
        }
        return nota;
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

