package com.purchase.order.service.implement;

import com.purchase.order.Repository.PoDetailRepository;
import com.purchase.order.Repository.PoHeaderRepository;
import com.purchase.order.Repository.UserRepository;
import com.purchase.order.dto.PoHeaderDTO;
import com.purchase.order.model.PoHeader;
import com.purchase.order.model.User;
import com.purchase.order.service.PoHeaderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PoHeaderServiceImpl implements PoHeaderService {

    @Autowired
    private PoHeaderRepository poHeaderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> createPoHeader(PoHeaderDTO poHeaderDTO) {
        try {
            List<User> users = userRepository.findAll();

            String firstName = users.stream()
                    .map(User::getFirstName)
                    .collect(Collectors.joining(", "));

            String lastName = users.stream()
                    .map(User::getLastName)
                    .collect(Collectors.joining(", "));

            String createdBy = firstName + " " + lastName;

            PoHeader poHeader = new PoHeader();
            poHeader.setDatetime(new Date());
            poHeader.setDescription(poHeaderDTO.getDescription());
            poHeader.setTotalPrice(poHeaderDTO.getTotalPrice());
            poHeader.setTotalCost(poHeaderDTO.getTotalCost());
            poHeader.setCreatedBy(createdBy);
            poHeader.setCreatedDatetime(new Date());

            PoHeader savedPoHeader = poHeaderRepository.save(poHeader);
            return ResponseEntity.status(HttpStatus.OK).body(savedPoHeader);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating PO header: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updatePoHeader(Long pohId, PoHeaderDTO poHeaderDTO) {
        Optional<PoHeader> poHeaderOpt = poHeaderRepository.findByPohId(pohId);
        if (poHeaderOpt.isPresent()) {
            List<User> users = userRepository.findAll();

            String firstName = users.stream()
                    .map(User::getFirstName)
                    .collect(Collectors.joining(", "));

            String lastName = users.stream()
                    .map(User::getLastName)
                    .collect(Collectors.joining(", "));

            String createdBy = firstName + " " + lastName;

            PoHeader poHeader = poHeaderOpt.get();
            poHeader.setDescription(poHeaderDTO.getDescription());
            poHeader.setTotalPrice(poHeaderDTO.getTotalPrice());
            poHeader.setTotalCost(poHeaderDTO.getTotalCost());
            poHeader.setUpdatedBy(createdBy);
            poHeader.setUpdatedDatetime(new Date());

            PoHeader updatedPoHeader = poHeaderRepository.save(poHeader);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPoHeader);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PO Header not found with ID " + pohId);
        }
    }

    @Override
    public ResponseEntity<?> getAllPoHeaders() {
        try {
            List<PoHeader> poHeaders = poHeaderRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(poHeaders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving PO headers: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getPoHeaderById(Long pohId) {
        try {
            Optional<PoHeader> poHeaderOpt = poHeaderRepository.findByPohId(pohId);

            if (poHeaderOpt.isEmpty()) {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(poHeaderOpt.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving item:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deletePoHeader(Long pohId) {
        try {
            Optional<PoHeader> poHeaderOpt = poHeaderRepository.findByPohId(pohId);
            if (poHeaderOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PO Header not found");
            }
            poHeaderRepository.deleteById(pohId);
            return ResponseEntity.status(HttpStatus.OK).body("PO Header deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting PO header: " + e.getMessage());
        }
    }
}
