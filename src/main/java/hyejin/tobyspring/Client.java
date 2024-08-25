package hyejin.tobyspring;

import hyejin.tobyspring.payment.Payment;
import hyejin.tobyspring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment1: " + payment1);
        /*System.out.println("-------------------------------------------------\n");

        Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment2: " + payment2);

        TimeUnit.SECONDS.sleep(3);

        Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(60));
        System.out.println("payment3: " + payment3);*/
    }

}
