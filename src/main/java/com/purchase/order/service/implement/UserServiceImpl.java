package com.purchase.order.service.implement;

import com.purchase.order.Repository.UserRepository;
import com.purchase.order.dto.UserDTO;
import com.purchase.order.model.User;
import com.purchase.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> createUser(UserDTO userDTO) {
        try {
            if (userDTO.getFirstName() == null ||
                userDTO.getLastName() == null ||
                userDTO.getEmail() == null ||
                userDTO.getPhone() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Field mandatory not null");
            }

            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());

            user.setCreatedBy("Admin");
            user.setCreatedDatetime(new Date());

            User saveUser = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(saveUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating user:" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateUser(Long userId, UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByUserId(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());

            user.setUpdatedBy("Admin");
            user.setUpdatedDatetime(new Date());

            User savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + userId + " not found");
        }
    }

    @Override
    public ResponseEntity<List<User>> listUser() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<?> getUser(Long userId) {
        try {
            Optional<User> user = userRepository.findByUserId(userId);

            if (user.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving user:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        try {
            Optional<User> user = userRepository.findByUserId(userId);

            if (user.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            } else {
                userRepository.delete(user.get());
                return new ResponseEntity<>("User delete successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving user:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
