package com.weather.demo.dao;

import com.weather.demo.model.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StationDAO extends MongoRepository<Station, UUID> {



    List<Station> findByAgency(UUID agencyID);

    List<Station> findByRegionAndAgency(UUID region, UUID agency);

    Station findByAgencyAndRegionAndName(UUID agency, UUID region, String name);
}
