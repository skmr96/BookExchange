package com.bookexchange.repository;

import com.bookexchange.domain.Exchange;
import com.bookexchange.domain.Item;
import com.bookexchange.enums.ExchangeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into exchanged_items (from_user_id, item_id, to_user_id, type) values (:fromUserId, :itemId, :toUserId, :type)", nativeQuery = true)
    public void addExchangedItem(@Param("fromUserId") Long fromUserId, @Param("itemId") Long itemId, @Param("toUserId") Long toUserId, @Param("type")
            Integer type);

}
