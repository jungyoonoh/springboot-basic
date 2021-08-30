package org.prgrms.kdt.devcourse.voucher;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemoryVoucherRepositoryTest {

    @BeforeAll
    void setUp(){
        memoryVoucherRepository = new MemoryVoucherRepository();
        newFixedVoucher = new FixedAmountVoucher(UUID.randomUUID(),100);
        newPercentVoucher = new PercentDiscountVoucher(UUID.randomUUID(),10);
    }


    MemoryVoucherRepository memoryVoucherRepository;

    FixedAmountVoucher newFixedVoucher;
    PercentDiscountVoucher newPercentVoucher;


    @Test
    @DisplayName("메모리 바우처에 바우처를 추가할 수 있다.")
    @Order(1)
    void testInsert() {
        memoryVoucherRepository.insert(newFixedVoucher);

        Optional<Voucher> insertedVoucher = memoryVoucherRepository.findById(newFixedVoucher.getVoucherId());
        assertThat(insertedVoucher.isEmpty(),is(false));
        assertThat(insertedVoucher.get(),samePropertyValuesAs(newFixedVoucher));
    }

    @Test
    @DisplayName("메모리 바우처에서 id를 기반으로 찾을 수 있다.")
    @Order(2)
    void testFindById() {
        Optional<Voucher> insertedVoucher = memoryVoucherRepository.findById(newFixedVoucher.getVoucherId());
        assertThat(insertedVoucher.isEmpty(),is(false));
        assertThat(insertedVoucher.get(),samePropertyValuesAs(newFixedVoucher));
    }


    @Test
    @DisplayName("메모리 바우처의 모든 바우처를 가져올 수 있다.")
    @Order(3)
    void findAll() {
        List<Voucher> allVouchers = memoryVoucherRepository.findAll();
        assertThat(allVouchers.size(),is(1));

        memoryVoucherRepository.insert(newPercentVoucher);

        List<Voucher> afterInsertAllVouchers = memoryVoucherRepository.findAll();
        assertThat(afterInsertAllVouchers.size(),is(2));
        assertThat(afterInsertAllVouchers
                ,containsInAnyOrder(samePropertyValuesAs(newFixedVoucher), samePropertyValuesAs(newPercentVoucher)));
    }
}