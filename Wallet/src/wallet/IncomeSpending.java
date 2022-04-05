package wallet;

/**
 *
 * @author Group 4
 */
public class IncomeSpending {

    String reason;
    long money;
    String date;
    int Id;

    /**
     * Create new a IncomeSpending
     *
     * @param id
     * @param date
     * @param reason
     * @param money
     * @throws IncomeSpendingException
     */
    public IncomeSpending(int id, String date, String reason, long money) throws IncomeSpendingException {
        this.setMoney(money);
        this.setReason(reason);
        this.setDate(date);
        this.setId(id);
    }

    /**
     * Gets the ID of income and spending
     *
     * @return
     */
    public int getId() {
        return Id;
    }

    /**
     * Sets the ID of income and spending
     *
     * @param id
     * @throws IncomeSpendingException
     */
    public void setId(int id) throws IncomeSpendingException {
        if (id < 0) {
            throw new IncomeSpendingException("The ID must be a integer number!");
        } else {
            this.Id = id;
        }
    }

    /**
     * Gets the date of income and spending
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the ID of income and spending
     *
     * @param date
     * @throws IncomeSpendingException
     */
    public void setDate(String date) throws IncomeSpendingException {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.date = date;
        } else {
            throw new IncomeSpendingException("The date must be exactly format yyyy-mm-dd!");
        }
    }

    /**
     * Gets the reason of income and spending
     *
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason of income and spending
     *
     * @param reason
     * @throws IncomeSpendingException
     */
    public void setReason(String reason) throws IncomeSpendingException {
        if (reason.equals("")) {
            throw new IncomeSpendingException("The reason can't be empty!");
        } else {
            this.reason = reason;
        }
    }

    /**
     * Gets the money of income and spending
     *
     * @return
     */
    public long getMoney() {
        return money;
    }

    /**
     * Sets the money of income and spending
     *
     * @param money
     * @throws IncomeSpendingException
     */
    public void setMoney(long money) throws IncomeSpendingException {
        this.money = money;
    }
}
