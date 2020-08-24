package com.practice.kafka.kafkaavroproducer.model;

public class UserInfo {

    private String name;
    private int favorite_number;
    private String favorite_color;

    public UserInfo() {
    }

    public UserInfo(String name, int favorite_number, String favorite_color) {
        this.name = name;
        this.favorite_number = favorite_number;
        this.favorite_color = favorite_color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFavorite_number() {
        return favorite_number;
    }

    public void setFavorite_number(int favorite_number) {
        this.favorite_number = favorite_number;
    }

    public String getFavorite_color() {
        return favorite_color;
    }

    public void setFavorite_color(String favorite_color) {
        this.favorite_color = favorite_color;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", favorite_number=" + favorite_number +
                ", favorite_color='" + favorite_color + '\'' +
                '}';
    }
}
