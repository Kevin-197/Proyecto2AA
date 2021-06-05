/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractaltrees;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author eidur
 */
public class GeneticAlgorithm {
    private int mutationindex;
    private int generationsize;
    private int totalGenerations;
    private String url;
    private int diffcrecimientoL;
    private int diffcrecimientoD;
    private int diffangulo;
    private int difframas;
    private ArrayList<Generation> bestGeneration;

    public GeneticAlgorithm(int mutationindex, int generationsize, int totalGenerations, String url) {
        this.mutationindex = mutationindex;
        this.generationsize = generationsize;
        this.totalGenerations = totalGenerations;
        this.url = url;
    }

    private int MaxRange6(int min, int max) {
        if(min+max<63) 
            return min+max;
        else 
            return 63;
    }
    private int MaxRange3(int min, int max) {
        if(min+max<7) 
            return min+max;
        else 
            return 7;
    }

    public BinaryPair crossChromosomes(String cromosoma1, String cromosoma2){
       
       int breaklimit;

       Random ran = (new Random());
       breaklimit = ran.nextInt(cromosoma1.length()-2)+1;
       System.out.println(" Pivot: "+breaklimit);
       return new BinaryPair(cromosoma1.substring(0, breaklimit)+cromosoma2.substring(breaklimit,cromosoma2.length() ), cromosoma2.substring(0, breaklimit)+cromosoma1.substring(breaklimit,cromosoma1.length() ));
    }
    
    public Fractal BinaryToTree(String toConvert){

        return new Fractal(
               Integer.parseInt(toConvert.substring(0, 3),2),
               new double[] {(Integer.parseInt(toConvert.substring(3, 9),2)),Integer.parseInt(toConvert.substring(3, 9),2) },
               Integer.parseInt(toConvert.substring(9, 13),2),
               new double[] {Integer.parseInt(toConvert.substring(13, 19),2),Integer.parseInt(toConvert.substring(13, 19),2)},
               Integer.parseInt(toConvert.substring(19, 23),2),
               new double[] {Integer.parseInt(toConvert.substring(23, 29),2), Integer.parseInt(toConvert.substring(23, 29),2)},
               new int[] {Integer.parseInt(toConvert.substring(29, 32),2),Integer.parseInt(toConvert.substring(29, 32),2)});

    }
    public String TreeToBinary(Fractal toConvert){
        /*
        
        String binario1 =  Integer.toBinaryString(cromosoma1);
        String binario2 =  Integer.toBinaryString(cromosoma2);
        System.out.println("Binartio1: "+binario1+" Bin 2: "+ binario2);
        */
        String result = String.format("%3s", Integer.toBinaryString(toConvert.getNivel())).replaceAll(" ", "0")+ // nivel
        String.format("%6s", Integer.toBinaryString((int) toConvert.getDecrecimientoL()[0])).replaceAll(" ", "0")+ // decrecimientoL + 64
        String.format("%4s", Integer.toBinaryString((int) toConvert.getLongitud())).replaceAll(" ", "0")+ //longitud
        String.format("%6s", Integer.toBinaryString((int) toConvert.getDecrecimientoD()[0])).replaceAll(" ", "0")+// decrecimientoD +  64
        String.format("%4s", Integer.toBinaryString((int) toConvert.getDiametro())).replaceAll(" ", "0")+ //diametro
        String.format("%6s", Integer.toBinaryString((int) toConvert.getAngulo()[0])).replaceAll(" ", "0")+ //angulo + 64
        String.format("%3s", Integer.toBinaryString(toConvert.getRamas()[0])).replaceAll(" ", "0"); //ramas + 8

        return result;

    }
    public void run(int[] ranRamas, double[] ranAngulos, int[] ranProfundidad, double[] ranDecrecimientoL, double[] ranDecrecimientoD, double[] ranDiametro, double[] ranLongitud) throws IOException{
        /*
        
        String binario1 =  Integer.toBinaryString(cromosoma1);
        String binario2 =  Integer.toBinaryString(cromosoma2);
        System.out.println("Binartio1: "+binario1+" Bin 2: "+ binario2);
        */
        Fractal Tree1;
        ArrayList<Generation> Generacion = new ArrayList<Generation >();
        int randomnivel, randomlongitud, randomdiametro, randomangulo1,randomangulo2, randomramas1,randomramas2, randomDecL1, randomDecL2, randomDecD1, randomDecD2, maxFitness;
        Random randBuilder = new Random();
        ArrayList<Generation> MatrizIndividuos;
        ArrayList<Generation> BestPicks = new ArrayList<Generation>();
        double TotalNotas = 0;
        ArrayList<Double> NotasNormalizadas = new ArrayList<Double>();
        ArrayList<Fractal> CandidatosCruce = new ArrayList<Fractal>();
        this.diffangulo=(int)ranAngulos[1]-(int)ranAngulos[0];
        this.difframas=(int)ranAngulos[1]-(int)ranAngulos[0];
        this.diffcrecimientoD=(int)ranDecrecimientoD[1]-(int)ranDecrecimientoD[0];
        this.diffcrecimientoL=(int)ranDecrecimientoL[1]-(int)ranDecrecimientoL[0];
        for(int i =0; i<this.generationsize;i++){
            randomnivel = randBuilder.nextInt(ranProfundidad[1]-ranProfundidad[0])+ranProfundidad[0];
            randomlongitud = randBuilder.nextInt((int)ranLongitud[1]-(int)ranLongitud[0])+(int)ranLongitud[0];
            randomdiametro = randBuilder.nextInt((int)ranDiametro[1]-(int)ranDiametro[0])+(int)ranDiametro[0];
            randomangulo1 = randBuilder.nextInt((int)ranAngulos[1]-(int)ranAngulos[0])+(int)ranAngulos[0];
            randomramas1 = randBuilder.nextInt((int)ranRamas[1]-(int)ranRamas[0])+(int)ranRamas[0];
            randomDecL1 = randBuilder.nextInt((int)ranDecrecimientoL[1]-(int)ranDecrecimientoL[0])+(int)ranDecrecimientoL[0];
            randomDecD1 = randBuilder.nextInt((int)ranDecrecimientoD[1]-(int)ranDecrecimientoD[0])+(int)ranDecrecimientoD[0];
            
            randomangulo2 = MaxRange6(randomangulo1, this.diffangulo);
            randomramas2 = MaxRange3(randomramas1, this.difframas);
            randomDecL2 =  MaxRange6(randomDecL1, this.diffcrecimientoL);
            randomDecD2 = MaxRange6(randomDecD1, this.diffcrecimientoD);
            
            
            Tree1 = new Fractal(randomnivel, new double[] {randomDecL1,randomDecL2} ,randomlongitud,new double[]{randomDecD1,randomDecD2}, randomdiametro,new double[] {randomangulo1,randomangulo2},new int[] {randomramas1,randomramas2});
            Generacion.add(new Generation(Tree1, new StringBuilder(TreeToBinary(Tree1))));
        }
        for(int j=0; j<this.totalGenerations;j++){
            
            //fitness CON GENERACIÓN
             Generacion.get(0).Tree.Fitness(this.url) ;
            maxFitness=0;
            for(int i=0; i<this.generationsize; i++){
                double currentNota = Generacion.get(i).Tree.Fitness(this.url);
                TotalNotas = TotalNotas+currentNota;
                if(currentNota >Generacion.get(maxFitness).Tree.getNota()){
                    maxFitness=i;
                }
                
            }
            
            
            
            //Extraer el mejor y ponerlo en bestGeneration
            BestPicks.add(Generacion.get(maxFitness));
            
            //normalizar notas y hacer selección de individuo aleatorio según notas normalizadas
            for (int i = 0; i < this.generationsize; i++) {
                double currentNotaN = ((Generacion.get(i).Tree.getNota())*100)/TotalNotas;
                System.out.println(currentNotaN);
                NotasNormalizadas.add(i,currentNotaN);
            }
            for (int i = 0; i < NotasNormalizadas.size(); i++) {
                double random = Math.random();
                double prop = 0;
                for (int k = 0; k < NotasNormalizadas.size(); k++) {
                    prop = prop + NotasNormalizadas.get(k)/100;
                    if(random < prop){
                        CandidatosCruce.add(Generacion.get(k).Tree);
                        break;
                    }
                }
            }
            TotalNotas=0;
            NotasNormalizadas.clear();
            
            Collections.shuffle(Generacion); //cambiar a  aletorio
        
        
        
            //System.out.println("Binartio1: "+sty.substring(4,sty.length()));
            int indicedemutacion = this.mutationindex;

            MatrizIndividuos = new ArrayList<Generation >();

            BinaryPair NewPareja;
            for(int i =0; i<Generacion.size();i=i+2){ //cambiar a  aletorio


                //for de parejas
                String c1= TreeToBinary(Generacion.get(i).getTree());//cambiar a  aletorio
                String c2= TreeToBinary(Generacion.get(i+1).getTree());//cambiar a  aletorio


                
                NewPareja = crossChromosomes(c1,c2);
                MatrizIndividuos.add(new Generation(new StringBuilder(NewPareja.getNewIndividual1Chromosome()),new StringBuilder(c1), new StringBuilder(c2), Generacion.get(i).getTree(), Generacion.get(i+1).getTree() ) ); 
               //cambiar a  aletorio
                MatrizIndividuos.add(new Generation(new StringBuilder(NewPareja.getNewIndividual2Chromosome()),new StringBuilder(c1), new StringBuilder(c2), Generacion.get(i).getTree(), Generacion.get(i+1).getTree() ) ); 
                //System.out.println(Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(0, 3),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(3, 9),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(9, 13),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(13, 19),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(19, 23),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(23, 29),2)+","+Integer.parseInt(NewPareja.getNewIndividual1Chromosome().substring(29, 32),2));  
                //cambiar a  aletorio
            }

            //cambiar bits indice de mutación
            int mutationnum=indicedemutacion;
            int row, column;
            Random rand= (new Random());
            while(mutationnum>0){
                row =rand.nextInt(MatrizIndividuos.size());
                column =rand.nextInt(32);
                if(MatrizIndividuos.get(row).getChromosome().charAt(column)=='1')
                    MatrizIndividuos.get(row).getChromosome().setCharAt(column, '0');
                else
                    MatrizIndividuos.get(row).getChromosome().setCharAt(column, '1');
                mutationnum--;
                System.out.println(row+", "+column);
            }
            //System.out.println(Integer.parseInt(MatrizIndividuos.get(0).substring(0, 3),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(3, 9),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(9, 13),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(13, 19),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(19, 23),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(23, 29),2)+","+Integer.parseInt(MatrizIndividuos.get(0).substring(29, 32),2));  

            for(int i =0; i<Generacion.size();i=i+2){ 
                MatrizIndividuos.get(i).setTree(BinaryToTree(MatrizIndividuos.get(i).getChromosome().toString()));
                Generacion.set(i, MatrizIndividuos.get(i));
            }
        }
        
        // return  Bestpicks
    }
            
    
}
