package com.bookexchange.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String name;
    String userName;
    String password;
    String email;
    int rewardPoints;
    boolean isVerified;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Item> items = new ArrayList<>();

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "from_user_id")
    List<Exchange> asFromUser;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "to_user_id")
    List<Exchange> asToUser;

    public User() {}
    public User(String name, String userName, String password, String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;

    }

}
