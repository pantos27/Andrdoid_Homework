package com.example.veierovioum.lesson19_customadapter;

import java.io.Serializable;

/**
 * Created by Veierovioum on 25/01/2016.
 */
public class Country  {
    String name;
    String[] cities;

    public Country(String name, String... cities) {
        this.name = name;
        this.cities = cities;
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return getName();
    }


}
