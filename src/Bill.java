import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Bill {

    private List<Item> _items;
    private Customer _customer;

    public Bill(Customer customer) {
        this._customer = customer;
        this._items = new ArrayList<>();
    }

    public void addGoods(Item arg) {
        _items.add(arg);
    }

    public String statement() {

        double totalAmount = 0;
        int totalBonus = 0;
        Iterator<Item> items = _items.iterator();

        String result = tableHeaderOutput();

        while (items.hasNext()) {
            double thisAmount = 0;
            double discount = 0;
            double sumTotal = 0;
            int bonus = 0;
            Item each = (Item) items.next();
            sumTotal = each.getQuantity() * each.getPrice();
            switch (each.getGoods().getPriceCode()) {
                case Goods.REGULAR:
                    if (each.getQuantity() > 2)
                        discount = sumTotal * 0.03;
                    bonus = (int) (each.getQuantity() * each.getPrice() * 0.05);
                    break;
                case Goods.SPECIAL_OFFER:
                    if (each.getQuantity() > 10)
                        discount = sumTotal * 0.005; // 0.5%
                    break;
                case Goods.SALE:
                    if (each.getQuantity() > 3)
                        discount = sumTotal * 0.01; // 0.1%
                    bonus = (int) (sumTotal * 0.01);
                    break;
            }
            if (isDiscounting(each))
                discount = _customer.useBonus((int) (sumTotal));
            thisAmount = sumTotal - discount;
            result += "\t" + each.getGoods().getTitle() + "\t" +
                    "\t" + each.getPrice() + "\t" + each.getQuantity() +
                    "\t" + "\t" + each.getQuantity() * each.getPrice() +
                    "\t"  + discount + "\t"  + thisAmount +
                    "\t" + bonus + "\n";
            totalAmount += thisAmount;
            totalBonus += bonus;
        }
        result += "Сумма счета составляет " + totalAmount + "\n";
        result += "Вы заработали " + totalBonus + " бонусных балов";
        _customer.receiveBonus(totalBonus);
        return result;
    }

    boolean isDiscounting(Item each){
        return (each.getGoods().getPriceCode() == Goods.SPECIAL_OFFER) && each.getQuantity() > 1;
    }

    String tableHeaderOutput(){
        String result = "Счет для " + _customer.getName() + "\n";
        result += "\t" + "Название" + "\t" + "Цена" +
                "\t" + "Кол-во" + "\t" + "Стоим" + "\t" + "Скидк" +
                "\t" + "Сумма" + "\t" + "Бонус" + "\n";
        return result;
    }
}