package com.bookexchange.domain;

import com.bookexchange.enums.ExchangeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@Table(name = "Exchanged_Items")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long exchangeId;
    @Column(name = "from_user_id")
    Long fromUserId;
    @Column(name = "to_user_id")
    Long toUserId;
    @Column(name = "item_id")
    Long itemId;
    @Transient
    Long itemId1;
    @Transient
    Long itemId2;
    @Enumerated
    ExchangeType type;
}
