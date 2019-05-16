package com.weather.demo.dao;

import com.weather.demo.model.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MetricDAO extends MongoRepository<Metric, UUID> {
    Metric findByNameAndUnit(String name, String unit);
}
