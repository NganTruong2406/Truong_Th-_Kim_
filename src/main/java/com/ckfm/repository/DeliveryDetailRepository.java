package com.ckfm.repository;

import com.ckfm.entity.DeliveryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDetailRepository extends JpaRepository<DeliveryDetail, Integer> {
}