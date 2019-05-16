package com.weather.demo.model;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection ="region")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonFilter("idfilter")
public class Region {

    @Id
    private UUID id;
    private UUID agency;
    private String name;
    private String type;

    public Region() {
    }

    public Region(UUID id, UUID agency, String name, String type) {
        this.id = id;
        this.agency = agency;
        this.name = name;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAgency() {
        return agency;
    }

    public void setAgency(UUID agency) {
        this.agency = agency;
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

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", agency='" + agency + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
