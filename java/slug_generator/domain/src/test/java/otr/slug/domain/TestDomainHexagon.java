package otr.slug.domain;

import org.junit.Test;

import otr.slug.domain.policy.CollapseMultipleUnderscoresPolicy;
import otr.slug.domain.policy.FilterStringPolicy;
import otr.slug.domain.policy.RemainYearPolicy;
import otr.slug.domain.policy.ReplaceNonAlphaDecimalPolicy;
import otr.slug.domain.policy.TrimUnderscoresPolicy;
import otr.slug.domain.service.SlugService;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDomainHexagon {

    @Test
    public void applyPolicies_getValidSlug() {
        // GIVEN
        String str = "Saweq- 2325a221w*^^!%#m asdp  2003 year 2nd-edition--";
        RawInput input = new RawInput(str);
        String expected = "saweq_a_w_m_asdp_2003_year_nd_edition";
        List<FilterStringPolicy> policies = List.of(
            String::toLowerCase,
            String::trim, // saweq- aw*^^!%#m asdp  2003 year nd-edition
            new ReplaceNonAlphaDecimalPolicy(), // saweq__aw______m_asdp__2003_year_nd_edition
            new RemainYearPolicy(),
        new CollapseMultipleUnderscoresPolicy(),
            new TrimUnderscoresPolicy()
        );

        // WHEN
        Slug actual = SlugService.createSlug(input, policies);

        // THEN
        assertThat(actual.value(), equalTo(expected));
    }

}
