package com.example.footballleagues.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Giphy {
    public String name;
    public List<String> items = new ArrayList<>();

    public Giphy(String name) {
        this.name = name;
        items.add(name);
    }

    public Giphy() {
    }

}
