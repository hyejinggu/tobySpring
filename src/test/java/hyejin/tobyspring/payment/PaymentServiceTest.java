package hyejin.tobyspring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }


    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 모두 충족했는지 검증")
    void convertedAmount() {

        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), this.clock);
        testAmount(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), this.clock);
        testAmount(BigDecimal.valueOf(2000), BigDecimal.valueOf(20000), this.clock);
    }


    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}