package dao;

import entidades.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class EmpleadoDAO {

    private final String cfgFile;

    public EmpleadoDAO(String cfgFile){
        this.cfgFile = cfgFile;
    }

    public SessionFactory sf(){
        return HibernateUtil.getSessionFactory(cfgFile);
    }

    // CREATE
    public void insertarEmpleado(Empleado e) {
        Session s = null;
        Transaction tx = null;
        try {
            s = sf().openSession();
            tx = s.beginTransaction();

            s.persist(e);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        } finally {
            if (s != null) s.close();
            HibernateUtil.closeSessionFactory();
        }
    }

    // READ
    public Empleado buscarPorId(Integer id) {
        Session s = null;
        try {
            s = sf().openSession();
            return s.get(Empleado.class, id);
        } finally {
            if (s != null) s.close();
            HibernateUtil.closeSessionFactory();
        }
    }

    // UPDATE
    public void actualizarEmpleado(Empleado e) {
        Session s = null;
        Transaction tx = null;
        try {
            s = sf().openSession();
            tx = s.beginTransaction();

            s.merge(e);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        } finally {
            if (s != null) s.close();
            HibernateUtil.closeSessionFactory();
        }
    }

    // DELETE
    public void borrarEmpleado(Empleado e) {
        Session s = null;
        Transaction tx = null;
        try {
            s = sf().openSession();
            tx = s.beginTransaction();

            s.remove(e);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            throw ex;
        } finally {
            if (s != null) s.close();
            HibernateUtil.closeSessionFactory();
        }
    }


}
