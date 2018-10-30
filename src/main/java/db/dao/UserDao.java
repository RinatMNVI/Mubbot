package db.dao;

import db.HibernateSessionFactoryUtil;
import db.domain.MubUser;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mrina on 17.09.2018.
 */
public class UserDao {

    public MubUser findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(MubUser.class, id);
    }


    public void save(MubUser mubUser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(mubUser);
        tx1.commit();
        session.close();
    }

    public void update(MubUser mubUser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(mubUser);
        tx1.commit();
        session.close();
    }

    public void delete(MubUser mubUser) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(mubUser);
        tx1.commit();
        session.close();
    }

    public List<MubUser> findAll() {
        List<MubUser> mubUsers = (List<MubUser>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From MubUser").list();
        return mubUsers;
    }
}
