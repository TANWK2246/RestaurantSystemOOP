/**
 * The type Service charge calculator.
 */
public class ServiceChargeCalculator {
    /**
     * The constant rate.
     */
    private static final double rate = 0.10;

    /**
     * Calculate double.
     *
     * @param amount the amount
     * @return the double
     */
    public static double calculate(double amount){
        return Math.round(amount * rate * 100)/100.0;
    }
}
