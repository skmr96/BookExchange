package com.bookexchange.repository;

import com.bookexchange.domain.Book;
import com.bookexchange.domain.Item;
import com.bookexchange.enums.ExistingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Transactional
    @Query("update Item i set i.status = :status where i.itemId= :itemId")
    public void underExchange(@Param("itemId")Long itemId, @Param("status") ExistingStatus status);

}
