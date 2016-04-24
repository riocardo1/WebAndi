/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andi.dao;

import com.andi.model.FocoInversionSocial;
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
public class FocosDAO {
    
    public ArrayList<FocoInversionSocial> getAllFocosBySubSector(Subsector subSectorId){
        ArrayList<FocoInversionSocial> focos = null;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from FocoInversionSocial foc where  foc.foinsoId in (select focoInversionSocial from SubsectorFoco as subfoco where subfoco.subsector = :idToFind)";
            Query query = session.createQuery(queryString);
            query.setEntity("idToFind", subSectorId);
            focos = (ArrayList<FocoInversionSocial>)query.list();
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
        return focos;
    }
}
