/**
 * Created by alexandru.dima1609 on 10-Jan-19.
 */
public class SoftwareDepartment extends Department {

    public SoftwareDepartment() {}
    public SoftwareDepartment(String name, int id)
    {
        super(name, id);
    }
    @Override
    public void accept(ShoppingCart shoppingCart)
    {
        shoppingCart.visit(this);
    }
}
