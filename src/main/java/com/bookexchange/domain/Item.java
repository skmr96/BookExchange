package com.bookexchange.domain;

import com.bookexchange.enums.ExistingStatus;
import com.bookexchange.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemId;
    String name;
    @Enumerated
    ItemType itemType;

    @Column(name = "user_id")
    Long userId;
    @Enumerated
    ExistingStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    List<Exchange> exchangedItems;

    public Item() {}
    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
        this.status = ExistingStatus.ACTIVE;
    }
}
