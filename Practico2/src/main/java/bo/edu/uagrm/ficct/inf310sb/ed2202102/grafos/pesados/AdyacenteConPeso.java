/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import java.util.Objects;

/**
 *
 * @author Andres
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{
    private double peso;
    private Integer indiceDeVertice;

    public AdyacenteConPeso(Integer indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }

    public AdyacenteConPeso(Integer indiceDeVertice, double peso) {
        this.peso = peso;
        this.indiceDeVertice = indiceDeVertice;
    }
    

    @Override
    public int compareTo(AdyacenteConPeso elOtroAdyacenteConPeso) {
        Integer esteVertice = this.indiceDeVertice;
        Integer elOtroVertice =  elOtroAdyacenteConPeso.indiceDeVertice;
        return esteVertice.compareTo(elOtroVertice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indiceDeVertice,peso);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AdyacenteConPeso other = (AdyacenteConPeso) obj;
        return indiceDeVertice == other.indiceDeVertice;
    }



    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Integer getIndiceDeVertice() {
        return indiceDeVertice;
    }

    public void setIndiceDeVertice(Integer indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }
    
    
}
