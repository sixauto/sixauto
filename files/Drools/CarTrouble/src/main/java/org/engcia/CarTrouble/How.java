package org.engcia.CarTrouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.engcia.model.Fact;
import org.engcia.model.Evidence;
import org.engcia.model.Hypothesis;
import org.engcia.model.Justification;
import org.engcia.view.UI;

public class How {
    private Map<Integer, Justification> justifications;

    private static BufferedReader br;

    public How(Map<Integer, Justification> justifications) {
        this.justifications = justifications;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private String getIdentation(int level) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < level; i++) {
            sb.append('\t');
        }
        return sb.toString();
    }

    public void getHowExplanation(){
        boolean oneErrorCodeJustificationPresent = false;
        List<Justification> justificationList = new ArrayList<>(justifications.values());
        Collections.reverse(justificationList);

        for(Justification justification : justificationList){
            ArrayList<Evidence> evidences = new ArrayList<>();
            ArrayList<Hypothesis> hypotheses = new ArrayList<>();

            for(Fact fact : justification.getLhs()){
                if (fact instanceof Evidence){
                   // if(!UI.errorCodeMapping.values().contains(((Evidence) fact).getValue()) || !oneErrorCodeJustificationPresent){
                      //  oneErrorCodeJustificationPresent = true;
                        evidences.add((Evidence) fact);
                    //}
                } else if (fact instanceof Hypothesis){
                    hypotheses.add((Hypothesis) fact);
                }
            }

            if(hypotheses.size() > 0){
                System.out.println("The system reached '" + justification.getConclusion() + "' because of your answer to this question:");
                for(Hypothesis hypothesis : hypotheses){
                    System.out.println("\t- " + hypothesis.toString());
                }
                if(evidences.size() > 0){
                    System.out.println("And the system knew what to ask because of these evidences:");
                    for(Evidence evidence : evidences){
                        System.out.println("\t- " + evidence.toString());
                    }
                }
            } else if(evidences.size() > 0) {
                System.out.println("The system reached '" + justification.getConclusion() + "' because of these evidences:");
                for(Evidence evidence : evidences){
                    System.out.println("\t- " + evidence.toString());
                }
            }


            System.out.println("\n");
        }
    }
}
