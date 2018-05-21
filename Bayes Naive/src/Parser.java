import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Parser {

    private Set<Instance> data;

    public Parser(String fName) {
        data= new HashSet<Instance>();
        try {
            Scanner scan = new Scanner(new File(fName));

            while (scan.hasNext()) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < 13; i++) {
                    if(fName.equals("spamUnlabelled.dat")&&i==12)
                        list.add(1);
                    else
                        list.add(scan.nextInt());
                }
                data.add(new Instance(list));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Set<Instance> getData() {
        return data;
    }

}
