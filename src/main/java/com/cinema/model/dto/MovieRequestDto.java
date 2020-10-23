package com.cinema.model.dto;

public class MovieRequestDto {
    private String title;

    public MovieRequestDto() {
    }

    public MovieRequestDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
