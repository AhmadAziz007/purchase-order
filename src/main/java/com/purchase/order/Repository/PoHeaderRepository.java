package com.purchase.order.Repository;

import com.purchase.order.model.PoHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoHeaderRepository extends JpaRepository<PoHeader, Long> {
    @Query(value = "select * from po_h where poh_id = :pohId", nativeQuery = true)
    Optional<PoHeader> findByPohId(@Param("pohId") Long pohId);

    @Query(value = "select a.poh_id, a.datetime, a.description, a.total_price, a.total_cost from po_h where poh_id = :pohId", nativeQuery = true)
    Optional<PoHeader> findByHeaderPoDetail(@Param("pohId") Long pohId);

    @Query(value = "select a.* from po_h as a order by a.poh_id asc", nativeQuery = true)
    List<PoHeader> findAll();

}
