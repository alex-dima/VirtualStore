/**
 * Created by alexandru.dima1609 on 11-Jan-19.
 */
public class StrategyB implements Strategy {
    @Override
    public Item execute(WishList wishList)
    {
        return wishList.getFirst().getElement();
    }
}
