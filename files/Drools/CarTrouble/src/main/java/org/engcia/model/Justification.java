package org.engcia.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Justification {
    private String rule;
    private Set<Fact> lhs;
    private Fact conclusion;

    public Justification(String rule, Set<Fact> lhs, Fact conclusion) {
        this.rule = rule;
        this.lhs = new LinkedHashSet<>(lhs);
        this.conclusion = conclusion;
    }

    public String getRuleName() {
        return this.rule;
    }

    public Set<Fact> getLhs() {
        return this.lhs;
    }

    public Fact getConclusion() {
        return this.conclusion;
    }
}
