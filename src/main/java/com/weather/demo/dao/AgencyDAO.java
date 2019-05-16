package com.weather.demo.dao;

import com.weather.demo.model.Agency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AgencyDAO extends MongoRepository<Agency, UUID> {

    Agency findByNameAndTypeAndUrl(String name, String type, String url);
}
