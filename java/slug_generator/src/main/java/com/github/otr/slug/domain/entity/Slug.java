package com.github.otr.slug.domain.entity;

import com.github.otr.slug.domain.policy.FilterStringPolicy;

/**
 *
 */
public class Slug {
    private final String value;

    private static final FilterStringPolicy[] policies = {
            String::toLowerCase,
            String::trim,
    };

    private Slug(String value) {
        this.value = value;
    }

    public static Slug valueOf(String unparsed) {
        String output = unparsed;
        for (FilterStringPolicy policy : policies) {
            output = policy.filter(output);
        }
        return new Slug(output);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Slug{" +
                "value='" + value + '\'' +
                '}';
    }

}
