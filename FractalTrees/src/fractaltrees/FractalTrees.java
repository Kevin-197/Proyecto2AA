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
//        JFrame frame =new JFrame();
//        frame.setSize(700,700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Fractal arbol = new Fractal(7,new double[] {30,45}, 20, new double[] {30,45}, 8, new double[] {25,45}, new int[] {1,5});
//        frame.add(arbol);
//        frame.setVisible(true); 
//        String Url2= "/fractaltrees/Images/Arboles/Arbol3.bmp";
//        arbol.pintar();
//        System.out.println(arbol.Fitness(Url2));
        
    }
    
    
    
}
