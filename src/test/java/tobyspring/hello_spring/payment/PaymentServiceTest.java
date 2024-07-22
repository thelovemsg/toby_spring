package tobyspring.hello_spring.payment;

import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.*;


class PaymentServiceTest {

    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void converetdAmount() throws IOException {
        testAmount(valueOf(500), valueOf(5000), clock);
        testAmount(valueOf(500), valueOf(5000), clock);
        testAmount(valueOf(500), valueOf(5000), clock);
    }

    @Test
    void validUntil() throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // void until이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        // ChronoUnit을 사용하여 초 이하의 단위는 무시하고 비교
        assertThat(payment.getValidUntil()).isEqualToIgnoringNanos(expectedValidUntil);
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);
    }
}