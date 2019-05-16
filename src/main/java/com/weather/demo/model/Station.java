package com.weather.demo.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection ="station")
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonFilter("idvaluelistfilter")

public class Station {

    @Id
    private UUID id;
    private UUID agency;
    private UUID region;
    private String name;
    private String iata;
    private String wmo;
    private Double msc_ID;
    private Double latitude;
    private Double longitude;
    private Double elevation;
    private String provider;
    private String dataset;
    private String type;

    public Station() {
    }

    public Station(UUID id, UUID agency, UUID region, String name, String iata,
                   String wmo, Double msc_ID, Double latitude, Double longitude, Double elevation,
                   String provider, String dataset, String type) {
        this.id = id;
        this.agency = agency;
        this.region = region;
        this.name = name;
        this.iata = iata;
        this.wmo = wmo;
        this.msc_ID = msc_ID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.provider = provider;
        this.dataset = dataset;
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

    public UUID getRegion() {
        return region;
    }

    public void setRegion(UUID region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getWmo() {
        return wmo;
    }

    public void setWmo(String wmo) {
        this.wmo = wmo;
    }

    public Double getMsc_ID() {
        return msc_ID;
    }

    public void setMsc_ID(Double msc_ID) {
        this.msc_ID = msc_ID;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", agency=" + agency +
                ", region=" + region +
                ", name='" + name + '\'' +
                ", iata='" + iata + '\'' +
                ", wmo='" + wmo + '\'' +
                ", msc_ID=" + msc_ID +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", elevation=" + elevation +
                ", provider='" + provider + '\'' +
                ", dataset='" + dataset + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
