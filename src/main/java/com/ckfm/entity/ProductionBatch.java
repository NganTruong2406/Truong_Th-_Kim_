package com.example.ckfsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "production_batches")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ProductionBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productionBatchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_detail_id", nullable = false)
    private PlanDetail planDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @Column(nullable = false)
    private Double producedQty;
}
