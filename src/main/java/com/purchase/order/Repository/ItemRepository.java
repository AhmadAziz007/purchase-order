package com.purchase.order.Repository;

import com.purchase.order.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select * from item where item_id = :itemId", nativeQuery = true)
    Optional<Item> findByItemId(@Param("itemId") Long itemId);

    @Query(value = "select a.item_id, a.name, a.description, a.price, a.cost  from item as a where a.item_id = :itemId", nativeQuery = true)
    Optional<Item> findByItemPoDetail(@Param("itemId") Long itemId);

    @Query(value = "select a.* from item a order by a.item_id asc", nativeQuery = true)
    List<Item> findAll();
}
