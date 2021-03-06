package com.andi.model;
// Generated 23/04/2016 10:02:31 PM by Hibernate Tools 4.3.1



/**
 * Pregunta generated by hbm2java
 */
public class Pregunta  implements java.io.Serializable {


     private long preguntaId;
     private String preguntaDescripcion;
     private Long preguntaPredecesora;
     private Long preguntaPadre;

    public Pregunta() {
    }

	
    public Pregunta(long preguntaId) {
        this.preguntaId = preguntaId;
    }
    public Pregunta(long preguntaId, String preguntaDescripcion, Long preguntaPredecesora, Long preguntaPadre) {
       this.preguntaId = preguntaId;
       this.preguntaDescripcion = preguntaDescripcion;
       this.preguntaPredecesora = preguntaPredecesora;
       this.preguntaPadre = preguntaPadre;
    }
   
    public long getPreguntaId() {
        return this.preguntaId;
    }
    
    public void setPreguntaId(long preguntaId) {
        this.preguntaId = preguntaId;
    }
    public String getPreguntaDescripcion() {
        return this.preguntaDescripcion;
    }
    
    public void setPreguntaDescripcion(String preguntaDescripcion) {
        this.preguntaDescripcion = preguntaDescripcion;
    }
    public Long getPreguntaPredecesora() {
        return this.preguntaPredecesora;
    }
    
    public void setPreguntaPredecesora(Long preguntaPredecesora) {
        this.preguntaPredecesora = preguntaPredecesora;
    }
    public Long getPreguntaPadre() {
        return this.preguntaPadre;
    }
    
    public void setPreguntaPadre(Long preguntaPadre) {
        this.preguntaPadre = preguntaPadre;
    }




}


