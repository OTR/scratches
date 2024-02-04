package otr.slug.domain.policy;

/**
 * Replace all the characters that are not Alphabetical or Decimal
 * with underscore
 */
public class ReplaceNonAlphaDecimalPolicy implements FilterStringPolicy {

    @Override
    public String filter(String input) {
        return input.replaceAll("[^a-z0-9]", "_");
    }

}
