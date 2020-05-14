import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public abstract class Department implements Subject
{
    private String name;
    private Vector<Item> items;
    private Vector<Customer> customers;
    private Vector<Customer> observers;
    private  int id;

    public Department()
    {
        items = new Vector<>();
        customers = new Vector<>();
        observers = new Vector<>();
    }
    public Department(String name, int id)
    {
        this.name=name;
        this.id=id;
        items = new Vector<>();
        customers = new Vector<>();
        observers = new Vector<>();
    }

    public void enter(Customer customer)
    {
        if(!(customers.contains(customer)))
            customers.add(customer);
    }

    public void exit(Customer customer)
    {
        boolean member=false;
        for(Item item : customer.getShoppingCart().getVector())
        {
            if(items.contains(item))
                member=true;
        }
        if(!member)
            customers.removeElement(customer);
    }

    public Vector<Customer> getCustomers()
    {
        return customers;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        items.removeElement(item);
    }

    public void removeItem(int itemID)
    {
       for(Item item : items)
       {
           if(item.getId() == itemID)
           {
               removeItem(item);
               break;
           }
       }
    }

    public Vector<Item> getItems()
    {
        return items;
    }

    @Override
    public void addObserver(Customer observer)
    {
        if(!(observers.contains(observer)))
            observers.add(observer);
    }

    @Override
    public void removeObserver(Customer observer)
    {
        boolean member=false;
        for(Item item : observer.getWishList().getVector())
        {
            if(items.contains(item))
                member=true;
        }
        if(!member)
            observers.removeElement(observer);
    }

    @Override
    public void notifyAllObservers(Notification notification)
    {
        Vector<Customer> copie= new Vector<>(observers);
        for(Customer observer : copie)
        {
            observer.update(notification);
        }
    }

    public abstract void accept(ShoppingCart shoppingCart);

    public double getLowestPrice()
    {
        double lowestPrice=Double.MAX_VALUE;
        for(Item item : items)
        {
            if(lowestPrice>item.getPrice())
                lowestPrice=item.getPrice();
        }
        return lowestPrice;
    }

    public double getHighestPrice()
    {
        double highestPrice=0.0;
        for(Item item : items)
        {
            if(highestPrice<item.getPrice())
                highestPrice=item.getPrice();
        }
        return highestPrice;
    }
    public Item getItem(int itemID)
    {
        for (Item item : items)
        {
            if(item.getId()==itemID)
                return item;
        }
        return null;
    }

    public Vector<Customer> getObservers()
    {
        return observers;
    }
}
