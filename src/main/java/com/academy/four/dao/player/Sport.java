package com.academy.four.dao.player;

public class Sport {

    public static final String[] SPORTS = {
            "Football",
            "Basketball",
            "Handball",
            "Volleyball",
            "Tennis",
            "Table Tennis",
            "Badminton",
            "Athletics",
            "Swimming",
            "Water Polo",
            "Boxing",
            "Karate",
            "Taekwondo",
            "Judo",
            "Wrestling",
            "Gymnastics",
            "Cycling",
            "Skiing",
            "Snowboarding",
            "Ice Hockey",
            "Rugby",
            "Cricket",
            "Baseball",
            "Golf",
            "Rowing",
            "Sailing",
            "Climbing",
            "Martial Arts",
            "Esports"
    };

    private Long id;
    private String name;
    private String description;

    //TODO: izbrisati niz String[] -> podmetnuti nakon upita na bazu JComboBox List<Sport>..

    @Override
    public String toString() {
        return name;
    }
}
