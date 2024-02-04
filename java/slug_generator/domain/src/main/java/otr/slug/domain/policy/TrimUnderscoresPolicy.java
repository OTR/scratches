package otr.slug.domain.policy;

/**
 * Trim leading and trailing underscores
 */
public class TrimUnderscoresPolicy implements FilterStringPolicy {

    @Override
    public String filter(String input) {
        return input.replaceAll("^_|_$", "");
    }
}
