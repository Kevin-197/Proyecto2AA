/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractaltrees;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author eidur
 */
public class FractalTrees {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        MainWindow pantalla = new MainWindow();
        pantalla.setVisible(true); 
//        Fractal arbol = new Fractal(4,new double[] {15,25}, 10, new double[] {15,25}, 8, new double[] {15,35}, new int[] {2,4});
//        String Url2= "Arbol1.bmp";
//        arbol.paint(arbol.getImgG());
//        System.out.println(arbol.Fitness(Url2));
    }
    
    
    
}
