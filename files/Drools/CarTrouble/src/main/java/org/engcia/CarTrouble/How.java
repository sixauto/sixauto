package org.engcia.CarTrouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.engcia.model.Fact;
import org.engcia.model.Hypothesis;
import org.engcia.model.Justification;

public class How {
    private Map<Integer, Justification> justifications;

    private static BufferedReader br;

    public How(Map<Integer, Justification> justifications) {
        this.justifications = justifications;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getHowExplanation(Integer factNumber) {
        return (getHowExplanation(factNumber, 0));
    }

    private String getHowExplanation(Integer factNumber, int level) {

            StringBuilder sb = new StringBuilder();
            Justification j = justifications.get(factNumber);
            if (j != null) { // justification for Fact factNumber was found
                sb.append("\n\n=== How did we reach this conclusion? ===\n");
                sb.append(getIdentation(level));
                sb.append("'" + j.getConclusion() + "'" + " was obtained by rule " + j.getRuleName() + " because");
                sb.append('\n');
                int l = level + 1;
                for (Fact f : j.getLhs()) {
                    sb.append(getIdentation(l));
                    sb.append(f);
                    sb.append('\n');
                    if (f instanceof Hypothesis) {
                        String s = getHowExplanation(f.getId(), l + 1);
                        sb.append(s);
                    }
                }
            }

            return sb.toString();
    }

    private String getIdentation(int level) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < level; i++) {
            sb.append('\t');
        }
        return sb.toString();
    }

    private static String readLine() {
        String input = "";

        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
