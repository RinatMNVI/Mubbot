package db.dao;

import db.HibernateSessionFactoryUtil;
import db.domain.Tags;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mrina on 18.09.2018.
 */
public class TagDao {

    public Tags findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tags.class, id);
    }


    public void save(Tags tags) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(tags);
        tx1.commit();
        session.close();
    }

    public void update(Tags tags) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(tags);
        tx1.commit();
        session.close();
    }

    public void delete(Tags tags) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(tags);
        tx1.commit();
        session.close();
    }

    public List<Tags> findAll() {
        List<Tags> tags = (List<Tags>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Tags").list();
        return tags;
    }

}
