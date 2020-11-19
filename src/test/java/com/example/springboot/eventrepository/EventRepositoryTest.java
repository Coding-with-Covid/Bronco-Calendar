package com.example.springboot.eventrepository;

import com.example.springboot.EventRepository;
import com.example.springboot.Event;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository repo;

    @Before
    public void setup() {
        System.out.println("Before testing");
    }

    @Test
    public void testFindTitle() {
        String[] majors = {};
        Event e = new Event("New Club Workshop", "2020-10-08T10:00:00", "Office of Student Life and Cultural Centers", "Zoom", "Club & Organization registration information for students seeking to establish new clubs on campus. No RSVP required. If you arrive late you may not be admitted. Zoom link provided here on the date of the programming.", majors, "", "", "", "");

        String searchText = "New Club Workshop";
        Event result = repo.findByTitle(searchText);
        System.out.println(result.toString());
        Assert.assertEquals(e.getTitle(), result.getTitle());
    }
}
