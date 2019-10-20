package github.project.springboottemplate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BatchTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    UserService userService;

    @Test
    public void batchAuthors() {

        authorService.batchAuthors();
    }

    @Test
    public void batchUsers() {

        userService.createNewUserBook();
    }
}