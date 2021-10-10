package com.example.footballleagues.configuration;

import com.example.footballleagues.client.GiphyClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Value("${api.giphy.url}")
    private String giphyUrl;


    @Bean
    public GiphyClient giphyClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(GiphyClient.class, giphyUrl);
    }
}
