package com.purchase.order.controller;

import com.purchase.order.dto.PoDetailDTO;
import com.purchase.order.dto.PoHeaderDTO;
import com.purchase.order.service.PoDetailService;
import com.purchase.order.service.PoHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/po")
public class PoController {

    @Autowired
    private PoHeaderService poHeaderService;

    @Autowired
    private PoDetailService poDetailService;

    // CRUD for PO Header
    @PostMapping("/header/save")
    public ResponseEntity<?> createPoHeader(@RequestBody PoHeaderDTO poHeaderDTO) {
        return poHeaderService.createPoHeader(poHeaderDTO);
    }

    @PutMapping("/header/update/{pohId}")
    public ResponseEntity<?> updatePoHeader(@PathVariable Long pohId, @RequestBody PoHeaderDTO poHeaderDTO) {
        return poHeaderService.updatePoHeader(pohId, poHeaderDTO);
    }

    @GetMapping("/header/list")
    public ResponseEntity<?> listPoHeaders() {
        return poHeaderService.getAllPoHeaders();
    }

    @GetMapping("/header/{pohId}")
    public ResponseEntity<?> getPoHeaderDetail(@PathVariable Long pohId) {
        return poHeaderService.getPoHeaderById(pohId);
    }

    @DeleteMapping("/header/delete/{pohId}")
    public ResponseEntity<?> deletePoHeader(@PathVariable Long pohId) {
        return poHeaderService.deletePoHeader(pohId);
    }

    // CRUD for PO Detail
    @PostMapping("/detail/save")
    public ResponseEntity<?> createPoDetail(@RequestBody PoDetailDTO poDetailDTO) {
        return poDetailService.createPoDetail(poDetailDTO);
    }

    @GetMapping("/detail/list")
    public ResponseEntity<?> listPoDetails() {
        return poDetailService.listPoDetail();
    }

    @GetMapping("/detail/get-one/{podId}")
    public ResponseEntity<?> getOneDetail(@PathVariable Long podId) {
        return poDetailService.getPoDetail(podId);
    }

//    @PutMapping("/detail/update-detail")
//    public ResponseEntity<?> updatePoDetail(@RequestBody PoDetailDTO poDetailDTO) {
//        return poDetailService.updatePoDetail(poDetailDTO);
//    }
}

