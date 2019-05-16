package com.weather.demo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection ="agency")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonFilter("idfilter")

public class Agency {

    @Id
    private UUID id;
    private String name;
    private String type;
    private String country;
    private String url;

    public Agency() {
    }

    public Agency(UUID id, String name, String type, String country, String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
