import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public class Customer implements Observer
{
    private String name;
    private ShoppingCart shoppingCart;
    private WishList wishList;
    private Vector<Notification> notifications;

    public Customer(String name, double budget, String strategy)
    {
        this.name=name;
        shoppingCart = Store.getInstance().getShoppingCart(budget);
        wishList=new WishList(strategy);
        notifications=new Vector<>();
    }

    public String getName()
    {
        return name;
    }

    public void addShoppingCartItem(Item item)
    {
        shoppingCart.add(item);
    }

    public void removeShoppingCartItem(Item item)
    {
        shoppingCart.remove(item);
    }

    public ShoppingCart getShoppingCart()
    {
        return shoppingCart;
    }

    public void addWishListItem(Item item)
    {
        wishList.add(item);
    }

    public void removeWishListItem(Item item)
    {
        wishList.remove(item);
    }

    public WishList getWishList()
    {
        return wishList;
    }

    @Override
    public void update(Notification notification)
    {
        notifications.add(notification);
        Item sursa;
        int itemID;

        if(notification.getType()==Notification.NotificationType.MODIFY)
        {
            sursa = Store.getInstance().getItemFromID(notification.getItemID());
            if(shoppingCart.contains(sursa))
            {
               shoppingCart.setBudget(shoppingCart.getBudget()+shoppingCart.getItem(shoppingCart.indexOf(sursa)).getPrice()-sursa.getPrice());
                shoppingCart.getItem(shoppingCart.indexOf(sursa)).setPrice(sursa.getPrice());
            }

            if(wishList.contains(sursa))
            {
                wishList.getItem(wishList.indexOf(sursa)).setPrice(sursa.getPrice());
            }

            return;
        }
        if(notification.getType()==Notification.NotificationType.REMOVE)
        {
            itemID = notification.getItemID();
            if(shoppingCart.contains(itemID))
            {
                this.removeShoppingCartItem(shoppingCart.getItem(shoppingCart.indexOf(itemID)));
                Store.getInstance().getDepartment(notification.getDepartmentID()).exit(this);
            }
            if(wishList.contains(itemID))
            {
                this.removeWishListItem(wishList.getItem(wishList.indexOf(itemID)));
                Store.getInstance().getDepartment(notification.getDepartmentID()).removeObserver(this);
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public Vector<Notification> getNotifications()
    {
        return notifications;
    }
}
