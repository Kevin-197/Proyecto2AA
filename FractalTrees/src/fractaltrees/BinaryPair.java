/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractaltrees;

/**
 *
 * @author eidur
 */
public class BinaryPair {
    private String newIndividual1Chromosome;
    private String newIndividual2Chromosome;

    public BinaryPair(String newIndividual1Chromosome, String newIndividual2Chromosome) {
        this.newIndividual1Chromosome = newIndividual1Chromosome;
        this.newIndividual2Chromosome = newIndividual2Chromosome;
    }

    public String getNewIndividual1Chromosome() {
        return newIndividual1Chromosome;
    }

    public void setNewIndividual1Chromosome(String newIndividual1Chromosome) {
        this.newIndividual1Chromosome = newIndividual1Chromosome;
    }

    public String getNewIndividual2Chromosome() {
        return newIndividual2Chromosome;
    }

    public void setNewIndividual2Chromosome(String newIndividual2Chromosome) {
        this.newIndividual2Chromosome = newIndividual2Chromosome;
    }
    
    
}
