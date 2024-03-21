package com.bookexchange.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "item_id")
public class Book extends Item{

    String isbnNo;
    String authorName;
    String bookUrl;

    public Book() {}
}
