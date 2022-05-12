import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {

//Author Lucas

    Scanner scan = new Scanner(System.in);

    public TextUI(){

    }

    public String get(){
        System.out.print("\n>");
        return scan.nextLine();
    }

    public int getInteger(){
        {
            while(true)
            {
                String input = get();
                try
                {
                    return Integer.parseInt(input);
                }
                catch(NumberFormatException exp)
                {
                    System.out.println(("You must enter an integer!"));
                }
            }
        }
    }
    public void clear(){
        for(int i = 0; i < 100; ++i){
            System.out.println("");
        }
    }
    public int getInteger(int min, int max)
    {
        if(min > max)
        {
            throw new IllegalArgumentException("min > max, min: " + min + " max:" + max);
        }
        while(true)
        {
            int res = getInteger();
            if(res >= min && res <= max)
            {
                return res;
            }
            System.out.println(("You must enter an integer between " + min + " and " + max + ", both inclusive."));
        }
    }

    public int select(String header, ArrayList<String> choices, String footer)
    {
        System.out.println((header));
        int count = 0;
        for(String s : choices)
        {
            System.out.println(("    " + (++count) + " - " + s));
        }
        System.out.println((footer));

        return getInteger(1, count)-1;
    }
    public int select(String header, String[] choices, String footer)
    {
        System.out.println((header));
        int count = 0;
        for(String s : choices)
        {
            System.out.println(("    " + (++count) + " - " + s));
        }
        System.out.println((footer));
        return getInteger(1, count)-1;
    }


}
