/**
 * Created by alexandru.dima1609 on 10-Jan-19.
 */
public class VideoDepartment extends Department
{
    public VideoDepartment() {}
    public VideoDepartment(String name, int id)
    {
        super(name, id);
    }

    @Override
    public void accept(ShoppingCart shoppingCart)
    {
        shoppingCart.visit(this);
    }

}
