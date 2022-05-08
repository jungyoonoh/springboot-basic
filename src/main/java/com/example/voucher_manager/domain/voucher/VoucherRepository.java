package com.example.voucher_manager.domain.voucher;

import com.example.voucher_manager.domain.customer.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoucherRepository {
    List<Voucher> findAll();
    List<Voucher> findVoucherListByCustomer(Customer customer);
    List<Voucher> findVoucherListByType(VoucherType voucherType);
    List<Voucher> findVoucherListByPeriods(LocalDateTime start, LocalDateTime end);
    Optional<Voucher> findById(UUID voucherId);
    Voucher update(Voucher voucher);
    Optional<Voucher> insert(Voucher voucher);
    void clear();
    void deleteVoucherByCustomer(Voucher voucher, Customer customer);
    boolean deleteVoucherById(UUID voucherId);
}