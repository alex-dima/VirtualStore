import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

/**
 * Created by alexandru.dima1609 on 10-Jan-19.
 */
public class Test
{
    private static DecimalFormat df2 = new DecimalFormat(".00");
    public static void main(String args[])
    {

        for(int j=0;j<=9;j++)
        {
            File storeFile= new File("D:\\#DESKTOP\\Facultate\\Programare Orientata Pe Obiecte\\Tema\\Teste\\test0"+j+"\\store.txt");
            File customersFile= new File("D:\\#DESKTOP\\Facultate\\Programare Orientata Pe Obiecte\\Tema\\Teste\\test0"+j+"\\customers.txt");
            File eventsFile= new File("D:\\#DESKTOP\\Facultate\\Programare Orientata Pe Obiecte\\Tema\\Teste\\test0"+j+"\\events.txt");
            File outputFile= new File("D:\\#DESKTOP\\Facultate\\Programare Orientata Pe Obiecte\\Tema\\Teste\\test0"+j+"\\output.txt");

            try
            {
                int id;
                Scanner scanner = new Scanner(storeFile);
                PrintWriter writer=new PrintWriter(outputFile);
                String line;
                StringTokenizer tokenizer;
                Store store = Store.getInstance();
                int forReps;
                line = scanner.nextLine();
                store.setName(line);

                int departmentIndex=0;
                while(scanner.hasNextLine())
                {
                    line = scanner.nextLine();
                    tokenizer=new StringTokenizer(line,";");
                    switch(tokenizer.nextToken())
                    {
                        case "BookDepartment":
                            store.addDepartment(new BookDepartment("BookDepartment",Integer.parseInt(tokenizer.nextToken())));
                            break;
                        case "MusicDepartment":
                            store.addDepartment(new MusicDepartment("MusicDepartment",Integer.parseInt(tokenizer.nextToken())));
                            break;
                        case "SoftwareDepartment":
                            store.addDepartment(new SoftwareDepartment("SoftwareDepartment",Integer.parseInt(tokenizer.nextToken())));
                            break;
                        case "VideoDepartment":
                            store.addDepartment(new VideoDepartment("VideoDepartment",Integer.parseInt(tokenizer.nextToken())));
                            break;

                    }

                    forReps = Integer.parseInt(scanner.nextLine());
                    for(int i=0;i<forReps;i++)
                    {
                        line=scanner.nextLine();
                        tokenizer=new StringTokenizer(line,";");
                        store.getDepartments().elementAt(departmentIndex).addItem(new Item(tokenizer.nextToken(),Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken())));
                    }
                    departmentIndex++;
                }



                scanner = new Scanner(customersFile);
                forReps = Integer.parseInt(scanner.nextLine());
                for(int i = 0; i < forReps; i++)
                {
                    line = scanner.nextLine();
                    tokenizer = new StringTokenizer(line,";");
                    store.enter(new Customer(tokenizer.nextToken(),Double.parseDouble(tokenizer.nextToken()),tokenizer.nextToken()));
                }

                int itemID;
                int depID;
                double itemPrice;
                String itemName;
                String customerName;
                String shopOrWish;
                Item strategyItem;



                scanner = new Scanner(eventsFile);
                forReps = Integer.parseInt(scanner.nextLine());
                for(int i=1; i<=forReps; i++)
                {
                    line =scanner.nextLine();
                    tokenizer=new StringTokenizer(line,";");

                    switch(tokenizer.nextToken())
                    {
                        case "addItem":
                            itemID=Integer.parseInt(tokenizer.nextToken());
                            shopOrWish=tokenizer.nextToken();
                            customerName=tokenizer.nextToken();
                            if(shopOrWish.equals("ShoppingCart"))
                            {
                                store.getCustomerFromName(customerName).addShoppingCartItem(store.getItemFromID(itemID));
                                store.getDepartmentFromItemID(itemID).enter(store.getCustomerFromName(customerName));
                            }
                            else
                            {
                                store.getCustomerFromName(customerName).addWishListItem(store.getItemFromID(itemID));
                                store.getDepartmentFromItemID(itemID).addObserver(store.getCustomerFromName(customerName));
                            }
                            break;
                        case "delItem":
                            itemID=Integer.parseInt(tokenizer.nextToken());
                            shopOrWish=tokenizer.nextToken();
                            customerName=tokenizer.nextToken();
                            if(shopOrWish.equals("ShoppingCart"))
                            {
                                store.getCustomerFromName(customerName).removeShoppingCartItem(store.getItemFromID(itemID));
                                store.getDepartmentFromItemID(itemID).exit(store.getCustomerFromName(customerName));
                            }
                            else
                            {
                                store.getCustomerFromName(customerName).removeWishListItem(store.getItemFromID(itemID));
                                store.getDepartmentFromItemID(itemID).removeObserver(store.getCustomerFromName(customerName));
                            }
                            break;
                        case "addProduct":
                            depID=Integer.parseInt(tokenizer.nextToken());
                            itemID=Integer.parseInt(tokenizer.nextToken());
                            itemPrice=Double.parseDouble(tokenizer.nextToken());
                            itemName=tokenizer.nextToken();
                            store.getDepartment(depID).addItem(new Item(itemName,itemID,itemPrice));
                            store.getDepartment(depID).notifyAllObservers(new Notification(Calendar.getInstance(),Notification.NotificationType.ADD,depID,itemID));

                            break;
                        case "modifyProduct":
                            depID=Integer.parseInt(tokenizer.nextToken());
                            itemID=Integer.parseInt(tokenizer.nextToken());
                            itemPrice=Double.parseDouble(tokenizer.nextToken());
                            store.getDepartment(depID).getItem(itemID).setPrice(itemPrice);
                            store.getDepartment(depID).notifyAllObservers(new Notification(Calendar.getInstance(),Notification.NotificationType.MODIFY,depID,itemID));
                            break;
                        case "delProduct":
                            itemID=Integer.parseInt(tokenizer.nextToken());
                            depID=store.getDepartmentFromItemID(itemID).getId();
                            store.getDepartmentFromItemID(itemID).removeItem(itemID);
                            store.getDepartment(depID).notifyAllObservers(new Notification(Calendar.getInstance(),Notification.NotificationType.REMOVE,depID,itemID));
                            break;
                        case "getItem":
                            customerName=tokenizer.nextToken();
                            strategyItem=store.getCustomerFromName(customerName).getWishList().executeStrategy();

                            store.getCustomerFromName(customerName).removeWishListItem(strategyItem);
                            store.getDepartmentFromItemID(strategyItem.getId()).removeObserver(store.getCustomerFromName(customerName));

                            store.getCustomerFromName(customerName).addShoppingCartItem(strategyItem);
                            store.getDepartmentFromItemID(strategyItem.getId()).enter(store.getCustomerFromName(customerName));
                            writer.println(strategyItem);
                            break;
                        case "getItems":
                            shopOrWish=tokenizer.nextToken();
                            customerName=tokenizer.nextToken();
                            if(shopOrWish.equals("ShoppingCart"))
                                writer.println(store.getCustomerFromName(customerName).getShoppingCart().getVector());
                            else
                                writer.println(store.getCustomerFromName(customerName).getWishList().getVector());
                            break;
                        case "getTotal":
                            shopOrWish=tokenizer.nextToken();
                            customerName=tokenizer.nextToken();
                            if(shopOrWish.equals("ShoppingCart"))
                                writer.println(df2.format(store.getCustomerFromName(customerName).getShoppingCart().getTotalPrice()));
                            else
                                writer.println(df2.format(store.getCustomerFromName(customerName).getWishList().getTotalPrice()));
                            break;
                        case "accept":
                            depID=Integer.parseInt(tokenizer.nextToken());
                            customerName=tokenizer.nextToken();
                            store.getDepartment(depID).accept(store.getCustomerFromName(customerName).getShoppingCart());
                            break;
                        case "getObservers":
                            depID=Integer.parseInt(tokenizer.nextToken());
                            writer.println(store.getDepartment(depID).getObservers());
                            break;
                        case "getNotifications":
                            customerName=tokenizer.nextToken();
                            writer.println(store.getCustomerFromName(customerName).getNotifications());
                            break;

                    }
                }
                writer.close();
                store.reset();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }

        }

    }
}
