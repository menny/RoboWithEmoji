package com.example.robowithemoji

import android.app.Application
import android.util.AttributeSet
import android.util.Xml
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.xmlpull.v1.XmlPullParser

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    private fun getAttributeSet(): AttributeSet {
        val xml = getApplicationContext<Application>().resources.getXml(R.xml.doc_with_emoji)
        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.eventType == XmlPullParser.START_TAG) {
                if ("Root" == xml.name) {
                    return Xml.asAttributeSet(xml)
                }
            }
        }

        throw IllegalStateException("Should have found the root tag")
    }

    @Test
    fun testAttributeValueShouldNotBeResourceId_label1() {
        Assert.assertEquals(0, getAttributeSet().getAttributeResourceValue(null, "label1", 0));
    }

    @Test
    fun testNoEmoji_label1() {
        Assert.assertEquals("no emoji", getAttributeSet().getAttributeValue(null, "label1"));
    }

    @Test
    fun testAttributeValueShouldNotBeResourceId_label2() {
        Assert.assertEquals(0, getAttributeSet().getAttributeResourceValue(null, "label2", 0));
    }

    @Test
    fun testAttributeValueShouldBeEmoji_label2() {
        Assert.assertEquals("\uD83D\uDE00", getAttributeSet().getAttributeValue(null, "label3"));

    }

    @Test
    fun testAttributeValueShouldNotBeResourceId_label3() {
        Assert.assertEquals(0, getAttributeSet().getAttributeResourceValue(null, "label3", 0));
    }

    @Test
    fun testAttributeValueShouldBeEmoji_label3() {
        Assert.assertEquals("\uD83D\uDE00", getAttributeSet().getAttributeValue(null, "label3"));

    }

    @Test
    fun getTextFromActivity() {
        ActivityScenario.launch(MainActivity::class.java)
            .moveToState(Lifecycle.State.RESUMED)
            .onActivity { Assert.assertEquals(
                "label1 'no emoji', label2 '\uD83D\uDE00', label3 '\uD83D\uDE00'",
                it.findViewById<TextView>(R.id.main_text).text.toString()) }
    }

    @Test
    fun testAttributeValueShouldBeEmoji_label3_what_should_not_return() {
        Assert.assertNotEquals("\uFFFD\uFFFD", getAttributeSet().getAttributeValue(null, "label3"));
    }
}