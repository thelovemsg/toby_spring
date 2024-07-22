package tobyspring.hello_spring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface ExRateProvider {
    BigDecimal getExRate(String currency);
}
