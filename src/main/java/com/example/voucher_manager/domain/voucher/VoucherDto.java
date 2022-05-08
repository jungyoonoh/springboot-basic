package com.example.voucher_manager.domain.voucher;

import java.time.LocalDateTime;
import java.util.UUID;

public record VoucherDto(UUID voucherId, Long discountInfo,
                         VoucherType voucherType, UUID ownerId,
                         LocalDateTime createdAt) {

    public UUID getVoucherId() {
        return voucherId;
    }

    public Long getDiscountInfo() {
        return discountInfo;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
