package com.example.ckfsystem.controller;

import com.example.ckfsystem.dto.request.InventoryTransactionRequest;
import com.example.ckfsystem.dto.response.ApiResponse;
import com.example.ckfsystem.dto.response.InventoryResponse;
import com.example.ckfsystem.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "Inventory management APIs")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/facility/{facilityId}")
    @Operation(summary = "Get all inventory for a facility")
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getByFacility(@PathVariable Long facilityId) {
        return ResponseEntity.ok(ApiResponse.ok(inventoryService.getByFacility(facilityId)));
    }

    @GetMapping("/facility/{facilityId}/product/{productId}")
    @Operation(summary = "Get inventory for a specific product in a facility")
    public ResponseEntity<ApiResponse<InventoryResponse>> getByFacilityAndProduct(
            @PathVariable Long facilityId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(ApiResponse.ok(
                inventoryService.getByFacilityAndProduct(facilityId, productId)));
    }

    @PostMapping("/transaction")
    @PreAuthorize("hasAnyRole('KITCHEN_STAFF', 'STORE_STAFF', 'COORDINATOR', 'MANAGER', 'ADMIN')")
    @Operation(summary = "Record an inventory transaction (IN / OUT / WASTE)")
    public ResponseEntity<ApiResponse<Void>> recordTransaction(
            @Valid @RequestBody InventoryTransactionRequest request) {
        inventoryService.recordTransaction(request);
        return ResponseEntity.ok(ApiResponse.ok("Transaction recorded", null));
    }
}
