package db.service;

import config.DataSingl;
import db.User;
import db.dao.TagDao;
import db.domain.MubUser;
import db.domain.Tags;

import java.util.List;

/**
 * Created by mrina on 18.09.2018.
 */
public class TagService {

    private TagDao tagDao = new TagDao();

    public TagService() {
    }

    public Tags findTag(int id) {
        return tagDao.findById(id);
    }

    public void saveTag(Tags tags, User user) {
        DataSingl.newInstance().deleteUser(user);
        tagDao.save(tags);
    }

    public void deleteTag(User user, Tags tags) {
        user.getUser().getExpenses().stream().filter(expenses -> expenses.getTag().getId() == tags.getId()).forEach(expenses -> {
            new ExpensesService().deleteExpenses(expenses);
        });
        DataSingl.newInstance().deleteUser(user);
        tagDao.delete(tags);
    }

    public Tags getTag(MubUser user, Integer id){
        return user.getTagsSet().stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public Tags getTag(MubUser user, String name){
        return user.getTagsSet().stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }


    public void updateTag(Tags tags) {
        tagDao.update(tags);
    }

    public List<Tags> findAllTags() {
        return tagDao.findAll();
    }
}
