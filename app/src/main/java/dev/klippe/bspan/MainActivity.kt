package dev.klippe.bspan

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.core.text.buildSpannedString
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exampleText.buildClickableText {
            block("StyleSpan") { textStyle = Typeface.BOLD_ITALIC }
            newLine()
            block("CustomTypefaceSpan") { setFont(this@MainActivity, R.font.nautilus_pompilius) }
            newLine()
            block("UnderlineSpan") { underlined = true }
            space()
            block("TypefaceSpan") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    setTypeface("serif")
                }
            }
            block(", ")
            block("URLSpan") { url = "google.com" }
            block(", ")
            block("StrikethroughSpan") { strikeThrough = true }
            newLine()
            block("QuoteSpan") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    quote(Color.RED)
                }
            }
            newLine()
            block("Plain text")
            block("SubscriptSpan") { subscript = true }
            block("SuperscriptSpan") { superscript = true }
            newLine(2)
            block("BackgroundSpan") { backgroundColor = Color.LTGRAY }
            newLine(2)
            block("ForegroundColorSpan") { textColor = Color.LTGRAY }
            newLine()
            block("AlignmentSpan") { alignment = Layout.Alignment.ALIGN_CENTER }
            newLine()
            block("TextAppearanceSpan") {
                setTextAppearance(this@MainActivity, android.R.style.TextAppearance_Medium)
            }
            newLine()
            // TODO add image and icon spans
            newLine()
            block("RelativeSizeSpan") { relativeSize = 1.5f }
            newLine()
            block("AbsoluteSizeSpan") { textSize = 20 }
            newLine()
            block("AbsoluteSizeSpanDp") { textSizeDp = 20 }
            newLine(2)
            block("Multiple spans") {
                textStyle = Typeface.ITALIC
                underlined = true
                setTextAppearance(this@MainActivity, android.R.style.TextAppearance_Large)
                alignment = Layout.Alignment.ALIGN_CENTER
                backgroundColor = Color.LTGRAY
            }
            newLine()
            block("It's clickable") {
                textSizeDp = 24
                alignment = Layout.Alignment.ALIGN_CENTER
                textColor = Color.MAGENTA
                onClick { Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show() }
            }
            newLine()
            block {
                block("Global and")
                space()
                block("local") {
                    underlined = true
                }
                space()
                block("spans")
                textStyle = Typeface.BOLD
            }
        }
    }
}
