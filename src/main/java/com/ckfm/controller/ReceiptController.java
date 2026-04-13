package com.ckfm.controller;

import com.ckfm.dto.ReceiptRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @PostMapping
    public String test(@RequestBody ReceiptRequest req) {
        return "OK";
    }
}