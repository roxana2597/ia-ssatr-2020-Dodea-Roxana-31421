/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.catalog;

/**
 *

 */
public class Elevi {
    private String NUME;
    private String PRENUME;
    private double NOTA;

    public Elevi(String NUME, double NOTA,String PRENUME) {
        this.NUME = NUME;
        this.NOTA = NOTA;
        this.PRENUME = PRENUME;
    }

    public String getNUME() {
        return NUME;
    }
    
    public String getPRENUME() {
        return PRENUME;
    }
    
    public double getNOTA() {
        return NOTA;
    }

    public void setNUME(String NUME) {
        this.NUME = NUME;
    }

    public void setPRENUME(String PRENUME) {
        this.PRENUME = PRENUME;
    }
    
    public void setNOTA(double NOTA) {
        this.NOTA = NOTA;
    }

    @Override
    public String toString() {
        return "ELEVI{" + "NUME=" + NUME + ", PRENUME=" + PRENUME + ", NOTA=" + NOTA + '}';
    }
}
