import java.util.Calendar;
import java.util.Date;

/**
 * Created by alexandru.dima1609 on 08-Jan-19.
 */
public class Notification
{
    enum NotificationType {ADD, REMOVE, MODIFY};
    private Calendar date;
    private NotificationType type;
    private int departmentID;
    private int itemID;

    public Notification(Calendar date, NotificationType type, int departmentID, int itemID)
    {
        this.date=date;
        this.type=type;
        this.departmentID=departmentID;
        this.itemID=itemID;
    }

    public int getDepartmentID()
    {
        return departmentID;
    }

    public int getItemID()
    {
        return itemID;
    }

    public NotificationType getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return type + ";" + itemID + ";" + departmentID;
    }
}
