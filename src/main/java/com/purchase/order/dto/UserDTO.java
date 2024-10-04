package com.purchase.order.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String createdBy;
    private String updatedBy;
    private Date createdDatetime;
    private Date updatedDatetime;
}
