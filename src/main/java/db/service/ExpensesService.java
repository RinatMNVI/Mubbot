package db.service;

import db.dao.ExpensesDao;
import db.domain.Expenses;

import java.util.List;

/**
 * Created by mrina on 18.09.2018.
 */
public class ExpensesService {

    private ExpensesDao expensesDao = new ExpensesDao();

    public ExpensesService() {
    }

    public Expenses findExpenses(int id) {
        return expensesDao.findById(id);
    }

    public void saveExpenses(Expenses expenses) {
        expensesDao.save(expenses);
    }

    public void deleteExpenses(Expenses expenses) {
        expensesDao.delete(expenses);
    }

    public void updateExpenses(Expenses expenses) {
        expensesDao.update(expenses);
    }

    public List<Expenses> findAllExpenses() {
        return expensesDao.findAll();
    }
}
