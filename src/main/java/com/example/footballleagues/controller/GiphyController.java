package com.example.footballleagues.controller;


import com.example.footballleagues.client.GiphyClient;
import com.example.footballleagues.dto.GiphyDto;
import com.example.footballleagues.model.Giphy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/giphy")
public class GiphyController {

    private Giphy giphy;
    private final GiphyClient giphyClient;

    @Value("${api.giphy.key}")
    private String giphyKey;


    @GetMapping()
    public String index(Model model) {
        return "giphy";
    }


    @PostMapping()
    public void postIndex(@ModelAttribute Giphy st, HttpServletResponse httpServletResponse) throws IOException {
        giphy = st;
        GiphyDto giphyDto = giphyClient.getRich(giphyKey, giphy.getName());
        httpServletResponse.sendRedirect(giphyDto.getData().get(0).getUrl());

    }
}


