package com.purchase.order.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ItemDTO {
    private Long itemId;
    private String name;
    private String description;
    private Integer price;
    private Integer cost;
    private String createdBy;
    private String updatedBy;
    private Date createdDatetime;
    private Date updatedDatetime;

}
