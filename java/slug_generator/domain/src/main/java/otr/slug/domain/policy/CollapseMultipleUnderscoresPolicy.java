package otr.slug.domain.policy;

/**
 * Collapse multiple underscores into a single underscore
 */
public class CollapseMultipleUnderscoresPolicy implements FilterStringPolicy {

    @Override
    public String filter(String input) {
        return input.replaceAll("_+", "_");
    }

}
