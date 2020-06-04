
public class Goods
{
    public static final int REGULAR = 0;
    public static final int SALE = 1;
    public static final int SPECIAL_OFFER = 2;
    private String _title;
    private int _priceCode;

    public Goods(String title, int priceCode)
    {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode()
    {
        return _priceCode;
    }
    public void setPriceCode(int arg)
    {
        _priceCode = arg;
    }
    public String getTitle()
    {
        return _title;
    }
}
