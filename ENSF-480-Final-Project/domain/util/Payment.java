package util;

public class Payment {
    private String creditCardNum;
    private int CVV;
    private DiscountStrategy discountStrategy;
    private double amountOwed;

    public Payment(String ccNum, int cvv) {
        this.creditCardNum = ccNum;
        this.CVV = cvv;
        this.discountStrategy = null;
        this.amountOwed = 0;
    }

    public String getCreditCardNumber() {
        return this.creditCardNum;
    }

    public int getCVV() {
        return this.CVV;
    }

    public void updatePaymentInfo(String ccNum, int cvv) {
        this.creditCardNum = ccNum;
        this.CVV = cvv;
    }

    public Payment getPayment() {
        return this;
    }

    public void setDiscount(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public DiscountStrategy getDiscountStrategy() {
        return this.discountStrategy;
    }

    public void performDiscount() {
        amountOwed = discountStrategy.applyDiscount(amountOwed);
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public void setOwed(double owed) {
        amountOwed = owed;
    }
}