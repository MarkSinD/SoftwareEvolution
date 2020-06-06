import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.util.*;

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

    public String statement() throws JSONException {

        double totalAmount = 0;
        int totalBonus = 0;
        Iterator<Item> items = _items.iterator();

        String result = null;

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
                    bonus = (int) (sumTotal * 0.05);
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

            Map obj=new LinkedHashMap();
            obj.put("name",each.getGoods().getTitle());
            obj.put("price", each.getPrice());
            obj.put("quantity", each.getQuantity());
            obj.put("prise", each.getQuantity() * each.getPrice());
            obj.put("discount", discount);
            obj.put("sum", thisAmount);
            obj.put("bonus",bonus);
            String jsonText = JSONValue.toJSONString(obj);
            System.out.print(jsonText + "\n");

            totalAmount += thisAmount;
            totalBonus += bonus;
        }

        Map obj=new LinkedHashMap();
        obj.put("totalAmount",totalAmount);
        obj.put("totalBonus", totalBonus);
        String jsonText = JSONValue.toJSONString(obj);
        System.out.print(jsonText + "\n");
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