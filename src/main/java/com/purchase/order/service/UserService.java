package com.purchase.order.service;

import com.purchase.order.dto.UserDTO;
import com.purchase.order.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<?> createUser(UserDTO userDTO);
    public ResponseEntity<?> updateUser(Long userId, UserDTO userDTO);
    public ResponseEntity<List<User>> listUser();
    public ResponseEntity<?> getUser(Long userId);
    public ResponseEntity<?> deleteUser(Long userId);
}
