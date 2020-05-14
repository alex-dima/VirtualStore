/**
 * Created by alexandru.dima1609 on 10-Jan-19.
 */
public class MusicDepartment extends Department {
    public MusicDepartment() {}
    public MusicDepartment(String name, int id)
    {
        super(name, id);
    }

    @Override
    public void accept(ShoppingCart shoppingCart)
    {
        shoppingCart.visit(this);
    }
}
