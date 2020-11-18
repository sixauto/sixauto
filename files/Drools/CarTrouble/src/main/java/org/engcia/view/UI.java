package org.engcia.view;

import org.apache.commons.lang3.StringUtils;
import org.engcia.CarTrouble.CarTrouble;
import org.engcia.model.Evidence;
import org.engcia.model.Hypothesis;
import org.engcia.model.Symptom;
import org.engcia.model.TroubleCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kie.api.runtime.ClassObjectFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UI {
    private static BufferedReader br;

    private static List<Symptom> symptomList = new ArrayList<>();

    public static List<String> errorCodeList = new ArrayList<>();

    public static Map<String, String> errorCodeMapping = new HashMap<>();

    public static void uiInit() {
        br = new BufferedReader(new InputStreamReader(System.in));
        symptomList = Arrays.asList(new Symptom(Symptom.LONG_ENGINE_CRANKING_TIME, "FLOW_1"),
                new Symptom(Symptom.SHARP_ACCELERATION, "FLOW_1"),
                new Symptom(Symptom.ENGINE_STARTS_WITHOUT_PROBLEMS, "FLOW_1"),
                new Symptom(Symptom.ENGINE_DIES_WHILE_DRIVING, "FLOW_1"),
                new Symptom(Symptom.HARD_SHIFTING, "FLOW_1"),
                new Symptom(Symptom.ENGINE_LIGHT_ON, "FLOW_BOTH"),
                new Symptom(Symptom.ENGINE_ROUGH_IDLE, "FLOW_BOTH"),
                new Symptom(Symptom.HARD_ENGINE_STARTING_CONDITION, "FLOW_2"),
                new Symptom(Symptom.ENGINE_DIES_SUDDENLY, "FLOW_2"),
                new Symptom(Symptom.LOSS_POWER_ENGINE, "FLOW_2"));

        errorCodeList = Arrays.asList("P0340", "P0341", "P0342", "P0343", "P0344");
        errorCodeMapping.put("P0340", Evidence.P0340);
        errorCodeMapping.put("P0341", Evidence.P0341);
        errorCodeMapping.put("P0342", Evidence.P0342);
        errorCodeMapping.put("P0343", Evidence.P0343);
        errorCodeMapping.put("P0344", Evidence.P0344);
    }

    public static void uiClose() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void askUserForErrorCodes() {

        System.out.println("Do you want to write the error codes manually or do you want to read from file? [manual/file]");
        String method = readLine();
        String errorCodes[] = new String[0];
        if (method.equals("manual")) {
            System.out.println("Which error codes do you have present in your OBD-II connector? [write the codes upper cased separated by commas]");
            String userAnswer = readLine();
            userAnswer = StringUtils.deleteWhitespace(userAnswer);
            errorCodes = userAnswer.split(",");

        } else if (method.equals("file")) {
            List<TroubleCode> troubleCodeList = readCodesFromFile();
            errorCodes = new String[troubleCodeList.size()];
            int i = 0;
            System.out.print("\n... The system read the following codes from the file: ");
            for(TroubleCode troubleCode : troubleCodeList){
                System.out.print(troubleCode.getCode() + " ");
                errorCodes[i] = troubleCode.getCode();
                i++;
            }
            System.out.println("\n");
        }

        String notKnownCodes = "";
        boolean ableToStartInferenceEngine = false;
        for (int i = 0; i < errorCodes.length; i++) {
            if (errorCodeList.contains(errorCodes[i])) {
                CarTrouble.KS.insert(new Evidence(errorCodeMapping.get(errorCodes[i])));
                ableToStartInferenceEngine = true;
            } else {
                notKnownCodes += errorCodes[i] + " ";
            }
        }
        if (!notKnownCodes.equals("") && ableToStartInferenceEngine) {
            System.out.println("The system doesn't know the following codes: " + notKnownCodes + ". We will ignore those codes and run the system based on the remaining codes.");
        } else if (!ableToStartInferenceEngine) {
            System.out.println("Unfortunately, the system doesn't know any of the codes you provided. We cannot help you.");
            System.exit(0);
        }
    }

    public static boolean answer(String ev, String v) {
        @SuppressWarnings("unchecked")
        Collection<Hypothesis> hypotheses = (Collection<Hypothesis>) CarTrouble.KS.getObjects(new ClassObjectFilter(Hypothesis.class));
        boolean questionFound = false;
        Hypothesis hypothesis = null;
        for (Hypothesis e : hypotheses) {
            if (e.getHypothesis().compareTo(ev) == 0) {
                questionFound = true;
                hypothesis = e;
                break;
            }
        }
        if (questionFound) {
            if (hypothesis.getValue().compareTo(v) == 0) {
                CarTrouble.agendaEventListener.addLhs(hypothesis);
                return true;
            } else {
                return false;
            }
        }
        System.out.print(ev + "? ");
        String value = readLine();

        Hypothesis e = new Hypothesis(ev, value);
        CarTrouble.KS.insert(e);

        if (value.compareTo(v) == 0) {
            CarTrouble.agendaEventListener.addLhs(e);
            return true;
        } else {
            return false;
        }
    }

    public static String readLine() {
        String input = "";

        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void symptomHandling() {

        String userSymptoms[] = askUserForSymptoms();

        List<Symptom> userSymptomsFlow1 = new ArrayList<>();
        List<Symptom> userSymptomsFlow2 = new ArrayList<>();

        for (int i = 0; i < userSymptoms.length; i++) {
            Symptom symptom = getPossibleSymptoms().get(Integer.parseInt(userSymptoms[i]) - 1);
            if (symptom.getFlow().equals("FLOW_1")) {
                userSymptomsFlow1.add(symptom);
            } else if (symptom.getFlow().equals("FLOW_2")) {
                userSymptomsFlow2.add(symptom);
            }
        }

        if (userSymptomsFlow1.size() > userSymptomsFlow2.size()) {
            CarTrouble.KS.insert(new Evidence(Evidence.FLOW_1));
        } else if (userSymptomsFlow1.size() < userSymptomsFlow2.size()) {
            CarTrouble.KS.insert(new Evidence(Evidence.FLOW_2));
        } else {
            int randomNumber = randomNumberBetween1and2();
            if (randomNumber == 1) {
                CarTrouble.KS.insert(new Evidence(Evidence.FLOW_1));
            } else {
                CarTrouble.KS.insert(new Evidence(Evidence.FLOW_2));
            }
        }
    }

    public static List<Symptom> getPossibleSymptoms() {
        return symptomList;
    }

    public static int randomNumberBetween1and2() {
        Random r = new Random();
        return r.nextInt(2) + 1;
    }

    public static String[] askUserForSymptoms() {
        int index = 1;
        StringBuilder symptomUI = new StringBuilder();
        symptomUI.append("\n\nWhich of this symptoms your car is facing? [write the numbers separated by commas]\n");
        for (Symptom symptom : getPossibleSymptoms()) {
            symptomUI.append("\n" + index + ": " + symptom.getValue());
            index++;
        }
        System.out.println(symptomUI);
        System.out.print("Your answer: ");
        String userAnswer = readLine();

        userAnswer = StringUtils.deleteWhitespace(userAnswer);
        return userAnswer.split(",");
    }

    private static List<TroubleCode> readCodesFromFile() {
        String fileName = "obd-II-test-file2.json";

        JSONParser jsonParser = new JSONParser();
        List<TroubleCode> troubleCodeList = new ArrayList<>();

        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(fileName));
            for (Object jsonObject : jsonArray) {
                TroubleCode troubleCode = new TroubleCode();
                troubleCode.setCode((String) ((JSONObject) jsonObject).get("code"));
                troubleCode.setDescription((String) ((JSONObject) jsonObject).get("description"));
                troubleCode.setManufacturer((String) ((JSONObject) jsonObject).get("manufacturer"));
                troubleCode.setSystem((String) ((JSONObject) jsonObject).get("system"));
                troubleCodeList.add(troubleCode);
            }
        } catch (Exception e) {

        }
        return troubleCodeList;
    }
}
