package com.example.footballleagues.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class TestHomeController {

    @Autowired
    private HomeController controller;

    @Test
    public void initializtionTest() {
        // Check the context is loaded
        assertThat(controller).isNotNull();
    }

}
