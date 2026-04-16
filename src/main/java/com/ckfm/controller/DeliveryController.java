package com.ckfm.controller;

import com.ckfm.entity.Delivery;
import com.ckfm.entity.DeliveryDetail;
import com.ckfm.entity.Order;
import com.ckfm.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Delivery> createDelivery(@RequestParam Integer orderId, @RequestBody List<DeliveryDetail> details) {
        
        Order order = new Order();
        order.setOrderId(orderId);

        Delivery savedDelivery = deliveryService.processDelivery(order, details);
        
        return ResponseEntity.ok(savedDelivery);
    }
}