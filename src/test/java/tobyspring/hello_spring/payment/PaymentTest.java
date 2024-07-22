package tobyspring.hello_spring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PaymentTest {
    @Test
    void createPrepared() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Payment payment = Payment.createPrepared(
                1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now()
        );

        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        Clock clock = Clock.fixed(Instant.now(),ZoneId.systemDefault());


    }
}
