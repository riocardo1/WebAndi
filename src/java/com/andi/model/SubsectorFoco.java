package com.andi.model;
// Generated 23/04/2016 10:02:31 PM by Hibernate Tools 4.3.1



/**
 * SubsectorFoco generated by hbm2java
 */
public class SubsectorFoco  implements java.io.Serializable {


     private long subsectorFocoId;
     private FocoInversionSocial focoInversionSocial;
     private Subsector subsector;

    public SubsectorFoco() {
    }

	
    public SubsectorFoco(long subsectorFocoId) {
        this.subsectorFocoId = subsectorFocoId;
    }
    public SubsectorFoco(long subsectorFocoId, FocoInversionSocial focoInversionSocial, Subsector subsector) {
       this.subsectorFocoId = subsectorFocoId;
       this.focoInversionSocial = focoInversionSocial;
       this.subsector = subsector;
    }
   
    public long getSubsectorFocoId() {
        return this.subsectorFocoId;
    }
    
    public void setSubsectorFocoId(long subsectorFocoId) {
        this.subsectorFocoId = subsectorFocoId;
    }
    public FocoInversionSocial getFocoInversionSocial() {
        return this.focoInversionSocial;
    }
    
    public void setFocoInversionSocial(FocoInversionSocial focoInversionSocial) {
        this.focoInversionSocial = focoInversionSocial;
    }
    public Subsector getSubsector() {
        return this.subsector;
    }
    
    public void setSubsector(Subsector subsector) {
        this.subsector = subsector;
    }




}


