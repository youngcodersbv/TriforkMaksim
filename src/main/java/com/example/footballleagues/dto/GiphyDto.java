package com.example.footballleagues.dto;

import lombok.Data;

import java.util.List;

@Data
public class GiphyDto {
    private List<GiphyItemDto> data;
    @Data
    public static  class GiphyItemDto{
        private String url;
    }

}
