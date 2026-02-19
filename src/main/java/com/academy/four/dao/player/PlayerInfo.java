package com.academy.four.dao.player;

import java.awt.*;
import java.io.Serializable;
// Entity(PlayerInfo)  <-> tabela(player_info)
public class PlayerInfo implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String sport;
    private Integer years;
    private boolean vegetarian;
    private Color color;

    public PlayerInfo() {}

    public PlayerInfo(Integer id, String firstName, String lastName, String sport, Integer years, boolean vegetarian, Color color) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.years = years;
        this.vegetarian = vegetarian;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sport='" + sport + '\'' +
                ", years=" + years +
                ", vegetarian=" + vegetarian +
                ", color=" + color +
                '}';
    }
}
