package org.engcia.CarTrouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.engcia.model.Symptom;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;

import org.engcia.model.Conclusion;
import org.engcia.model.Justification;
import org.engcia.view.UI;

public class CarTrouble {
    public static KieSession KS;
    public static BufferedReader BR;
    public static TrackingAgendaEventListener agendaEventListener;
    public static Map<Integer, Justification> justifications;

    private static BufferedReader br;

    public static final void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        UI.uiInit();
        runEngine();
        UI.uiClose();
    }

    private static void runEngine() {
        try {
            CarTrouble.justifications = new TreeMap<Integer, Justification>();

            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            final KieSession kSession = kContainer.newKieSession("ksession-rules");
            CarTrouble.KS = kSession;
            CarTrouble.agendaEventListener = new TrackingAgendaEventListener();
            kSession.addEventListener(agendaEventListener);

            // Query listener
            ViewChangedEventListener listenerConclusions = new ViewChangedEventListener() {
                @Override
                public void rowDeleted(Row row) {
                }

                @Override
                public void rowInserted(Row row) {
                    Conclusion conclusion = (Conclusion) row.get("$conclusion");

                    if(conclusion.getDescription().equals(Conclusion.UNKNOWN_PROBLEM)){
                        System.out.println(Conclusion.UNKNOWN_PROBLEM);
                        System.exit(0);
                    }

                    System.out.println("\n");
                    System.out.println(">>>" + conclusion.toString());

                    System.out.println("Do you want to know how did the system reach this conclusion?");
                    String answer = null;
                    try {
                        answer = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (answer.equals("yes")){
                        How how = new How(CarTrouble.justifications);
                        how.getHowExplanation();
                    }

                    // stop inference engine after as soon as got a conclusion
                    kSession.halt();

                }

                @Override
                public void rowUpdated(Row row) {
                }

            };

            LiveQuery query = kSession.openLiveQuery("Conclusions", null, listenerConclusions);

            System.out.println("\nRunning Six@uto expert system...\n\n");

            UI.askUserForErrorCodes();
            kSession.fireAllRules();

            // kSession.fireUntilHalt();

            query.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}

