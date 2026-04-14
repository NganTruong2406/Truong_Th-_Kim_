package com.example.ckfsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_details")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RecipeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_product_id", nullable = false)
    private Product ingredientProduct;

    @Column(nullable = false)
    private Double standardQty;
}
