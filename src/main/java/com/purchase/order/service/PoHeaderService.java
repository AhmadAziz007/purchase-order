package com.purchase.order.service;

import com.purchase.order.dto.PoHeaderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoHeaderService {
    public ResponseEntity<?> createPoHeader(PoHeaderDTO poHeaderDTO);
    public ResponseEntity<?> updatePoHeader(Long pohId, PoHeaderDTO poHeaderDTO);
    public ResponseEntity<?> deletePoHeader(Long pohId);
    public ResponseEntity<?> getAllPoHeaders();
    public ResponseEntity<?> getPoHeaderById(Long pohId);

}
