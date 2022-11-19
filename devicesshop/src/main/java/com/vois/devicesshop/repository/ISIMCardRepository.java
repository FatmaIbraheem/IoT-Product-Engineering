package com.vois.devicesshop.repository;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.model.SIMCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISIMCardRepository extends MongoRepository<SIMCard, String> {
}
