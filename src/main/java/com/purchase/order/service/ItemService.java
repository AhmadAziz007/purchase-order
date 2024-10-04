package com.purchase.order.service;

import com.purchase.order.dto.ItemDTO;
import com.purchase.order.model.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemService {
    public ResponseEntity<?> createItem(ItemDTO itemDTO);
    public ResponseEntity<?> updateItem(Long itemId, ItemDTO itemDTO);
    public ResponseEntity<List<Item>> listItem();
    public ResponseEntity<?> getItem(Long itemId);
    public ResponseEntity<?> deleteItem(Long itemId);
}
