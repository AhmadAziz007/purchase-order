package com.purchase.order.Repository;

import com.purchase.order.model.PoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoDetailRepository extends JpaRepository<PoDetail, Long> {
    @Query(value = "select * from po_d where pod_id = :podId", nativeQuery = true)
    Optional<PoDetail> findByPodId(@Param("podId") Long podId);

    @Query(value = "select " +
            "a.pod_id, " +
            "a.poh_id, " +
            "b.datetime, " +
            "b.description, " +
            "b.total_price, " +
            "b.total_cost, " +
            "a.item_id, " +
            "c.name, " +
            "c.price, " +
            "c.cost, " +
            "a.item_qty, " +
            "a.item_cost, " +
            "a.item_price, " +
            "a.created_by, " +
            "a.updated_by, " +
            "a.created_datetime, " +
            "a.updated_datetime " +
            "from po_d as a " +
            "left join po_h as b on a.poh_id = b.poh_id " +
            "left join item as c on a.item_id = c.item_id " +
            "order by a.pod_id asc",
            nativeQuery = true)
    List<Object[]> findAllWithCustomMapping();

    @Query(value = "select " +
            "a.pod_id, " +
            "a.poh_id, " +
            "b.datetime, " +
            "b.description, " +
            "b.total_price, " +
            "b.total_cost, " +
            "a.item_id, " +
            "c.name, " +
            "c.price, " +
            "c.cost, " +
            "a.item_qty, " +
            "a.item_cost, " +
            "a.item_price, " +
            "a.created_by, " +
            "a.updated_by, " +
            "a.created_datetime, " +
            "a.updated_datetime " +
            "from po_d as a " +
            "left join po_h as b on a.poh_id = b.poh_id " +
            "left join item as c on a.item_id = c.item_id " +
            "where a.pod_id = :podId " +
            "order by a.pod_id asc",
            nativeQuery = true)
    List<Object[]> findByPuschaseOrderDetailId(@Param("podId") Long podId);

}
