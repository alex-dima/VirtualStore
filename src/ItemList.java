import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public abstract class ItemList
{
    static class Node<Item>
    {
        Item element;
        Node<Item> next;
        Node<Item> previous;

        public Node()
        {
            next=null;
            previous=null;

        }

        public void setElement(Item element)
        {
            this.element = element;
        }

        public void setNext(Node<Item> next)
        {
            this.next = next;
        }

        public void setPrevious(Node<Item> previous)
        {
            this.previous = previous;
        }

        public Item getElement()
        {
            return element;
        }

        public Node<Item> getNext()
        {
            return next;
        }

        public Node<Item> getPrevious()
        {
            return previous;
        }
    }

    class ItemIterator<E> implements ListIterator<E>
    {
        Node<Item> current;
        int currentIndex=1;

        public ItemIterator()
        {
            current=first;
        }

        public ItemIterator(int index)
        {
            current=first;
            for(int i=1;i<index;i++)
            {
                current=current.next;
                currentIndex++;
            }
        }
        @Override
        public boolean hasNext()
        {
            if(current==null)return false;
            if(current.next!=null)
                return true;
            return false;
        }

        @Override
        public E next()
        {
            if(hasNext())
            {
                current=current.next;
                currentIndex++;
            }
            return (E)current;
        }

        @Override
        public boolean hasPrevious()
        {
            if(current==null)return false;
            if(current.previous!=null)
                return true;
            return false;
        }

        @Override
        public E previous()
        {
           if(hasPrevious())
           {
               current=current.previous;
               currentIndex--;
           }
           return (E)current;
        }

        @Override
        public int nextIndex()
        {
            if(hasNext())
                return currentIndex+1;
            return currentIndex;
        }

        @Override
        public int previousIndex()
        {
            if(hasPrevious())
                return currentIndex-1;
            return currentIndex;
        }

        @Override
        public void remove() {}

        @Override
        public void set(E e) {}

        @Override
        public void add(E e) {}

    }

    private Node<Item> first;
    private Comparator defaultComparator;
    private Comparator comparator;
    public ItemList()
    {
        first=null;
    }

    public void setComparator(Comparator comparator)
    {
        this.comparator = comparator;
    }

    public void setDefaultComparator(Comparator defaultComparator)
    {
        this.defaultComparator = defaultComparator;
    }

    public Comparator getComparator()
    {
        return comparator;
    }

    public Comparator getDefaultComparator()
    {
        return defaultComparator;
    }

    public abstract boolean add(Item element);
    public abstract boolean addAll(Collection<? extends Item> c);
    public Item getItem(int index)
    {
        Node<Item> current=first;
        for(int i=1;i<index;i++)
        {
            current=current.next;
        }
        return current.getElement();
    }

    public Node<Item> getNode(int index)
    {
        Node<Item> current=first;
        for(int i=1;i<index;i++)
        {
            current=current.next;
        }
        return current;
    }

    public Node<Item> getFirst()
    {
        return first;
    }

    public void setFirst(Node<Item> first)
    {
        this.first = first;
    }

    public int indexOf(Item item)
    {
        Node<Item> current=first;
        int index=1;
        while(current!=null)
        {
            if(current.element.equals(item))
                return index;
            current=current.next;
            index++;
        }
        return 0;
    }

    public int indexOf(Node<Item> node)
    {
        return indexOf(node.getElement());
    }

    public int indexOf(int itemID)
    {
        Node<Item> current=first;
        int index=1;
        while(current!=null)
        {
            if(current.element.getId()==itemID)
                return index;
            current=current.next;
            index++;
        }
        return 0;
    }

    public boolean contains(Item item)
    {
        if(indexOf(item)!=0)return true;
        return false;
    }

    public boolean contains(int itemID)
    {
        if(indexOf(itemID)!=0)return true;
        return false;
    }

    public boolean contains(Node<Item> node)
    {
        if(indexOf(node)!=0)return true;
        return false;
    }

    public abstract Item remove(int index);

    public boolean remove(Item item)
    {
       if(remove(indexOf(item))!=null)
           return true;
       return false;
    }
    public boolean removeAll(Collection<? extends Item> collection )
    {
       boolean returnValue = true;
        for(Item item : collection)
        {
            returnValue = (returnValue&remove(item));
        }
        return true;
    }

    public boolean isEmpty()
    {
        if(first==null)return true;
        return false;
    }
    public ListIterator<Item> listIterator (int index)
    {
        return new ItemIterator<>(index);
    }
    public ListIterator<Item> listIterator ()
    {
        return new ItemIterator<>();
    }
    public Double getTotalPrice ()
    {
        ItemIterator<Node<Item>> itemIterator= new ItemIterator<>();
        double totalPrice=0;
        while(itemIterator.hasNext())
        {
            totalPrice+=itemIterator.current.getElement().getPrice();
            itemIterator.next();
        }
       if(itemIterator.current!=null)
       {
           totalPrice+=itemIterator.current.getElement().getPrice();
           itemIterator.next();
       }
        return totalPrice;
    }

    public Vector<Item> getVector()
    {
        Vector<Item> vector=new Vector<>();
        ItemIterator<Node<Item>> itemIterator=new ItemIterator<>();
        while(itemIterator.hasNext())
        {
            vector.add(itemIterator.current.getElement());
            itemIterator.next();
        }

        if(itemIterator.current!=null)
            vector.add(itemIterator.current.getElement());
        return vector;
    }
}
