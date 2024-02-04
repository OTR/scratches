package otr.slug.domain.entity;

import otr.slug.domain.policy.FilterStringPolicy;
import otr.slug.domain.policy.RemainYearPolicy;

/**
 *
 */
public class Slug {

    private static final FilterStringPolicy[] policies = {
            String::toLowerCase,
            String::trim,
            new RemainYearPolicy()
    };

}
