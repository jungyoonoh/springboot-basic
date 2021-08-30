package org.prgrms.kdt.voucher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.prgrms.kdt.common.BaseRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yhh1056
 * Date: 2021/08/30 Time: 8:02 오후
 */

class VoucherJdbcRepositoryTest extends BaseRepositoryTest {

    @Autowired
    VoucherJdbcRepository voucherJdbcRepository;

    @Test
    @DisplayName("바우처 저장 테스트")
    void insert() {
        voucherJdbcRepository.insert(Voucher.of(1000L, VoucherType.FIX));

        int count = voucherJdbcRepository.count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("바우처 ID 조회 테스트")
    void findById() {
        UUID voucherId = UUID.randomUUID();
        Voucher voucher = givenVoucher(voucherId);
        voucherJdbcRepository.insert(voucher);

        Optional<Voucher> findVoucher = voucherJdbcRepository.findById(voucherId);

        assertFalse(findVoucher.isEmpty());
        assertThat(findVoucher.get().getVoucherId()).isEqualTo(voucherId);
    }

    private Voucher givenVoucher(UUID voucherId) {
        return new Voucher(voucherId, 50L, VoucherType.PERCENT, LocalDateTime.now());
    }
}