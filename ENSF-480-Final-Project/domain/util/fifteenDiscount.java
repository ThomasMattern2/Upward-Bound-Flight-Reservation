package util;

public class fifteenDiscount implements DiscountStrategy{
    public double applyDiscount(double originalPrice){
        return originalPrice * 0.85;
    }
}