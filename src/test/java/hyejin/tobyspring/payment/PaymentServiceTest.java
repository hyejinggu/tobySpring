package hyejin.tobyspring.payment;

import hyejin.tobyspring.exrate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 모두 충족했는지 검증")
    void prepare() throws IOException {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000));
        testAmount(BigDecimal.valueOf(2000), BigDecimal.valueOf(20000));


        //assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        //assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}