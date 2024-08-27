package hyejin.tobyspring;

import hyejin.tobyspring.api.ApiTemplate;
import hyejin.tobyspring.api.ErApiExtractor;
import hyejin.tobyspring.api.SimpleApiExecutor;
import hyejin.tobyspring.exrate.CachedExRateProvider;
import hyejin.tobyspring.exrate.RestTemplateExRateProvider;
import hyejin.tobyspring.payment.ExRateProvider;
import hyejin.tobyspring.exrate.WebApiExRateProvider;
import hyejin.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
