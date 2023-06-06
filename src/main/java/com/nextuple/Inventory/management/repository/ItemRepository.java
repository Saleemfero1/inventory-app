package com.nextuple.Inventory.management.repository;
import com.nextuple.Inventory.management.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item,String>{

    List<Item> findByOrganizationId(String organizationId);

    Optional<Item> findByItemIdAndOrganizationId(String itemId, String organizationId);



    List<Item> findByStatusAndOrganizationId(boolean status, String organizationId);
}
