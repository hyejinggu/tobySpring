package hyejin.tobyspring;

import hyejin.tobyspring.exrate.CachedExRateProvider;
import hyejin.tobyspring.exrate.WebApiExRateProvider;
import hyejin.tobyspring.payment.ExRateProvider;
import hyejin.tobyspring.payment.ExRateProviderStub;
import hyejin.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }
}
