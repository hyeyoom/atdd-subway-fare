package nextstep.subway.maps.map.application;

public class FareCalculator {
    public int calculate(int distance) {
        int overDistance = distance - 10;
        if (overDistance > 0) {
            return 1250 + calculateOverFare(distance);
        }
        return 1250;
    }

    private int calculateOverFare(int distance) {
        return (int) ((Math.ceil((distance - 1) / 5) + 1) * 100);
    }
}
