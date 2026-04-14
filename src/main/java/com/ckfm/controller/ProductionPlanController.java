package com.example.ckfsystem.controller;

import com.example.ckfsystem.dto.request.ProductionPlanRequest;
import com.example.ckfsystem.dto.response.ApiResponse;
import com.example.ckfsystem.dto.response.ProductionPlanResponse;
import com.example.ckfsystem.entity.enums.PlanStatus;
import com.example.ckfsystem.service.ProductionPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/production-plans")
@RequiredArgsConstructor
@Tag(name = "Production Plans", description = "Production planning APIs")
public class ProductionPlanController {

    private final ProductionPlanService productionPlanService;

    @PostMapping
    @PreAuthorize("hasAnyRole('KITCHEN_STAFF', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Create a new production plan")
    public ResponseEntity<ApiResponse<ProductionPlanResponse>> create(
            @Valid @RequestBody ProductionPlanRequest request) {
        ProductionPlanResponse response = productionPlanService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Production plan created", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get production plan by ID")
    public ResponseEntity<ApiResponse<ProductionPlanResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(productionPlanService.getById(id)));
    }

    @GetMapping("/kitchen/{kitchenId}")
    @Operation(summary = "Get all production plans for a kitchen")
    public ResponseEntity<ApiResponse<List<ProductionPlanResponse>>> getByKitchen(
            @PathVariable Long kitchenId) {
        return ResponseEntity.ok(ApiResponse.ok(productionPlanService.getByKitchen(kitchenId)));
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get production plans by date range")
    public ResponseEntity<ApiResponse<List<ProductionPlanResponse>>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(ApiResponse.ok(productionPlanService.getByDateRange(from, to)));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('KITCHEN_STAFF', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Advance plan status (PLANNED→IN_PROGRESS→COMPLETED)")
    public ResponseEntity<ApiResponse<ProductionPlanResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam PlanStatus status) {
        return ResponseEntity.ok(ApiResponse.ok("Plan status updated",
                productionPlanService.updateStatus(id, status)));
    }
}
