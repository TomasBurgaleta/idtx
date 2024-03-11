package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    @Query(value = "select price from Price price where price.brandId = :brand and price.productId = :product and price.startDate < :moment and price.endDate > :moment")
    Stream<Price> getAllPriceByDateTime(@Param("moment") LocalDateTime date, @Param("product") Integer product, @Param("brand")  Integer brand);

}
