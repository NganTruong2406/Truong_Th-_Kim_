package com.ckfm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class ReceiptRequest {

    @NotNull
    public Integer itemId;

    @NotNull
    public Integer batchId;

    @NotNull
    public Integer facilityId;

    @NotNull
    @Positive
    public BigDecimal quantity;
}