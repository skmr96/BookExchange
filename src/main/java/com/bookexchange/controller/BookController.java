package com.bookexchange.controller;

import com.bookexchange.domain.Book;
import com.bookexchange.domain.Exchange;
import com.bookexchange.domain.Item;
import com.bookexchange.domain.User;
import com.bookexchange.enums.ExistingStatus;
import com.bookexchange.exception.UserNotVerifiedException;
import com.bookexchange.service.ItemService;
import com.bookexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @PostMapping("api/v1/items")
    public List<Book> addItems(@RequestParam Long userId, @RequestBody List<Book> itemList)
    {
        User user = userService.getUser(userId);
        if(!user.isVerified()){
            throw new UserNotVerifiedException();
        }
        itemList.forEach(item -> item.setUserId(userId));
        return itemService.addItems(itemList);
    }

    @GetMapping("api/v1/items")
    public List<Book> listItems()
    {
        return itemService.getItems();
    }

    @PostMapping("api/v1/exchangeItems")
    public ResponseEntity exchangeItems(@RequestBody Exchange exchange)
    {
        Long fromUserId = exchange.getFromUserId();
        Long toUserId = exchange.getToUserId();
        User fromUser = userService.getUser(fromUserId);
        User toUser = userService.getUser(toUserId);

        if(!fromUser.isVerified() || !toUser.isVerified()){
            throw new UserNotVerifiedException();
        }

        itemService.exchangeItems(exchange);
        itemService.updateItemStatusToExchanged(exchange.getItemId1(), ExistingStatus.UNDER_EXCHANGE);
        itemService.updateItemStatusToExchanged(exchange.getItemId2(), ExistingStatus.UNDER_EXCHANGE);

        updateRewardPoints(fromUserId);
        updateRewardPoints(toUserId);

        return ResponseEntity.status(HttpStatus.OK).body("Books exchanged and reward points added");
    }
    private void updateRewardPoints(Long userId)
    {
        int userRP = userService.getRewardPoints(userId);
        userService.updateRewardPoints(++userRP,userId);
    }

    @PostMapping("api/v1/items/{itemId}/borrow")
    public ResponseEntity borrowItems(@PathVariable Long itemId, @RequestParam Long fromUserId, @RequestParam Long toUserId )
    {
        User fromUser = userService.getUser(fromUserId);
        User toUser = userService.getUser(toUserId);
        if(!fromUser.isVerified() || !toUser.isVerified()){
            throw new UserNotVerifiedException();
        }
        Exchange exchange = new Exchange();
        exchange.setFromUserId(fromUserId);
        exchange.setToUserId(toUserId);
        exchange.setItemId(itemId);
        itemService.borrowItems(exchange);
        updateRewardPoints(fromUserId);
        return ResponseEntity.status(HttpStatus.OK).body("Book borrowed and reward points added to owner of the book");
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello satheesh";
    }
}
