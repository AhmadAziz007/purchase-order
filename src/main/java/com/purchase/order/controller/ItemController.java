package com.purchase.order.controller;

import com.purchase.order.dto.ItemDTO;
import com.purchase.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO);
    }

    @GetMapping("/list-item")
    public ResponseEntity<?> listItem() {
        return itemService.listItem();
    }

    @GetMapping("/detail/{itemId}")
    public ResponseEntity<?> itemDetail(@PathVariable Long itemId) {
        return itemService.getItem(itemId);
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemId, @RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(itemId, itemDTO);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        return itemService.deleteItem(itemId);
    }
}
