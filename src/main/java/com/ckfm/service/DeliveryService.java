package com.ckfm.service;

import com.ckfm.entity.*;
import com.ckfm.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryDetailRepository deliveryDetailRepository;

    // Khởi tạo các Repository
    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryDetailRepository deliveryDetailRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryDetailRepository = deliveryDetailRepository;
    }

    // NFR-03: Dùng @Transactional để đảm bảo nếu lỗi ở dòng nào thì rollback lại toàn bộ, không làm sai lệch tồn kho
    @Transactional 
    public Delivery processDelivery(Order order, List<DeliveryDetail> details) {
        
        // 1. Yêu cầu FR-27: Hệ thống phải tạo Delivery từ Order
        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setStatus("SHIPPING"); // Trạng thái đang giao hàng
        delivery = deliveryRepository.save(delivery); // Lưu phiếu giao vào DB

        // 2. Yêu cầu FR-28 & FR-29: Lưu chi tiết giao hàng cho từng Item, Batch, Quantity
        for (DeliveryDetail detail : details) {
            // Gắn mã phiếu giao tổng vào từng chi tiết
            detail.setDelivery(delivery);
            
            // 3. Yêu cầu FR-30: Hệ thống phải trừ kho khi giao
            Batch batch = detail.getBatch();
            if (batch != null && batch.getQuantity() != null) {
                // Lấy tồn kho hiện tại trừ đi số lượng xuất giao
                batch.setQuantity(batch.getQuantity().subtract(detail.getQuantity()));
                
                // (Note cho nhóm: Sau này Đức sẽ gọi hàm BatchRepository.save(batch) ở đây để cập nhật vào DB)
            }
            
            // Lưu chi tiết giao hàng vào DB
            deliveryDetailRepository.save(detail);
        }
        
        return delivery;
    }
}