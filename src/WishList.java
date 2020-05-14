import java.util.Collection;
import java.util.Comparator;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public class WishList extends ItemList
{
    private Strategy strategy;
    private Item lastAdded;
    public WishList(String strategy)
    {
        switch(strategy)
        {
            case "A":
                this.strategy=new StrategyA();
                break;
            case "B":
                this.strategy=new StrategyB();
                break;
            case "C":
                this.strategy=new StrategyC();
                break;
        }
        setDefaultComparator(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Item item1= (Item)o1;
                Item item2= (Item)o2;
                return(item1.getName().compareTo(item2.getName()));
            }
        });
    }
    @Override
    public boolean add(Item element) {
        if(contains(element))return false;
        Node<Item> newNode= new Node<>();

        newNode.setElement(element);
        newNode.setPrevious(null);
        newNode.setNext(getFirst());

        if(getFirst()!=null)
            getFirst().setPrevious(newNode);
        setFirst(newNode);
        lastAdded = newNode.getElement();
        if(getFirst().next!=null)
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
        return toRemove.getElement();
    }

    public Item executeStrategy()
    {
        return strategy.execute(this);
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public Strategy getStrategy()
    {
        return strategy;
    }

    public Item getLastAdded()
    {
        return lastAdded;
    }
}
