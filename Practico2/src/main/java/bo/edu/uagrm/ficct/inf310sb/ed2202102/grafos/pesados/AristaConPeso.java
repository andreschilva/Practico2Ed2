/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

/**
 *
 * @author Andres
 */
public class AristaConPeso implements Comparable<AristaConPeso> {
    private int Origen;
    private int destino;
    private Double costo;

    public AristaConPeso() {
    }
    
    public AristaConPeso(Double costo) {
        this.costo = costo;
    }

    public AristaConPeso(int Origen, int destino) {
        this.Origen = Origen;
        this.destino = destino;
    }

    public AristaConPeso(int Origen, int destino, Double costo) {
        this.Origen = Origen;
        this.destino = destino;
        this.costo = costo;
    }

    public int getOrigen() {
        return Origen;
    }

    public void setOrigen(int Origen) {
        this.Origen = Origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public int compareTo(AristaConPeso laOtraAristaConPeso) {
        Double costoDeEstaArista = this.costo;
        Double costoDeLaOtraArista = laOtraAristaConPeso.costo;
        return costoDeEstaArista.compareTo(costoDeLaOtraArista);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.Origen;
        hash = 31 * hash + this.destino;
        return hash;
    }

    @Override
    public boolean equals(Object otraAristaConPeso) {
        if (this == otraAristaConPeso) {
            return true;
        }
        if (otraAristaConPeso == null) {
            return false;
        }
        if (getClass() != otraAristaConPeso.getClass()) {
            return false;
        }
        final AristaConPeso other = (AristaConPeso) otraAristaConPeso;
        if (this.Origen != other.Origen) {
            return false;
        }
        if (this.destino != other.destino) {
            return false;
        }
        return true;
    }


    

    
}
