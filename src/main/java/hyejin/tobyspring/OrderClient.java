package hyejin.tobyspring;

import hyejin.tobyspring.data.OrderRepository;
import hyejin.tobyspring.order.Order;
import hyejin.tobyspring.order.OrderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class OrderClient {
    public static void main(String[] args) {

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
        OrderService service = beanFactory.getBean(OrderService.class);

        Order order = service.createOrder("111", BigDecimal.TEN);
        System.out.println(order);
    }
}
