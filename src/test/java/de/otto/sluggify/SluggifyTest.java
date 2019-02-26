package de.otto.sluggify;

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
    public void shouldSluglifySomeTextWithLeadingAndTrailingHyphen() {
        assertThat(Sluggify.sluggify("-Schöne neue Röhrenjeans in Größe 42-"), is("schoene-neue-roehrenjeans-in-groesse-42"));
    }

    @Test
    public void shouldSluggifyAmpersandCharacters() {
        assertThat(Sluggify.sluggify("Röhrenjeans & Wäsche"), is("roehrenjeans-waesche"));
    }

    @Test
    public void shouldSluggifyLeetHaxorSpeak() {
        assertThat(Sluggify.sluggify("-ĿēĚt^ħĂxôŔ-"), is("leet-haxor"));
    }

    @Test
    public void shouldRemoveSuccessiveHyphens() {
        assertThat(Sluggify.sluggify("Ŀē---Ět^-^ħĂx...ôŔ"), is("le-et-hax-or"));
    }

    @Test
    public void shouldNotReplaceUnderscores() {
        assertThat(Sluggify.sluggify("Under_Score"), is("under_score"));
    }

    @Test
    public void shouldRemoveOpastrophesBeforeLetterS() {
        assertThat(Sluggify.sluggify("Mom's pants"), is("moms-pants"));
    }

    @Test
    public void shouldReplaceOpastrophesWithDashIfNotFollowedByLetterS() {
        assertThat(Sluggify.sluggify("Turtles'pants are awkward'"), is("turtles-pants-are-awkward"));
    }

    @Test
    public void shouldSluggifyOtherSpecialLetters() {
        assertThat(Sluggify.sluggify("ťśšşŝșùŵýžĜ ĤÌĴĶÑŚÙŴÝŹ"), is("tsssssuwyzg-hijknsuwyz"));
    }

    @Test
    public void shouldSluggifySingleWeirdCharacter() {
        assertThat(Sluggify.sluggify("®"), is(""));
    }

    @Test
    public void shouldSlugifyNullsAndEmptyStrings() {
        assertThat(Sluggify.sluggify(null), is(nullValue()));
        assertThat(Sluggify.sluggify(""), is(""));
    }

    @Test
    public void slugifyPlusSymbol() throws Exception {
        assertThat(Sluggify.sluggify("A+"), is("aplus"));
        assertThat(Sluggify.sluggify("A++"), is("aplusplus"));
        assertThat(Sluggify.sluggify("A+++"), is("aplusplusplus"));
    }

    @Test
    public void slugifyMitLetztesZeichenAlsBindestrich() {
        assertThat(Sluggify.sluggify("David-schreibt-auch-einen-Test-"), is("david-schreibt-auch-einen-test"));
    }

    @Test
    public void slugifyMitSonderzeichen() {
        assertThat(Sluggify.sluggify("Haup(t)hose_+*~#'/-\"'un[d]so--Wahns{i}n.n;"), is("haup-t-hose_plus-un-d-so-wahns-i-n-n"));
    }

    @Test
    public void shouldSluggifyCategoryPath() {
        // given
        String categoryTitle = "Mode>Jungen>Mädchen>Schnürschuhe";

        // when
        String path = Sluggify.sluggifyPath(categoryTitle, ">", "/");

        // then
        assertThat(path.toString(), is("mode/jungen/maedchen/schnuerschuhe"));
    }

    @Test
    public void shouldSluggifyCategoryPathWithOnePathElement() {
        // given
        String categoryTitle = "Mode";

        // when
        String path = Sluggify.sluggifyPath(categoryTitle, ">", "/");

        // then
        assertThat(path.toString(), is("mode"));
    }

    @Test
    public void shouldSluggifyCategoryPathWithTwoEmptyPathElements() {
        // given
        String categoryTitle = "Mode>>";

        // when
        String path = Sluggify.sluggifyPath(categoryTitle, ">", "/");

        // then
        assertThat(path.toString(), is("mode//"));
    }
}