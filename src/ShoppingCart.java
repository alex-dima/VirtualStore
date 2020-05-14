import java.util.Collection;
import java.util.Comparator;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public class ShoppingCart extends ItemList implements Visitor
{
    private double budget;
    public ShoppingCart(double budget)
    {
        this.budget=budget;
        setDefaultComparator(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Item item1= (Item)o1;
                Item item2= (Item)o2;
                if(item1.getPrice()>item2.getPrice())
                    return 1;
                else
                {
                    if(item1.getPrice()<item2.getPrice())
                        return -1;
                    else
                        return(item1.getName().compareTo(item2.getName()));
                }
            }
        });

    }

    public void setBudget(double budget)
    {
        this.budget = budget;
    }

    public double getBudget()
    {
        return budget;
    }

    @Override
    public boolean add(Item element)
    {

        if(budget<element.getPrice())return false;
        Node<Item> newNode = new Node<>();

        newNode.setElement(element);
        newNode.setPrevious(null);
        newNode.setNext(getFirst());

        if(getFirst()!=null)
            getFirst().setPrevious(newNode);
        setFirst(newNode);
        setBudget(getBudget()- newNode.getElement().getPrice());

        if (getFirst().next!=null)
        {
            Node<Item> current = newNode;
            Item swap;
            while(getDefaultComparator().compare(current.getElement(),current.next.getElement())>0)
            {
                swap=current.getElement();
                current.setElement(current.next.getElement());
                current.next.setElement(swap);
                current=current.next;
                if(current.next==null)
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Item> c)
    {
        boolean returnValue=true;
        for(Item item : c)
        {
            returnValue=(returnValue&add(item));
        }
        return returnValue;
    }

    @Override
    public Item remove(int index)
    {
        Node<Item> toRemove=getNode(index);

        if(index==1)
        {
            if(toRemove.next==null)
                setFirst(null);
            else
            {
                setFirst(toRemove.next);
            }
            return toRemove.getElement();
        }

        if(toRemove.previous!=null)
                toRemove.previous.next=toRemove.next;
        if(toRemove.next!=null)
            toRemove.next.previous=toRemove.previous;
        setBudget(getBudget() + toRemove.getElement().getPrice());
        return toRemove.getElement();
    }

    @Override
    public void visit(BookDepartment bookDepartment)
    {
        Node<Item> node = getFirst();
        while(node!=null)
        {
            if(bookDepartment.getItems().contains(node.getElement()))
                node.getElement().setPrice(node.getElement().getPrice() * 0.9);
            node=node.next;
        }
    }

    @Override
    public void visit(MusicDepartment musicDepartment)
    {
        Node<Item> node = getFirst();
        double totalPrice=0;
        while(node!=null)
        {
            if(musicDepartment.getItems().contains(node.getElement()))
                totalPrice+=node.getElement().getPrice();
            node=node.next;
        }
        setBudget(getBudget()+totalPrice*0.1);
    }

    @Override
    public void visit(SoftwareDepartment softwareDepartment)
    {

        if(getBudget()<softwareDepartment.getLowestPrice())
        {

            Node<Item> node = getFirst();
            while(node!=null)
            {
                if(softwareDepartment.getItems().contains(node.getElement()))
                    node.getElement().setPrice(node.getElement().getPrice()*0.8);
                node=node.next;
            }
        }
    }

    @Override
    public void visit(VideoDepartment videoDepartment)
    {
        Node<Item> node = getFirst();
        double totalPrice=0;
        while(node!=null)
        {
            if(videoDepartment.getItems().contains(node.getElement()))
                totalPrice+=node.getElement().getPrice();
            node=node.next;
        }
        if(totalPrice>videoDepartment.getHighestPrice())
        {
            node = getFirst();
            while(node!=null)
            {
                if(videoDepartment.getItems().contains(node.getElement()))
                    node.getElement().setPrice(node.getElement().getPrice()*0.85);
                node=node.next;
            }
        }
        setBudget(getBudget()+totalPrice*0.05);
    }
}
