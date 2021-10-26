package com.example.footballleagues.model;

import antlr.collections.List;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestLeague {
    @Test
    public void testFilter(){
        League league = new League();
        league.setCountry("England");
        league.setName("League");


        assertTrue(League.filter("En", league));
        assertTrue(League.filter("Le", league));

        assertFalse(League.filter("haag", league));


    }
}
