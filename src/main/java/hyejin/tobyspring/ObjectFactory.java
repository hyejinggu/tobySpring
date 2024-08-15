package hyejin.tobyspring;

import hyejin.tobyspring.exrate.CachedExRateProvider;
import hyejin.tobyspring.payment.ExRateProvider;
import hyejin.tobyspring.exrate.WebApiExRateProvider;
import hyejin.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider();
    }
}
