package com.bookexchange.service;

import com.bookexchange.domain.Book;
import com.bookexchange.domain.Exchange;
import com.bookexchange.domain.Item;
import com.bookexchange.enums.ExchangeType;
import com.bookexchange.enums.ExistingStatus;
import com.bookexchange.repository.ExchangeRepository;
import com.bookexchange.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    public List<Book> addItems(List<Book> itemList) {
        return itemRepository.saveAll(itemList);
    }

    public List<Book> getItems() {
        return itemRepository.findAll();
    }

    public void exchangeItems(Exchange exchange) {
        Exchange ex1 = new Exchange();
        ex1.setType(ExchangeType.EXCHANGE);
        ex1.setItemId(exchange.getItemId1());
        ex1.setFromUserId(exchange.getFromUserId());
        ex1.setToUserId(exchange.getToUserId());

        Exchange ex2 = new Exchange();
        ex2.setType(ExchangeType.EXCHANGE);
        ex2.setItemId(exchange.getItemId2());
        ex2.setFromUserId(exchange.getToUserId());
        ex2.setToUserId(exchange.getFromUserId());

        exchangeRepository.addExchangedItem(exchange.getFromUserId(), exchange.getItemId1(), exchange.getToUserId(), ExchangeType.EXCHANGE.getType());
        exchangeRepository.addExchangedItem(exchange.getToUserId(), exchange.getItemId2(), exchange.getFromUserId(), ExchangeType.EXCHANGE.getType());
    }

    public void borrowItems(Exchange exchange) {
        Exchange ex1 = new Exchange();
        ex1.setType(ExchangeType.BORROW);
        ex1.setItemId(exchange.getItemId1());
        ex1.setFromUserId(exchange.getFromUserId());
        ex1.setToUserId(exchange.getToUserId());

        exchangeRepository.addExchangedItem(exchange.getFromUserId(), exchange.getItemId(), exchange.getToUserId(), ExchangeType.BORROW.getType());
    }

    public void updateItemStatusToExchanged(Long itemId, ExistingStatus status) {
        itemRepository.underExchange(itemId, status);
    }
}
