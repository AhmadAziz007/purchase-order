package com.purchase.order.dto;

import com.purchase.order.model.Item;
import com.purchase.order.model.PoHeader;
import lombok.Data;

import java.util.Date;

@Data
public class PoDetailDTO {
    private Long podId;
    private PoHeader poHeader;
    private Item item;
    private Integer itemQty;
    private Integer itemCost;
    private Integer itemPrice;
    private String createdBy;
    private String updatedBy;
    private Date createdDatetime;
    private Date updatedDatetime;
}
