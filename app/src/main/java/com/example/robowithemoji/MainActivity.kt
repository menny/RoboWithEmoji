package com.example.robowithemoji

import android.os.Bundle
import android.util.Xml
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val xml = resources.getXml(R.xml.doc_with_emoji)

        while (xml.next() != XmlPullParser.END_DOCUMENT) {
            if (xml.eventType == XmlPullParser.START_TAG) {
                if ("Root" == xml.name) {
                    val attrs = Xml.asAttributeSet(xml)
                    val label1 = attrs.getAttributeValue(null, "label1")
                    val label2 = attrs.getAttributeValue(null, "label2")
                    val label3 = attrs.getAttributeValue(null, "label3")
                    findViewById<TextView>(R.id.main_text).text = "label1 '$label1', label2 '$label2', label3 '$label3'"
                }
            }
        }
    }

}