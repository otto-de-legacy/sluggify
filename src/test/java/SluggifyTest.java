import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SluggifyTest {

    @Test
    public void shouldSluglifySomeText() {
        assertThat(Sluggify.sluggify("Schöne neue Röhrenjeans in Größe 42"), is("schoene-neue-roehrenjeans-in-groesse-42"));
    }

    @Test
    public void shouldSluglifySomeTextWithLeadingHyphen() {
        assertThat(Sluggify.sluggify("-Schöne neue Röhrenjeans in Größe 42-"), is("schoene-neue-roehrenjeans-in-groesse-42"));
    }

    @Test
    public void shouldSluggifyAmpPercentCharacters() {
        assertThat(Sluggify.sluggify("Röhrenjeans & Wäsche"), is("roehrenjeans-waesche"));
    }

    @Test
    public void shouldSlugifyNullsAndEmptyStrings() {
        assertThat(Sluggify.sluggify(null), is(nullValue()));
        assertThat(Sluggify.sluggify(""), is(""));
    }
}