import org.junit.Assert;
import org.junit.Test;

public class TestProgram {
    @Test
    public void NameShouldJack(){
        Customer c = new Customer("Jack", 10);
        String name = c.getName();
        Assert.assertEquals("Jack", name);
    }
    @Test
    public void NameShouldBill(){
        Customer c = new Customer("Bill", 10);
        String name = c.getName();
        Assert.assertEquals("Bill", name);
    }
    @Test
    public void BonusShould10(){
        Customer c = new Customer("Jack", 10);
        int bonus = c.getBonus();
        Assert.assertEquals(10, bonus);
    }
    @Test
    public void BonusShould0(){
        Customer c = new Customer("Jack", 10);
        c.receiveBonus(0);
        int bonus = c.getBonus();
        Assert.assertEquals(0, bonus);
    }
    @Test
    public void DiscountShouldNegative(){
        Customer c = new Customer("Jack", 50);
        int bonus = c.getBonus();
        int discount = c.useBonus(100);
        Assert.assertEquals(-50, discount);
    }

    @Test
    public void DiscountShould50(){
        Customer c = new Customer("Jack", 150);
        int bonus = c.getBonus();
        int discount = c.useBonus(50);
        Assert.assertEquals(50, discount);
    }

    @Test
    public void TitleShouldCola(){
        Goods goods = new Goods("Cola", 10);
        String name = goods.getTitle();
        Assert.assertEquals(name, "Cola");
    }

    @Test
    public void CodeShould10(){
        Goods goods = new Goods("Cola", 10);
        int code = goods.getPriceCode();
        Assert.assertEquals(code, 10);
    }

    @Test
    public void CodeShouldChange(){
        Goods goods = new Goods("Cola", 10);
        goods.setPriceCode(40);
        int code = goods.getPriceCode();
        Assert.assertEquals(code, 40);
    }

    @Test
    public void QuantityShould1(){
        Goods goods = new Goods("Cola", 10);
        Item item = new Item(goods, 1, 40);
        int quantity = item.getQuantity();
        Assert.assertEquals(quantity, 1);
    }

    @Test
    public void PriceShould40(){
        Goods goods = new Goods("Cola", 10);
        Item item = new Item(goods, 1, 40);
        double price = item.getPrice();
        Assert.assertEquals(price, 40, 0.0001);
    }

    @Test
    public void GoodShouldGood(){
        Goods goods = new Goods("Cola", 10);
        Item item = new Item(goods, 1, 40);
        Goods testGood = item.getGoods();
        Assert.assertEquals(testGood, goods);
    }

    @Test
    public void NameShouldJack21(){
        Customer c = new Customer("Jack", 10);
        Bill bill = new Bill(c);

        Goods good = new Goods("Pipsi", 3345 );
        Item item = new Item(good, 1, 60);
        bill.addGoods(item);

        String str = "Счет для Jack\n" +
                "\tНазвание\tЦена\tКол-во\tСтоим\tСкидк\tСумма\tБонус\n" +
                "\tPipsi\t\t60.0\t1\t\t60.0\t0.0\t60.0\t0\n" +
                "Сумма счета составляет 60.0\n" +
                "Вы заработали 0 бонусных балов";
        Assert.assertEquals(bill.statement(), str);
    }
}
