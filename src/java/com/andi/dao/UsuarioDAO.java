/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andi.dao;

import com.andi.model.Usuario;
import com.andi.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author juanse0529
 */
public class UsuarioDAO {
    //CRUD FOR USUARIO
    public void addUsuario(Usuario usuario){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(usuario);
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
    public void deleteUsuario(String usuarioId){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            Usuario usuario = (Usuario)session.load(Usuario.class,usuarioId);
            session.delete(usuario);
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
    public void updateUsuario(String usuarioId,Usuario usuario){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            Usuario oldUsuario = (Usuario)session.load(Usuario.class,usuarioId);
            oldUsuario.setUsuarioArea(usuario.getUsuarioArea());
            oldUsuario.setSubsector(usuario.getSubsector());
            oldUsuario.setUsuarioCargo(usuario.getUsuarioCargo());
            oldUsuario.setUsuarioEmpresa(usuario.getUsuarioEmpresa());
            oldUsuario.setUsuarioNombre(usuario.getUsuarioNombre());
            oldUsuario.setUsuarioPass(usuario.getUsuarioPass());
            session.update(oldUsuario);
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
    public Usuario getUsuarioByID(String usuarioId){
        Usuario usuario = null;
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            String queryString = "from Usuario where usuarioId = :idToFind";
            Query query = session.createQuery(queryString);
            query.setString("idToFind", usuarioId);
            usuario = (Usuario)query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.flush();
            session.close();
        }
        return usuario;
    }
}
