import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 11-Jan-19.
 */
public class StrategyC implements Strategy {
    @Override
    public Item execute(WishList wishList)
    {
        return wishList.getLastAdded();
    }
}
