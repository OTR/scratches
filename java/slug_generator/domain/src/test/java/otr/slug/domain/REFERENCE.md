# A suit of tests to be refactored

```java
@RunWith(Parameterized.class)
public class NormalizerTest {

    /**
     * A suit of test data pairs "input" -> "output"
     */
    @Parameterized.Parameters(name = "Filename #{index} should be {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                "[O`Reilly. Head First] - Head First Design Patterns - [Freeman].pdf",
                "oreilly_head_first_head_first_design_patterns_freeman"
            },
            {
                "Apress_Java_Design_Patterns.pdf",
                "apress_java_design_patterns"
            },
            {
                "C_M_Bishop__Pattern_Recognition_and_Machine_Learning (1).pdf",
                "c_m_bishop_pattern_recognition_and_machine_learning"
            },
            {
                "Data-Science-from-Scratch-First-Principles-with-Python-by-Joel-Grus-z-lib.org_.epub_.pdf",
                "data_science_from_scratch_first_principles_with_python_by_joel_grus_z_liborg_epub"
            },
            {
                "Get_Your_Hands_Dirty_on_Clean_Architecture_Tom_Hombergs.pdf",
                "get_your_hands_dirty_on_clean_architecture_tom_hombergs"
            },
            {
                "Growing Object-Oriented Software, Guided by Tests.pdf",
                "growing_object_oriented_software_guided_by_tests"
            },
            {
                "Hands-On_Artificial_Intelligence_with_Java_for_Beginners_Build_intelligent_apps_using_machine_learning_and_deep_learning.epub",
                "hands_on_artificial_intelligence_with_java_for_beginners_build_intelligent_apps_using_machine_learning_and_deep_learning"
            },
            {
                "Hands-On_Automation_Testing_with_Java_for_Beginners_Build_automation_testing_frameworks_from_scratch_with_Java.epub",
                "hands_on_automation_testing_with_java_for_beginners_build_automation_testing_frameworks_from_scratch_with_java"
            },
            {
                "Head First Design Patterns_ Building Extensible and -- Eric Freeman, Elisabeth Robson -- 2, 2020 -- O'Reilly Media -- 9781492078005 -- 82e7adbc05c9163080fcbc432a32fe04 -- Anna’s Archive.pdf",
                "head_first_design_patterns_building_extensible_and_eric_freeman_elisabeth_robson_oreilly_media_eadbccfcbcafe_annas_archive"
            },
            {
                "Head First Design Patterns_ Building Extensible and -- Eric Freeman; Elisabeth Robson -- Head First Series, 2, 2021 -- O'Reilly Media -- 9781492078005 -- baa86919b1e94222902d4b845d01df9b -- Anna’s Archive.pdf",
                "head_first_design_patterns_building_extensible_and_eric_freeman_elisabeth_robson_head_first_series_oreilly_media_baabedbddfb_annas_archive"
            }
        });
    };

    @Parameterized.Parameter(0)
    public String input;

    @Parameterized.Parameter(1)
    public String expected;

    /**
     * A positive test case, provide unstructured noizy filename
     * as an input to the method and check actual result with expected
     * "input" to "expected" pairs provided via Parametrized static member
     */
    @Test
    public void testPositiveTranslateToNormalized() {
        // GIVEN
        Normalizer normalizer = new Normalizer();

        // WHEN
        String actual = normalizer.normalize(input);

        // THEN
        assertThat(actual, equalTo(expected));

    }

}
```