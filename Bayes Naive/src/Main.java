import java.util.Set;
import java.util.HashSet;

public class Main {

    public Main(String fName1, String fName2) {
        Set<Instance> dataToBuildClassification = new HashSet<Instance>();
        Set<Instance> dataToClassify = new HashSet<Instance>();
        Parser p1 = new Parser(fName1);
        Parser p2 = new Parser(fName2);
        dataToBuildClassification = p1.getData();
        dataToClassify = p2.getData();
        Classifier c = new Classifier(dataToBuildClassification);

        for(Instance i:dataToClassify){
            c.classifyInput(i);
        }
    }

    public static void main(String[] args) {
        new Main(args[0], args[1]);

    }
}