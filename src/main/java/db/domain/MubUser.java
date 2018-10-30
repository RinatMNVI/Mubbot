package db.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Created by mrina on 17.09.2018.
 */
@Entity
@Table(name = "users")
public class MubUser {
    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Expenses> expenses;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tags> tagsSet;

    public MubUser(){}

    public MubUser(int id,String name){
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MubUser user = (MubUser) o;
        return Objects.equals(id, user.id);
    }


    public Set<Tags> getTagsSet() {
        return tagsSet;
    }

    public void setTagsSet(Set<Tags> tagsSet) {
        this.tagsSet = tagsSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int telegramId) {
        this.id = telegramId;
    }

    public Set<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expenses> expenses) {
        this.expenses = expenses;
    }
}
