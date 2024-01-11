package util;


public class fiftyDiscount implements DiscountStrategy{
    public double applyDiscount(double originalPrice){
        return originalPrice * 0.5;
    }
}