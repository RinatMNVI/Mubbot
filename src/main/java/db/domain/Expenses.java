package db.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mrina on 18.09.2018.
 */
@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id")
    private Tags tag;

    private int cost;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private MubUser buyer;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime buyDate;

    public Expenses(){}

    public Expenses(Tags tag, Integer cost, MubUser buyer, String comment){
        this.buyer = buyer;
        this.cost = cost;
        this.tag = tag;
        this.comment = comment;
        buyDate = LocalDateTime.now();
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    public int getCost() {
        return cost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public Expenses setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
        return this;
    }

    public MubUser getBuyer() {
        return buyer;
    }

    public void setBuyer(MubUser buyer) {
        this.buyer = buyer;
    }
}
