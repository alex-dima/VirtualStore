import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public class Store
{
    private static Store instance = null;
    private String name;
    private Vector<Department> departments;
    private Vector<Customer> customers;

    private Store()
    {
        departments = new Vector<>();
        customers = new Vector<>();
    }

    public static Store getInstance()
    {
        if(instance == null)
        {
            instance = new Store();
        }
        return instance;
    }

    public void reset()
    {
        instance = null;
        name= null;
        departments.clear();
        customers.clear();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return instance.name;
    }

    public void addDepartment(Department department)
    {
        instance.departments.add(department);
    }

    public Vector<Department> getDepartments()
    {
        return instance.departments;
    }

    public Department getDepartment(int id)
    {
        for( Department department : instance.departments)
        {
            if(department.getId() == id)
                return department;
        }
        return null;
    }

    public void  enter(Customer customer)
    {
        instance.customers.add(customer);
    }

    public void exit(Customer customer)
    {
        instance.customers.removeElement(customer);
    }

    public Vector<Customer> getCustomers()
    {
        return instance.customers;
    }

    public ShoppingCart getShoppingCart(Double budget)
    {
        return new ShoppingCart(budget);
    }

    public Item getItemFromID(int id)
    {
        for(Department department : instance.departments)
        {
            for(Item item : department.getItems())
            {
                if(item.getId() == id)
                    return item;
            }
        }
        return null;
    }
    public Department getDepartmentFromItemID(int id)
    {
        for(Department department : instance.departments)
        {
            for(Item item : department.getItems())
            {
                if(item.getId() == id)
                    return department;
            }
        }
        return null;
    }

    public Customer getCustomerFromName(String name)
    {
        for(Customer customer : instance.customers)
        {

            if(customer.getName().equals(name))
                return customer;
        }
        return null;
    }
}
