package tobyspring.hello_spring.exrate;

import org.springframework.stereotype.Component;
import tobyspring.hello_spring.payment.ExRateProvider;

import java.math.BigDecimal;

@Component
public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        if(currency.equals("USD")) return BigDecimal.valueOf(1000);
        throw new IllegalArgumentException("지원되지 않은 통화입니다.");
    }
}
