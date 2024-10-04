package com.purchase.order.service.implement;

import com.purchase.order.Repository.ItemRepository;
import com.purchase.order.Repository.PoDetailRepository;
import com.purchase.order.Repository.PoHeaderRepository;
import com.purchase.order.dto.PODetailDTOView;
import com.purchase.order.dto.PoDetailDTO;
import com.purchase.order.model.Item;
import com.purchase.order.model.PoDetail;
import com.purchase.order.model.PoHeader;
import com.purchase.order.service.PoDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PoDetailServiceImpl implements PoDetailService {

    @Autowired
    private PoDetailRepository poDetailRepository;

    @Autowired
    private PoHeaderRepository poHeaderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ResponseEntity<?> createPoDetail(PoDetailDTO poDetailDTO) {
        try {
            Optional<PoHeader> poHeaderOpt = poHeaderRepository.findById(poDetailDTO.getPoHeader().getPohId());
            Optional<Item> itemOpt = itemRepository.findById(poDetailDTO.getItem().getItemId());

            if (poHeaderOpt.isPresent() && itemOpt.isPresent()) {
                Item item = itemOpt.get();
                PoHeader poHeader = poHeaderOpt.get();

                Integer calculatedItemPrice = poDetailDTO.getItemQty() * item.getPrice();
                Integer calculatedItemCost = item.getCost() + calculatedItemPrice;

                PoDetail poDetail = new PoDetail();
                poDetail.setPoHeader(poHeader);
                poDetail.setItem(item);
                poDetail.setItemQty(poDetailDTO.getItemQty());
                poDetail.setItemPrice(calculatedItemPrice);
                poDetail.setItemCost(calculatedItemCost);
                poDetail.setCreatedBy("Admin");
                poDetail.setCreatedDatetime(new Date());

                PoDetail savedPoDetail = poDetailRepository.save(poDetail);
                return ResponseEntity.status(HttpStatus.OK).body(savedPoDetail);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PO Header or Item not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating PO detail: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<PODetailDTOView>> listPoDetail() {
        List<Object[]> resultList = poDetailRepository.findAllWithCustomMapping();
        List<PODetailDTOView> transaksiList = new ArrayList<>();

        for (Object[] obj : resultList) {
            PODetailDTOView detailDTOView = new PODetailDTOView();
            detailDTOView.setPodId((Long) obj[0]);
            detailDTOView.setPohId((Long) obj[1]);
            detailDTOView.setDatetime((Date) obj[2]);
            detailDTOView.setDescription((String) obj[3]);
            detailDTOView.setTotalPrice((Integer) obj[4]);
            detailDTOView.setTotalCost((Integer) obj[5]);
            detailDTOView.setItemId((Long) obj[6]);
            detailDTOView.setName((String) obj[7]);
            detailDTOView.setPrice((Integer) obj[8]);
            detailDTOView.setCost((Integer) obj[9]);
            detailDTOView.setItemQty((Integer) obj[10]);
            detailDTOView.setItemCost((Integer) obj[11]);
            detailDTOView.setItemPrice((Integer) obj[12]);
            detailDTOView.setCreatedBy((String) obj[13]);
            detailDTOView.setUpdatedBy((String) obj[14]);
            detailDTOView.setCreatedDatetime((Date) obj[15]);
            detailDTOView.setUpdatedDatetime((Date) obj[16]);
            transaksiList.add(detailDTOView);
        }
        return ResponseEntity.ok(transaksiList);
    }

    @Override
    public ResponseEntity<List<PODetailDTOView>> getPoDetail(Long podId) {
        List<Object[]> resultList = poDetailRepository.findByPuschaseOrderDetailId(podId);
        List<PODetailDTOView> transaksiList = new ArrayList<>();

        for (Object[] obj : resultList) {
            PODetailDTOView detailDTOView = new PODetailDTOView();
            detailDTOView.setPodId((Long) obj[0]);
            detailDTOView.setPohId((Long) obj[1]);
            detailDTOView.setDatetime((Date) obj[2]);
            detailDTOView.setDescription((String) obj[3]);
            detailDTOView.setTotalPrice((Integer) obj[4]);
            detailDTOView.setTotalCost((Integer) obj[5]);
            detailDTOView.setItemId((Long) obj[6]);
            detailDTOView.setName((String) obj[7]);
            detailDTOView.setPrice((Integer) obj[8]);
            detailDTOView.setCost((Integer) obj[9]);
            detailDTOView.setItemQty((Integer) obj[10]);
            detailDTOView.setItemCost((Integer) obj[11]);
            detailDTOView.setItemPrice((Integer) obj[12]);
            detailDTOView.setCreatedBy((String) obj[13]);
            detailDTOView.setUpdatedBy((String) obj[14]);
            detailDTOView.setCreatedDatetime((Date) obj[15]);
            detailDTOView.setUpdatedDatetime((Date) obj[16]);
            transaksiList.add(detailDTOView);
        }
        return ResponseEntity.ok(transaksiList);
    }

}
