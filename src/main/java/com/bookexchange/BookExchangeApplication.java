package com.bookexchange;

import com.bookexchange.domain.Item;
import com.bookexchange.domain.User;
import com.bookexchange.enums.ItemType;
import com.bookexchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
)
public class BookExchangeApplication implements ApplicationRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookExchangeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Item> items = Arrays.asList(new Item("Book1", ItemType.BOOK));
        User user = new User("Sk", "skum", "1234", "sathishm74@yahoo.com");
        user.setItems(items);
        userService.addUser(user);

        List<Item> items2 = Arrays.asList(new Item("Book2", ItemType.BOOK), new Item("Book3", ItemType.BOOK));
        User user2 = new User("John", "john22", "1234", "john2@yahoo.com");
        user2.setVerified(true);
        user2.setItems(items2);
        userService.addUser(user2);

        List<Item> items3 = Arrays.asList(new Item("Book3", ItemType.BOOK), new Item("Book4", ItemType.BOOK));
        User user3 = new User("Mike", "mike33", "1234", "mike3@yahoo.com");
        user3.setVerified(true);
        user3.setItems(items3);
        userService.addUser(user3);
    }
}
