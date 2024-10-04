package com.purchase.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "po_d", schema = "public")
public class PoDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pod_id")
    private Long podId;

    @ManyToOne
    @JoinColumn(name = "poh_id")
    private PoHeader poHeader;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_qty")
    private Integer itemQty;

    @Column(name = "item_cost")
    private Integer itemCost;

    @Column(name = "item_price")
    private Integer itemPrice;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_datetime")
    private Date createdDatetime;

    @Column(name = "updated_datetime")
    private Date updatedDatetime;
}
