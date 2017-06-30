public class Blowout extends Sale {
    public double discount = 0.5;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double value) {
        discount = value;
    }

    public static void main(String[] args) {

        Sale sale = new Blowout();
        System.out.println(sale.discount);
        System.out.println(sale.getDiscount());
        Blowout blowout = (Blowout) sale;
        System.out.println(blowout.discount);
        sale.setDiscount(0.75);
        System.out.println(sale.discount);
    }
}
