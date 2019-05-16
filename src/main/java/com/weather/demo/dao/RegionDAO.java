package com.weather.demo.dao;

import com.weather.demo.model.Region;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegionDAO extends MongoRepository<Region, UUID> {
    List<Region> findByAgency(UUID agency);

    Region findByNameAndTypeAndAgency(String name, String type, UUID agency);
}
