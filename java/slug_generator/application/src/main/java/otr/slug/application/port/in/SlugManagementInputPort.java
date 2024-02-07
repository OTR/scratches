package otr.slug.application.port.in;

import otr.slug.application.port.out.SlugManagementOutputPort;
import otr.slug.application.usecase.SlugManagementUseCase;

import otr.slug.domain.policy.CollapseMultipleUnderscoresPolicy;
import otr.slug.domain.policy.FilterStringPolicy;
import otr.slug.domain.policy.RemainYearPolicy;
import otr.slug.domain.policy.ReplaceNonAlphaDecimalPolicy;
import otr.slug.domain.policy.TrimUnderscoresPolicy;
import otr.slug.domain.service.SlugService;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import java.util.Collections;
import java.util.List;

public class SlugManagementInputPort implements SlugManagementUseCase {

    private SlugManagementOutputPort outputPort;
    private List<FilterStringPolicy> policies;

    public SlugManagementInputPort(SlugManagementOutputPort outputPort) {
        this();
        this.outputPort = outputPort;
    }

    public SlugManagementInputPort() {
        setUpPolicies();
    }

    @Override
    public Slug createSlug(RawInput rawInput) {
        return SlugService.createSlug(rawInput, policies);
    }

    @Override
    public List<Slug> retrieveSlugs() {
        if (this.outputPort != null) {
            return this.outputPort.retrieveSlugs();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Slug persistSlug(Slug slug) {
        if (this.outputPort != null) {
            return this.outputPort.persistSlug(slug);
        } else {
            return slug;
        }
    }

    private void setUpPolicies() {
        policies = List.of(
            String::toLowerCase,
            String::trim,
            new ReplaceNonAlphaDecimalPolicy(),
            new RemainYearPolicy(),
            new CollapseMultipleUnderscoresPolicy(),
            new TrimUnderscoresPolicy()
        );
    }

}
