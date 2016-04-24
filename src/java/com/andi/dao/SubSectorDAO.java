/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andi.dao;

import com.andi.model.Sector;
import com.andi.model.Subsector;
import com.andi.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanse0529
 */
public class SubSectorDAO {
     public void addSector(Subsector subsector){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(subsector);
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
    public void deleteSector(String subSectorId){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            Subsector subSector = (Subsector)session.load(Subsector.class,subSectorId);
            session.delete(subSector);
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
    public ArrayList<Subsector> getAllSubSectoresBySector(Sector sectorId){
        ArrayList<Subsector> subSectores = null;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from Subsector where sector = :idToFind";
            Query query = session.createQuery(queryString);
            query.setEntity("idToFind", sectorId);
            subSectores = (ArrayList<Subsector>)query.list();
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
        return subSectores;
    }
    public void updateSector(String subSectorId,Subsector subSector){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            Subsector oldSector = (Subsector)session.load(Subsector.class,subSectorId);
            oldSector.setSubsectorNombre(subSector.getSubsectorNombre());
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
    public Subsector getSubSectorByID(String subSectorId){
        Subsector sector = null;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from Subsector where subsectorNombre = :idToFind";
            Query query = session.createQuery(queryString);
            query.setString("idToFind", subSectorId);
            sector = (Subsector)query.uniqueResult();
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
