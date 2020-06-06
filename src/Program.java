import org.json.JSONException;

class Program
{
    public static void main(String[] args) throws JSONException {
        Goods cola = new Goods("cola", Goods.REGULAR);
        Goods pepsi = new Goods("pepsi", Goods.SALE);

        Item i1 = new Item(cola, 6, 65);
        Item i2 = new Item(pepsi, 3, 50);

        Customer x = new Customer("Jahn", 10);


        Bill b1 = new Bill(x);


        b1.addGoods(i1);
        b1.addGoods(i2);
        String bill = b1.statement();
        System.out.println(bill);
    }
}