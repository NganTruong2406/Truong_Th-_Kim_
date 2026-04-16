package com.ckfm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "batch")
@Getter
@Setter
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id")
    private Integer batchId;
    
    @Column(name = "quantity")
    private java.math.BigDecimal quantity;

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public java.math.BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
}