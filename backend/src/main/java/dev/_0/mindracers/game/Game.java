package dev._0.mindracers.game;

import dev._0.mindracers.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "score")
    private Integer score;

    // relations
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    // getters and setters

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUser() {
        return user_id;
    }

    public void SetUser(User user) {
        this.user_id = user;
    }
}
