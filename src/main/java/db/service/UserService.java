package db.service;

import config.DataSingl;
import db.dao.UserDao;
import db.domain.MubUser;

import java.util.List;

/**
 * Created by mrina on 17.09.2018.
 */
public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public MubUser findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(MubUser mubUser) {
        usersDao.save(mubUser);
    }

    public void deleteUser(MubUser mubUser) {
        usersDao.delete(mubUser);
    }

    public void updateUser(MubUser mubUser) {
        usersDao.update(mubUser);
    }

    public List<MubUser> findAllUsers() {
        return usersDao.findAll();
    }

    public MubUser getUser(Integer userId, String userName){
        MubUser mubUser = findUser(userId);
        if (mubUser == null) {
            mubUser = new MubUser(userId, userName);
            saveUser(mubUser);
        }
        return mubUser;
    }

}
