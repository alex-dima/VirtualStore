import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 10-Jan-19.
 */
public class BookDepartment extends Department {

    public BookDepartment() {}
    public BookDepartment(String name, int id)
    {
        super(name, id);
    }
    @Override
    public void accept(ShoppingCart shoppingCart)
    {
        shoppingCart.visit(this);
    }
}
