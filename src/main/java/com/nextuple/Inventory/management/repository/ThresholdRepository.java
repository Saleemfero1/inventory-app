package com.nextuple.Inventory.management.repository;

import com.nextuple.Inventory.management.model.Threshold;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ThresholdRepository extends MongoRepository<Threshold,String> {
    Optional<Threshold> findByItemIdAndLocationId(String itemId, String locationId);

   
    Optional<Threshold> findByItemIdAndLocationIdAndOrganizationId(String itemId, String locationId, String organizationId);

    List<Threshold> findAllByOrganizationId(String organizationId);

    Optional<Threshold> findByIdAndOrganizationId(String thresholdId, String organizationId);
}
