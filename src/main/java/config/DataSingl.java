package config;

import db.User;
import db.domain.MubUser;
import db.service.ExpensesService;
import db.service.TagService;
import db.service.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mrina on 18.09.2018.
 */
public class DataSingl {

    private static DataSingl instance;


    public static DataSingl newInstance(){
        if (instance == null)
            instance = new DataSingl();
        return instance;
    }


    private LinkedList<User> users;

    private DataSingl(){
        users = new LinkedList<>();
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public User getUser(Integer id, String name){
        User user = users.stream().filter(x -> x.getUser().getId() == id).findAny().isPresent() ?
                users.stream().filter(x -> x.getUser().getId() == id).findAny().get() :
                null;
        if (user == null) {
            user = new User(id, name);
            users.add(user);
        }
        return user;
    }

    public User getUser(Integer id){
        return users.stream().filter(x -> x.getUser().getId() == id).findFirst().get();
    }


    public void addUser(User user){
        if (!users.contains(user))
            users.add(user);
    }

    public void deleteUser(User user){
        users.remove(user);
    }

}
