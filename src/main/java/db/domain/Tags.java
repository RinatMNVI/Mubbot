package db.domain;

import javax.persistence.*;

/**
 * Created by mrina on 18.09.2018.
 */
@Entity
public class Tags {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MubUser buyer;

    private String name;

    public Tags(){}

    public Tags(String name, MubUser buyer){
        this.name = name;
        this.buyer = buyer;
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

    public MubUser getBuyer() {
        return buyer;
    }

    public void setBuyer(MubUser buyer) {
        this.buyer = buyer;
    }

    public void setId(int id) {
        this.id = id;
    }
}
