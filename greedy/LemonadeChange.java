package greedy;

/**
 * LeetCode 860 - Lemonade Change
 *
 * Greedy approach:
 *  - Always try to give change using larger bills first
 *  - Preserve smaller bills for future transactions
 */
public class LemonadeChange {

    /**
     * Determines if correct change can be provided to all customers.
     *
     * @param bills array of customer payments in sequence
     * @return true if change can be provided, false otherwise
     * 
     * Time Complexity: O(n) where n is the number of customers
     * Space Complexity: O(1) for storing bill counts
     */
    public boolean canProvideChange(int[] bills) {

        int fiveDollarBills = 0;
        int tenDollarBills = 0;

        for (int bill : bills) {

            if (bill == 5) {
                fiveDollarBills++;
            }

            else if (bill == 10) {
                if (fiveDollarBills == 0) {
                    return false;
                }
                fiveDollarBills--;
                tenDollarBills++;
            }

            else { // bill == 20
                // Prefer giving one $10 and one $5 as change
                if (tenDollarBills > 0 && fiveDollarBills > 0) {
                    tenDollarBills--;
                    fiveDollarBills--;
                }
                // Otherwise give three $5 bills
                else if (fiveDollarBills >= 3) {
                    fiveDollarBills -= 3;
                }
                // No valid way to give change
                else {
                    return false;
                }
            }
        }

        return true;
    }
}
