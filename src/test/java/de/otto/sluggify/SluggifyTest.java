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
    public void shouldRemoveOpastrophes() {
        assertThat(Sluggify.sluggify("Mom's pants"), is("moms-pants"));
    }

    @Test
    public void shouldSluggifyOtherSpecialLetters() {
        assertThat(Sluggify.sluggify("ťśšşŝșùŵýžĜ ĤÌĴĶÑŚÙŴÝŹ"), is("tsssssuwyzg-hijknsuwyz"));
    }

    @Test
    public void shouldSlugifyNullsAndEmptyStrings() {
        assertThat(Sluggify.sluggify(null), is(nullValue()));
        assertThat(Sluggify.sluggify(""), is(""));
    }
}