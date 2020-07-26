package nextstep.subway.maps.map.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FareCalculatorTest {

    @DisplayName("기본 요금을 계산한다.")
    @Test
    void calculate() {
        final FareCalculator fareCalculator = new FareCalculator();
        int distance = 8;

        final int fare = fareCalculator.calculate(distance);

        assertThat(fare).isEqualTo(1250);
    }

    @DisplayName("10km초과 ∼ 50km 추가 요금을 계산한다.")
    @ParameterizedTest
    @CsvSource({"13, 1350", "21, 1550"})
    void calculateForExtraOverFare(int distance, int result) {
        final FareCalculator fareCalculator = new FareCalculator();

        final int fare = fareCalculator.calculate(distance);

        assertThat(fare).isEqualTo(result);
    }
}