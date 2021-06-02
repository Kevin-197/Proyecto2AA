/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractaltrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author eidur
 */


public class Generation {
    double fitnessGrade;
    Fractal new1;
    Fractal new2;
    Fractal parent1;
    Fractal parent2;
    
    static public BinaryPair crossChromosomes(String cromosoma1, String cromosoma2){
       
       int breaklimit;

       Random ran = (new Random());
       breaklimit = ran.nextInt(cromosoma1.length()-2)+1;
       System.out.println(" Pivot: "+breaklimit);
       return new BinaryPair(cromosoma1.substring(0, breaklimit)+cromosoma2.substring(breaklimit,cromosoma2.length() ), cromosoma2.substring(0, breaklimit)+cromosoma1.substring(breaklimit,cromosoma1.length() ));
    }
    static public String TreeToBinary(Fractal toConvert){
        /*
        
        String binario1 =  Integer.toBinaryString(cromosoma1);
        String binario2 =  Integer.toBinaryString(cromosoma2);
        System.out.println("Binartio1: "+binario1+" Bin 2: "+ binario2);
        */
        String result = String.format("%3s", Integer.toBinaryString(toConvert.getNivel())).replaceAll(" ", "0")+ // nivel
        String.format("%6s", Integer.toBinaryString((int) toConvert.getDecrecimientoL()[0])).replaceAll(" ", "0")+ // decrecimientoL
        String.format("%4s", Integer.toBinaryString((int) toConvert.getLongitud())).replaceAll(" ", "0")+ //longitud
        String.format("%6s", Integer.toBinaryString((int) toConvert.getDecrecimientoD()[0])).replaceAll(" ", "0")+// decrecimientoD
        String.format("%4s", Integer.toBinaryString((int) toConvert.getDiametro())).replaceAll(" ", "0")+ //diametro
        String.format("%6s", Integer.toBinaryString((int) toConvert.getAngulo()[0])).replaceAll(" ", "0")+ //angulo
        String.format("%3s", Integer.toBinaryString(toConvert.getRamas()[0])).replaceAll(" ", "0"); //ramas

        return result;

    }
    static public Fractal BinaryToTree(String toConvert){

        return new Fractal(Integer.parseInt(toConvert.substring(0, 3),2),
                new double[] {(Integer.parseInt(toConvert.substring(3, 9),2)),Integer.parseInt(toConvert.substring(3, 9),2) },
                Integer.parseInt(toConvert.substring(9, 13),2),
                new double[] {Integer.parseInt(toConvert.substring(13, 19),2),Integer.parseInt(toConvert.substring(13, 19),2)},
                Integer.parseInt(toConvert.substring(19, 23),2),
                new double[] {Integer.parseInt(toConvert.substring(23, 29),2), Integer.parseInt(toConvert.substring(23, 29),2)},
                new int[] {Integer.parseInt(toConvert.substring(29, 32),2),Integer.parseInt(toConvert.substring(29, 32),2)});

    }
    public static void main(String[] args) {
        // TODO code application logic here
        //String sty= "Mywar";
        ArrayList<Fractal> Generacion = new ArrayList<Fractal >();
        Fractal Tree1 = new Fractal(4, new double[] {30,30} ,14,new double[]{35,35}, 15,new double[] {30,30},new int[] {23,23});
        Generacion.add(Tree1);
        Tree1 = new Fractal(5,  new double[] {28,28},11,  new double[] {30,30}, 14, new double[] {33,33}, new int[] {2,2});
        Generacion.add(Tree1);
        Tree1 = new Fractal(6,  new double[] {27,27},14, new double[] {27,27}, 15, new double[] {32,32}, new int[] {3,3});
        Generacion.add(Tree1);
        Tree1 = new Fractal(7,  new double[] {30,30},12,  new double[] {34,34}, 16, new double[] {30,30}, new int[] {4,4});
        Generacion.add(Tree1);
        Tree1 = new Fractal(6,  new double[] {29,29},13, new double[] {33,33}, 13, new double[] {31,31}, new int[] {4,4});
        Generacion.add(Tree1);
        Tree1 = new Fractal(4,  new double[] {28,28},15,  new double[] {34,34}, 13, new double[] {32,32}, new int[] {5,5});
        Generacion.add(Tree1);
        Collections.shuffle(Generacion);
        
        
        
        //System.out.println("Binartio1: "+sty.substring(4,sty.length()));
        int indicedemutacion = 4;
        int generationSize =6;
        ArrayList<StringBuilder> MatrizIndividuos = new ArrayList<StringBuilder >();
        
        
        for(int i =0; i<Generacion.size();i=i+2){
            
        
            //for de parejas
            String c1= TreeToBinary(Generacion.get(i));
            String c2= TreeToBinary(Generacion.get(i+1));


            BinaryPair NewPareja;
            NewPareja = crossChromosomes(c1,c2);
            MatrizIndividuos.add(new StringBuilder(NewPareja.getNewIndividual1Chromosome())); 
            MatrizIndividuos.add(new StringBuilder(NewPareja.getNewIndividual2Chromosome())); 
            //System.out.println(Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(0, 3),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(3, 9),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(9, 13),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(13, 19),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(19, 23),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(23, 29),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(29, 32),2));  

        }
        
        //cambiar bits indice de mutación
        int mutationnum=indicedemutacion;
        int row, column;
        Random rand= (new Random());
        while(mutationnum>0){
            row =rand.nextInt(MatrizIndividuos.size());
            column =rand.nextInt(32);
            if(MatrizIndividuos.get(row).charAt(column)=='1')
                MatrizIndividuos.get(row).setCharAt(column, '0');
            else
                MatrizIndividuos.get(row).setCharAt(column, '1');
            mutationnum--;
            System.out.println(row+", "+column);
        }
        //System.out.println(Integer.parseInt(MatrizIndividuos.get(0).substring(0, 3),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(3, 9),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(9, 13),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(13, 19),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(19, 23),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(23, 29),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(29, 32),2));  

        for(int i =0; i<Generacion.size();i=i+2){
            Generacion.set(i, BinaryToTree(MatrizIndividuos.get(i).toString()));
        }
        
        
        
        
    }
}
