package com.purchase.order.service;

import com.purchase.order.dto.PODetailDTOView;
import com.purchase.order.dto.PoDetailDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoDetailService {
    public ResponseEntity<?> createPoDetail(PoDetailDTO poDetailDTO);
    public ResponseEntity<List<PODetailDTOView>> listPoDetail();
    public ResponseEntity<List<PODetailDTOView>> getPoDetail(Long podId);
}
