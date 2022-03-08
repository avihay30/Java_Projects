package root;

/***
 * Class that enables to perform root (sqrt) calculations with some given precision.
 */
public class Rooter {

    private double precision;

    public Rooter(double precision) {
        this.precision = precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    /***
     * Calculate sqrt of given number,
     * according to the provided algorithm (in requirements).
     * @param x the number to calculated the sqrt of
     * @return sqrt of `x`
     */
    public double sqrt(double x) {
        double one = x / 2;
        double two;
        double diff;

        while (true) {
            two = x / one;

            if (one == two) {
                return one;
            }

            diff = absDiff(one, two);
            // if abs(diff) is lower than precision returning one or two (doesn't matter)
            if (diff < precision) {
                return one;
            }
            one = (one + two) / 2;
        }
    }

    /***
     * Util method that calculate the absolute different between x and y.
     * @return the abs diff (|x-y|)
     */
    private double absDiff(double x, double y) {
        if (x > y)
            return x-y;
        return y-x;
    }
}
