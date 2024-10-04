package com.purchase.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "po_h", schema = "public")
public class PoHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poh_id")
    private Long pohId;

    @Column(name = "datetime")
    private Date datetime;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_cost")
    private Integer totalCost;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_datetime")
    private Date createdDatetime;

    @Column(name = "updated_datetime")
    private Date updatedDatetime;

}

