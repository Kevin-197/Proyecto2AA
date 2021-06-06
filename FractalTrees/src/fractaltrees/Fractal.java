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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private double nota;

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

    public BufferedImage getImg() {
        return img;
    }

    public double getNota() {
        return nota;
    }
    
    
    
    
    private void drawTree(Graphics2D g3, int x1, int y1, double angle, int nivel, double[] decrecimientoL, double longitud, double[] decrecimientoD, double diametro, double[] angulo, int[] ramas) {
        if (nivel <= 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * longitud * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * longitud * 10.0);
        diametro = diametro-((((new Random()).nextInt((int) ((decrecimientoD[1]-decrecimientoD[0])+1))+decrecimientoD[0])*diametro) / 100);
        longitud = longitud-((((new Random()).nextInt((int) ((decrecimientoL[1]-decrecimientoL[0])+1))+decrecimientoL[0])*longitud) / 100);
        g3.setStroke(new BasicStroke((float) diametro));
        g3.drawLine(x1, y1, x2, y2);
        int Cramas = ((new Random()).nextInt((int) ((ramas[1]-ramas[0])+1))+ramas[0]);
        if(Cramas == 1){
            drawTree(g3, x2, y2, angle, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
        }
        else{
            double ag = ((new Random()).nextInt((int) ((angulo[1]-angulo[0])+1))+angulo[0]);
            if(Cramas%2 == 0){
                ag = ((new Random()).nextInt((int) ((angulo[1]-angulo[0])+1))+angulo[0])/2;
            }
            for (int i = 0; i < (Cramas/2); i++){

                drawTree(g3, x2, y2, angle + ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                drawTree(g3, x2, y2, angle - ag, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                if(Cramas%2 != 0){
                    drawTree(g3, x2, y2, angle, nivel - 1, decrecimientoL, longitud, decrecimientoD, diametro, angulo, ramas);
                }

                ag = ag + (((new Random()).nextInt((int) ((angulo[1]-angulo[0])+1))+angulo[0])); 
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.BLACK);
        g.drawImage(this.img, 0, 0, null);
        
    }
    public void pintar() {
        this.imgG.setColor(Color.WHITE);
        this.imgG.fillRect(0, 0, img.getWidth(), img.getHeight());
        this.imgG.setColor(Color.BLACK);
        drawTree(this.imgG, 300, 580, -90, this.nivel, this.decrecimientoL, this.longitud, this.decrecimientoD, this.diametro, this.angulo, this.ramas);
    }
    
    
    public double SubFitness(String Url, int multX, int multY) throws IOException{
        double nota = 0;
        BufferedImage image;
        image = ImageIO.read(getClass().getResourceAsStream("Images/Arboles/Arbol3.bmp"));
        BufferedImage image2;
        image2 = this.img;
        int RangeX = 150*multX;
        int RangeY = 150*multY;
        for (int xPixel = RangeX-150; xPixel < RangeX; xPixel++) {
            for (int yPixel = RangeY-150; yPixel < RangeY; yPixel++) {
                int color2 = image2.getRGB(xPixel, yPixel);
                int color1 = image.getRGB(xPixel, yPixel);
                if(color1==Color.WHITE.getRGB() && color2==Color.WHITE.getRGB()){
//                    System.out.println("blancos");
                    nota = nota+0.5;
                }else if(color1==Color.WHITE.getRGB() && color2!=Color.WHITE.getRGB()){
//                    System.out.println("diferentes1");
                    nota=nota-3;
                }else if(color1!=Color.WHITE.getRGB() && color2==Color.BLACK.getRGB()){
                    //System.out.println("negros");
                    nota = nota +3;
                }else if(color1!=Color.WHITE.getRGB() && color2==Color.WHITE.getRGB()){
//                    System.out.println("diferentes2");
                    nota= nota-2;
                }
            }
        }
        return nota;
    }
    
    public double Fitness(String Url) throws IOException{
        double notaF = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                notaF = notaF + SubFitness(Url, i, j);
                //System.out.println(i+"  "+j);
            }
        }
        
        notaF = notaF/16;
        this.nota=notaF;
        return notaF;
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

