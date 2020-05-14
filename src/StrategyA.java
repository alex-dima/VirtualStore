import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 11-Jan-19.
 */
public class StrategyA implements Strategy {
    @Override
    public Item execute(WishList wishList)
    {
        Vector<Item> vector= wishList.getVector();
        Item minPriceItem=null;
        for(Item item : vector)
        {
            if(minPriceItem==null)
                minPriceItem=item;
            else
            {
                if(minPriceItem.getPrice()>item.getPrice())
                    minPriceItem=item;
            }
        }

        return minPriceItem;

    }
}
