package com.purchase.order.service.implement;

import com.purchase.order.Repository.ItemRepository;
import com.purchase.order.Repository.UserRepository;
import com.purchase.order.dto.ItemDTO;
import com.purchase.order.model.Item;
import com.purchase.order.model.User;
import com.purchase.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> createItem(ItemDTO itemDTO) {
        try {
            List<User> users = userRepository.findAll();

            String firstName = users.stream()
                                    .map(User::getFirstName)
                                    .collect(Collectors.joining(", "));

            String lastName = users.stream()
                                   .map(User::getLastName)
                                   .collect(Collectors.joining(", "));

            String createdBy = firstName + " " + lastName;

            if (itemDTO.getName() == null ||
                    itemDTO.getDescription() == null ||
                    itemDTO.getPrice() == null ||
                    itemDTO.getCost() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fields name, description, price, and cost are mandatory and cannot be null");
            }

            Item item = new Item();
            item.setName(itemDTO.getName());
            item.setDescription(itemDTO.getDescription());
            item.setPrice(itemDTO.getPrice());
            item.setCost(itemDTO.getCost());

            item.setCreatedBy(createdBy);
            item.setCreatedDatetime(new Date());

            Item saveItem = itemRepository.save(item);

            return ResponseEntity.status(HttpStatus.OK).body(saveItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating item: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateItem(Long itemId, ItemDTO itemDTO) {
        Optional<Item> itemOpt = itemRepository.findByItemId(itemId);
        if (itemOpt.isPresent()) {
            List<User> users = userRepository.findAll();

            String firstName = users.stream()
                    .map(User::getFirstName)
                    .collect(Collectors.joining(", "));

            String lastName = users.stream()
                    .map(User::getLastName)
                    .collect(Collectors.joining(", "));

            String createdBy = firstName + " " + lastName;

            Item item = itemOpt.get();
            item.setName(itemDTO.getName());
            item.setDescription(itemDTO.getDescription());
            item.setPrice(itemDTO.getPrice());
            item.setCost(itemDTO.getCost());

            item.setUpdatedBy(createdBy);
            item.setUpdatedDatetime(new Date());

            Item saveItem = itemRepository.save(item);

            return ResponseEntity.status(HttpStatus.OK).body(saveItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + itemId + " not found");
        }
    }

    @Override
    public ResponseEntity<List<Item>> listItem() {
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<?> getItem(Long itemId) {
        try {
            Optional<Item> item = itemRepository.findByItemId(itemId);

            if (item.isEmpty()) {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(item.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving item:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteItem(Long itemId) {
        try {
            Optional<Item> item = itemRepository.findByItemId(itemId);

            if (item.isEmpty()) {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            } else {
                itemRepository.delete(item.get());
                return new ResponseEntity<>("Item delete successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving item:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
