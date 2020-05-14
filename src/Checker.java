import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by alexandru.dima1609 on 11-Jan-19.
 */
public class Checker
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader1 = new BufferedReader(new FileReader("D:\\#DESKTOP\\Facultate\\Programare Orientata Pe Obiecte\\Tema\\Teste\\test09\\result.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("D:\\#DESKTOP\\Facultate\\Programare Orientata Pe Obiecte\\Tema\\Teste\\test09\\output.txt"));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        boolean areEqual = true;
        int lineNum = 1;
        while (line1 != null || line2 != null)
        {
            if(line1 == null || line2 == null)
            {
                areEqual = false;
                System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
            }
            else if(! line1.equalsIgnoreCase(line2))
            {
                areEqual = false;
                System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            lineNum++;
        }
        if(areEqual)
        {
            System.out.println("Two files have same content.");
        }

        reader1.close();
        reader2.close();
    }
}
