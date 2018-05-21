import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Classifier {

    private Set<Instance> spamInstances = new HashSet<Instance>();
    private Set<Instance> notSpamInstances = new HashSet<Instance>();

    private Map<Integer, Double> trueAttributeProbabilityOnSpam;
    private Map<Integer, Double> falseAttributeProbabilityOnSpam;
    private Map<Integer, Double> trueAttributeProbabilityOnNotSpam;
    private Map<Integer, Double> falseAttributeProbabilityOnNotSpam;

    private Map<Integer, Double> trueAttributeProbability;
    private Map<Integer, Double> falseAttributeProbability;

    private double notSpamPriorProbability;
    private double spamPriorProbability;

    public Classifier(Set<Instance> data) {
        intializeMaps();
        calculateClassInstances(data);
        calculateAttributeClassInstances();
        spamPriorProbability=((double)(spamInstances.size())/(double)(notSpamInstances.size()+spamInstances.size()));
        notSpamPriorProbability=((double)(notSpamInstances.size())/(double)(notSpamInstances.size()+spamInstances.size()));
    }


    public void classifyInput(Instance instance){

        //first calculate prob(spam|x1,x2,x3..)

        double spamLiklyHood = 1.0;

        //calculate prob(x1,x2,x3|spam)
        for(int index=0; index<instance.getAttributeData().size();index++){
            if(instance.getAttributeData().get(index)==1)
                spamLiklyHood*=trueAttributeProbabilityOnSpam.get(index);
            else
                spamLiklyHood*=falseAttributeProbabilityOnSpam.get(index);

        }
        spamLiklyHood*=spamPriorProbability;


        //then calculate prob(notSpam|x1,x2,x3..)

        double notSpamLiklyHood=1.0;

        //calculate prob(x1,x2,x3|spam)
        for(int index=0; index<instance.getAttributeData().size();index++){
            if(instance.getAttributeData().get(index)==1)
                notSpamLiklyHood*=trueAttributeProbabilityOnNotSpam.get(index);
            else
                notSpamLiklyHood*=falseAttributeProbabilityOnNotSpam.get(index);

        }

        notSpamLiklyHood*=notSpamPriorProbability;


        double pX = 1.0;
        //calculate p(X)
        for(int index=0; index<instance.getAttributeData().size();index++){
            if(instance.getAttributeData().get(index)==1)
                pX*=trueAttributeProbability.get(index);
            else
                pX*=falseAttributeProbability.get(index);
        }

        //normalize
        notSpamLiklyHood=notSpamLiklyHood/pX;
        spamLiklyHood=spamLiklyHood/pX;

        System.out.println(instance.getAttributeData().toString());
        System.out.println(notSpamLiklyHood);
        System.out.println(spamLiklyHood);
        if(notSpamLiklyHood>spamLiklyHood)
            System.out.println("notSpam");
        else
            System.out.println("spam");

    }

    private void calculateAttributeClassInstances() {
        for (Instance i : spamInstances) {
            for (int index = 0; index < i.getAttributeData().size(); index++) {
                if (i.getAttributeData().get(index) == 1) {
                    trueAttributeProbabilityOnSpam.replace(index, trueAttributeProbabilityOnSpam.get(index) + 1);
                    trueAttributeProbability.replace(index, trueAttributeProbability.get(index) + 1);
                }
                else {
                    falseAttributeProbabilityOnSpam.replace(index, falseAttributeProbabilityOnSpam.get(index) + 1);
                    falseAttributeProbability.replace(index, falseAttributeProbability.get(index) + 1);
                }
            }
        }

        for (int index = 0; index < 12; index++) {
                trueAttributeProbabilityOnSpam.replace(index, trueAttributeProbabilityOnSpam.get(index)/(double)spamInstances.size());
                falseAttributeProbabilityOnSpam.replace(index, falseAttributeProbabilityOnSpam.get(index)/(double)spamInstances.size());
        }

        for (Instance i : notSpamInstances) {
            for (int index = 0; index < i.getAttributeData().size(); index++) {
                if (i.getAttributeData().get(index) == 1) {
                    trueAttributeProbabilityOnNotSpam.replace(index, trueAttributeProbabilityOnNotSpam.get(index) + 1);
                    trueAttributeProbability.replace(index, trueAttributeProbability.get(index) + 1);
                }
                else {
                    falseAttributeProbabilityOnNotSpam.replace(index, falseAttributeProbabilityOnNotSpam.get(index) + 1);
                    falseAttributeProbability.replace(index, falseAttributeProbability.get(index) + 1);
                }
            }
        }

        for (int index = 0; index < 12; index++){
            trueAttributeProbabilityOnNotSpam.replace(index, trueAttributeProbabilityOnNotSpam.get(index)/(double)notSpamInstances.size());
            falseAttributeProbabilityOnNotSpam.replace(index, falseAttributeProbabilityOnNotSpam.get(index)/(double)notSpamInstances.size());
            trueAttributeProbability.replace(index, trueAttributeProbability.get(index)/(double)notSpamInstances.size());
            falseAttributeProbability.replace(index, falseAttributeProbability.get(index)/(double)notSpamInstances.size());
        }

    }

    private void calculateClassInstances(Set<Instance> data) {
        for (Instance i : data) {
            if (i.getClassification() == true)
                spamInstances.add(i);
            else
                notSpamInstances.add(i);
        }
    }

    private void intializeMaps() {
        trueAttributeProbabilityOnSpam = new HashMap<Integer, Double>();
        trueAttributeProbabilityOnNotSpam = new HashMap<Integer, Double>();
        falseAttributeProbabilityOnSpam = new HashMap<Integer, Double>();
        falseAttributeProbabilityOnNotSpam = new HashMap<Integer, Double>();

        trueAttributeProbability= new HashMap<Integer, Double>();
        falseAttributeProbability = new HashMap<Integer, Double>();


        for (int i = 0; i < 12; i++) {

            trueAttributeProbabilityOnSpam.put(i, 1.0);
            trueAttributeProbabilityOnNotSpam.put(i, 1.0);
            falseAttributeProbabilityOnSpam.put(i, 1.0);
            falseAttributeProbabilityOnNotSpam.put(i, 1.0);

            trueAttributeProbability.put(i, 1.0);
            falseAttributeProbability.put(i, 1.0);

        }
    }

}