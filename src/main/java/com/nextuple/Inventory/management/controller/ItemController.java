package com.nextuple.Inventory.management.controller;

import com.nextuple.Inventory.management.model.Item;
import com.nextuple.Inventory.management.service.InventoryServices;
import com.nextuple.Inventory.management.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/item")
@RestController
public class ItemController{
    @Autowired
    private ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    //we cn write this logger in any method

    ////////////////////////////////////////////////--item API--///////////////////////////////////////////////
    @GetMapping("/{organizationId}")
    public ResponseEntity<List<Item>> itemDetails(@PathVariable String organizationId){
        return new ResponseEntity<>(itemService.itemDetails(organizationId),HttpStatus.OK);
    }
    @GetMapping("/{organizationId}/{itemId}")
    public ResponseEntity<Item>findItem(@PathVariable("organizationId")String organizationId, @PathVariable("itemId") String itemId){
        return ResponseEntity.ok(itemService.findItem(itemId,organizationId));
    }
    @PostMapping("/{organizationId}")
    public ResponseEntity<Item>createItem(@PathVariable("organizationId")String organizationId, @RequestBody Item item)
    {
           Item itemCreated = itemService.createItem(item,organizationId);

           URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(itemCreated.getItemId())
                       .toUri();
               return ResponseEntity.created(uri).body(itemCreated);
    }

    @DeleteMapping("/{organizationId}/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") String itemId,@PathVariable("organizationId")String organizationId){
        String message = itemService.deleteItemIfNotPresentInReferenceCollection(itemId,organizationId);
        return new ResponseEntity<>(message,HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{organizationId}/{id}")
    public ResponseEntity<Item>updateItem(@RequestBody Item item,@PathVariable("id") String itemId, @PathVariable("organizationId")String organizationId){
        Item updatedItem = itemService.updateItem(organizationId, item,itemId);
        return  new ResponseEntity<>(updatedItem,HttpStatus.CREATED);
    }
    @GetMapping("/active/{organizationId}")
    public  ResponseEntity<List<Item>>getActiveItem(@PathVariable("organizationId")String organizationId){
    return new ResponseEntity<>(itemService.getActiveItems(organizationId),HttpStatus.OK);
    }

}
