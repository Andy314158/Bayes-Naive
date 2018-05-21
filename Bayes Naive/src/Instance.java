import java.util.ArrayList;

public class Instance {

    private boolean classification;
    private ArrayList<Integer> attributeData;

    public Instance(ArrayList<Integer> list2) {

        attributeData = new ArrayList<Integer>(list2.subList(0, 12));
        System.out.println(attributeData.toString());

        if(list2.get(12)==1)
            classification=true;
        else
            classification=false;
    }

    public boolean getClassification() {
        return classification;
    }

    public ArrayList<Integer> getAttributeData(){
        return attributeData;
    }
}
