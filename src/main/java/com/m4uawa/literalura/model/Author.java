package com.m4uawa.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Author {
    @JsonAlias("name") String name;

    @JsonAlias("birth_year") String birthDate;

    @JsonAlias("death_year") String deathDate;

    @Override
    public String toString() {
        return "Author [name=" + name + ", birthDate=" + birthDate + ", deathDate=" + deathDate + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }
    
}
