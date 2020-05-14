import java.text.DecimalFormat;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public class Item
{
    private static DecimalFormat df2 = new DecimalFormat(".00");
    private String name;
    private int id;
    private double price;

    public Item(){}
    public Item(String name, int id, double price)
    {
        this.name=name;
        this.id=id;
        this.price=price;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Item))
            return false;
        Item item1 = (Item)obj;
        if(this.name.equals(item1.getName()) && this.id==item1.getId())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return name + ";" + id + ";" + df2.format(price);
    }
}
