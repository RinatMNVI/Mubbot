package db;

import db.domain.MubUser;
import db.domain.Tags;
import db.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by mrina on 14.10.2018.
 */
public class User {

    private MubUser user;
    private ArrayList<Tags> tagsList;
    private String currentCommand;
    private String currentTag;

    public User(Integer userId, String userName){
        user = new UserService().findUser(userId);
        if (user == null) {
            user = new MubUser(userId, userName);
            new UserService().saveUser(user);
            tagsList = new ArrayList<>();
        } else
            tagsList = new ArrayList<>(user.getTagsSet());
    }


    public void clearTagAndCommand(){
        currentCommand = null;
        currentTag = null;
    }

    public MubUser getUser() {
        return user;
    }

    public void setUser(MubUser user) {
        this.user = user;
    }

    public ArrayList<Tags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(ArrayList<Tags> tagsList) {
        this.tagsList = tagsList;
    }

    public Tags getTag(String name){
        return tagsList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }

    public boolean isTagExist(String name){
        return tagsList.stream().filter(x -> x.getName().equals(name)).findAny().isPresent();
    }

    public Tags getTag(Integer id){
        return tagsList.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public String getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(String currentCommand) {
        this.currentCommand = currentCommand;
    }

    public String getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(String currentTag) {
        this.currentTag = currentTag;
    }
}
