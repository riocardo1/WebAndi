package com.andi.model;
// Generated 23/04/2016 10:02:31 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Dimension generated by hbm2java
 */
public class Dimension  implements java.io.Serializable {


     private long dimensionId;
     private String dimensionDescripcion;
     private Set<Recomendacion> recomendacions = new HashSet<Recomendacion>(0);

    public Dimension() {
    }

	
    public Dimension(long dimensionId) {
        this.dimensionId = dimensionId;
    }
    public Dimension(long dimensionId, String dimensionDescripcion, Set<Recomendacion> recomendacions) {
       this.dimensionId = dimensionId;
       this.dimensionDescripcion = dimensionDescripcion;
       this.recomendacions = recomendacions;
    }
   
    public long getDimensionId() {
        return this.dimensionId;
    }
    
    public void setDimensionId(long dimensionId) {
        this.dimensionId = dimensionId;
    }
    public String getDimensionDescripcion() {
        return this.dimensionDescripcion;
    }
    
    public void setDimensionDescripcion(String dimensionDescripcion) {
        this.dimensionDescripcion = dimensionDescripcion;
    }
    public Set<Recomendacion> getRecomendacions() {
        return this.recomendacions;
    }
    
    public void setRecomendacions(Set<Recomendacion> recomendacions) {
        this.recomendacions = recomendacions;
    }




}


