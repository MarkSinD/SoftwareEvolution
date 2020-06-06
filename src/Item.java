
class Item
{
    private Goods _Goods;
    private int _quantity;
    private double _price;

    public Item(Goods goods, int quantity, double price)
    {
        _Goods = goods;
        _quantity = quantity;
        _price = price;
    }

    public int getQuantity()
    {
        return _quantity;
    }
    public double getPrice()
    {
        return _price;
    }
    public Goods getGoods()
    {
        return _Goods;
    }

}
