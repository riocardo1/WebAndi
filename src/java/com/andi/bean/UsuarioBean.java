/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andi.bean;

import com.andi.dao.SectorDAO;
import com.andi.dao.SubSectorDAO;
import com.andi.dao.UsuarioDAO;
import com.andi.model.Sector;
import com.andi.model.Subsector;
import com.andi.model.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.annotations.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author juanse0529
 */
@Named(value = "usuarioBean")
@SessionScoped

public class UsuarioBean implements Serializable {

    /**
     * Creates a new instance of UsuarioBean
     */
     private String usuarioId;
     private String usuarioNombre;
     private String usuarioPass;
     private String usuarioEmpresa;
     private String usuarioCargo;
     private String usuarioArea;
     private Subsector subsector;
     private String nombreSector;
     private String nombreSubSector;
     private Map<String,String> listaSectores;
     private Map<String,String> listaSubSectores;
     private Sector sector;
    
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String country; 
    private String city;  
    private Map<String,String> countries;
    private Map<String,String> cities;
     
     
     
     
    public UsuarioBean() {
        
    }
    @PostConstruct
    public void init() {
            
        llenaSectores();
        
        llenaSubSectores();

        
        
    }
    public void actualizaSubSector(){
        
            if(nombreSector !=null && !nombreSector.equals(""))
            listaSubSectores = data.get(nombreSector);
            else
            listaSubSectores = new HashMap<String, String>();
            
    }
    public void llenaSectores(){
                        
        countries  = new HashMap<String, String>();
        
        SectorDAO sectorDAO = new SectorDAO();
        ArrayList<Sector> sectores = sectorDAO.getAllSectores();
        for (int i = 0; i < sectores.size(); i++) {
              countries.put(sectores.get(i).getSectorNombre(),sectores.get(i).getSectorNombre());  
        }
    }
    public void llenaSubSectores(){
        
        Map<String,String> map = new HashMap<String, String>();
        
        Iterator it = countries.entrySet().iterator();
        while (it.hasNext()) {
        Map.Entry sector = (Map.Entry)it.next();
        SubSectorDAO subSectorDAO = new SubSectorDAO();
        Sector sectortmp = new Sector((String) sector.getKey());
        ArrayList<Subsector> subSectores = subSectorDAO.getAllSubSectoresBySector(sectortmp);
        if (subSectores.size()> 0) {
                
            map = new HashMap<String, String>();
            for (int j = 0; j < subSectores.size(); j++) {    
                map.put(subSectores.get(j).getSubsectorNombre(),subSectores.get(j).getSubsectorNombre());                        
            }
            data.put((String) sector.getValue(),map);
        
        }
        }
                
    }
    public void addUsuario() throws IOException{
        Subsector tmp = new Subsector(getCity());
        tmp.setSector(new Sector(getCountry()));
        setSubsector(tmp);
        Usuario usuario = new Usuario(getUsuarioId(), getSubsector(),getUsuarioNombre(), getUsuarioPass(), getUsuarioEmpresa(), getUsuarioCargo(), getUsuarioArea());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.addUsuario(usuario);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage("Usuario creado correctamente, nuevo ID es "+ usuario.getUsuarioId()));
        context.getExternalContext().getSessionMap().put("sector", getCountry());
        context.getExternalContext().getSessionMap().put("subsector", getCity());
        context.getExternalContext().getSessionMap().put("empresa", getUsuarioEmpresa());
        String ruta = "./faces/menu.xhtml";
        ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
        contexto.redirect(ruta);
    }
    public void returnUsuarioById(){
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = usuarioDao.getUsuarioByID(getUsuarioId());
        if(usuario != null){
            setUsuarioId(usuario.getUsuarioId());
            setUsuarioNombre(usuario.getUsuarioNombre());
            setUsuarioPass(usuario.getUsuarioPass());
            setUsuarioEmpresa(usuario.getUsuarioEmpresa());
            setUsuarioCargo(usuario.getUsuarioCargo());
            setUsuarioArea(usuario.getUsuarioArea());
            setSubsector(usuario.getSubsector());
            setNombreSector(usuario.getSubsector().getSector().getSectorNombre());
            setNombreSubSector(usuario.getSubsector().getSubsectorNombre());
           
        }else{
            setUsuarioId("");
            setUsuarioNombre("");
            setUsuarioPass("");
            setUsuarioEmpresa("");
            setUsuarioCargo("");
            setUsuarioArea("");
            setNombreSector("");
            setNombreSubSector("");
        }   
    }
    public void deleteUsuario(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.deleteUsuario(getUsuarioId());
        setUsuarioId("");
        setUsuarioNombre("");
        setUsuarioPass("");
        setUsuarioEmpresa("");
        setUsuarioCargo("");
        setUsuarioArea("");
        setNombreSector("");
        setNombreSubSector(""); 
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Usuario con ID: "+getUsuarioId()+" Eliminado"));
    
    }
    /**
     * @return the usuarioId
     */
    public String getUsuarioId() {
        return usuarioId;
    }

    /**
     * @param usuarioId the usuarioId to set
     */
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * @return the usuarioNombre
     */
    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    /**
     * @param usuarioNombre the usuarioNombre to set
     */
    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    /**
     * @return the usuarioPass
     */
    public String getUsuarioPass() {
        return usuarioPass;
    }

    /**
     * @param usuarioPass the usuarioPass to set
     */
    public void setUsuarioPass(String usuarioPass) {
        this.usuarioPass = usuarioPass;
    }

    /**
     * @return the usuarioEmpresa
     */
    public String getUsuarioEmpresa() {
        return usuarioEmpresa;
    }

    /**
     * @param usuarioEmpresa the usuarioEmpresa to set
     */
    public void setUsuarioEmpresa(String usuarioEmpresa) {
        this.usuarioEmpresa = usuarioEmpresa;
    }

    /**
     * @return the usuarioCargo
     */
    public String getUsuarioCargo() {
        return usuarioCargo;
    }

    /**
     * @param usuarioCargo the usuarioCargo to set
     */
    public void setUsuarioCargo(String usuarioCargo) {
        this.usuarioCargo = usuarioCargo;
    }

    /**
     * @return the usuarioArea
     */
    public String getUsuarioArea() {
        return usuarioArea;
    }

    /**
     * @param usuarioArea the usuarioArea to set
     */
    public void setUsuarioArea(String usuarioArea) {
        this.usuarioArea = usuarioArea;
    }

    /**
     * @return the subsector
     */
    public Subsector getSubsector() {
        return subsector;
    }

    /**
     * @param subsector the subsector to set
     */
    public void setSubsector(Subsector subsector) {
        this.subsector = subsector;
    }

    /**
     * @return the nombreSector
     */
    public String getNombreSector() {
        return nombreSector;
    }

    /**
     * @param nombreSector the nombreSector to set
     */
    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    /**
     * @return the nombreSubSector
     */
    public String getNombreSubSector() {
        return nombreSubSector;
    }

    /**
     * @param nombreSubSector the nombreSubSector to set
     */
    public void setNombreSubSector(String nombreSubSector) {
        this.nombreSubSector = nombreSubSector;
    }

    /**
     * @return the listaSectores
     */
    public  Map<String,String> getListaSectores() {
        return listaSectores;
    }

    /**
     * @param listaSectores the listaSectores to set
     */
    public void setListaSectores(Map<String,String> listaSectores) {
        this.listaSectores = listaSectores;
    }

    /**
     * @return the listaSubSectores
     */
    public Map<String,String> getListaSubSectores() {
        return listaSubSectores;
    }

    /**
     * @param listaSubSectores the listaSubSectores to set
     */
    public void setListaSubSectores(Map<String,String> listaSubSectores) {
        this.listaSubSectores = listaSubSectores;
    }

    /**
     * @return the sector
     */
    public Sector getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getCountry() {
        return country;
    }
 
    public void setCountry(String country) {
        this.country = country;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public Map<String, String> getCountries() {
        return countries;
    }
 
    public Map<String, String> getCities() {
        return cities;
    }
 
    public void onCountryChange() {
        if(country !=null && !country.equals(""))
            cities = data.get(country);
        else
            cities = new HashMap<String, String>();
    }
     
    public void displayLocation() {
        FacesMessage msg;
        if(city != null && country != null)
            msg = new FacesMessage("Selected", city + " of " + country);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
}
