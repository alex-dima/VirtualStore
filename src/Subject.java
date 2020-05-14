/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public interface Subject
{
    public void addObserver(Customer customer);
    public void removeObserver(Customer customer);
    public void notifyAllObservers(Notification notification);
}
