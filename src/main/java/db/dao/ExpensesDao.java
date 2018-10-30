package db.dao;

import db.HibernateSessionFactoryUtil;
import db.domain.Expenses;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by mrina on 18.09.2018.
 */
public class ExpensesDao {

    public Expenses findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Expenses.class, id);
    }


    public void save(Expenses expenses) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(expenses);
        tx1.commit();
        session.close();
    }

    public void update(Expenses expenses) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(expenses);
        tx1.commit();
        session.close();
    }

    public void delete(Expenses expenses) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(expenses);
        tx1.commit();
        session.close();
    }

    public List<Expenses> findAll() {
        List<Expenses> expenses = (List<Expenses>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Expenses").list();
        return expenses;
    }
}
