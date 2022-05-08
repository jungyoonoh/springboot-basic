package com.example.voucher_manager.domain.voucher;

public class VoucherModelMapper {

    public static VoucherDto toDto(Voucher voucher) {
        return new VoucherDto(voucher.getVoucherId(),
                voucher.getDiscountInformation(),
                voucher.getVoucherType(),
                voucher.getOwnerId(),
                voucher.getCreatedAt());
    }

    public static Voucher toEntity(VoucherDto voucherDto) {
        if (voucherDto.getVoucherType().equals(VoucherType.FIXED)) {
            return FixedAmountVoucher.of(voucherDto);
        }
        return PercentDiscountVoucher.of(voucherDto);
    }
}
