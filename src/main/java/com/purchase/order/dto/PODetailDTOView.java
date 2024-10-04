package com.purchase.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.purchase.order.model.Item;
import com.purchase.order.model.PoHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PODetailDTOView {
    private Long podId;
    private Long pohId;
    private Date datetime;
    private String description;
    private Integer totalPrice;
    private Integer totalCost;
    private Long itemId;
    private String name;
    private Integer price;
    private Integer cost;
    private Integer itemQty;
    private Integer itemCost;
    private Integer itemPrice;
    private String createdBy;
    private String updatedBy;
    private Date createdDatetime;
    private Date updatedDatetime;
}
