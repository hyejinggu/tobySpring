package hyejin.tobyspring.exrate;

import hyejin.tobyspring.api.*;
import hyejin.tobyspring.payment.ExRateProvider;

import java.math.BigDecimal;

//@Component
public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;

        // client가 callback을 만들어서 템플릿을 호출한다.
        return apiTemplate.getForExRate(url);
    }

}
