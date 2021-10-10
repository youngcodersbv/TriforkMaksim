package com.example.footballleagues.client;

import com.example.footballleagues.dto.GiphyDto;
import feign.Param;
import feign.RequestLine;

public interface GiphyClient {
    @RequestLine("GET ?api_key={key}&q=pizza&limit=1")
    GiphyDto getRich(@Param("key") String key);
    @RequestLine("GET ?api_key={key}&q={pizza}&limit=1")
    GiphyDto getRich(@Param("key") String key, @Param("pizza") String pizza);
}
