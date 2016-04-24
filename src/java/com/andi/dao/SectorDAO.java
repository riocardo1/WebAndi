/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andi.dao;

import com.andi.model.Sector;
import com.andi.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanse0529
 */
public class SectorDAO {
    public void addSector(Sector sector){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(sector);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
    }
    public void deleteSector(String sectorId){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            Sector sector = (Sector)session.load(Sector.class,sectorId);
            session.delete(sector);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
    }
    public ArrayList<Sector> getAllSectores(){
        ArrayList<Sector> sectores = null;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from Sector";
            Query query = session.createQuery(queryString);
            sectores = (ArrayList<Sector>)query.list();
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
        return sectores;
    }
    public void updateSector(String sectorId,Sector sector){
                Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            Sector oldSector = (Sector)session.load(Sector.class,sectorId);
            oldSector.setSectorNombre(sector.getSectorNombre());
            session.update(oldSector);
            tx.commit();
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
    }
    public Sector getSectorByID(String sectorId){
        Sector sector = null;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from Sector where sectorNombre = :idToFind";
            Query query = session.createQuery(queryString);
            query.setString("idToFind", sectorId);
            sector = (Sector)query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
        return sector;
    }
}
