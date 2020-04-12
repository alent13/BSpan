package dev.klippe.library

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.getSpans

/**
 * https://developer.android.com/guide/topics/text/spans
 */

fun TextView.buildSpannableText(spans: SpannableStringBuilder.() -> SpannableStringBuilder)
        : SpannableStringBuilder = SpannableStringBuilder().apply { text = spans() }

fun TextView.buildClickableText(span: SpannableStringBuilder.() -> SpannableStringBuilder)
        : SpannableStringBuilder = SpannableStringBuilder().apply {
    text = span()
    highlightColor = Color.TRANSPARENT
    movementMethod = LinkMovementMethod.getInstance()
}

fun SpannableStringBuilder.block(spans: (SpannableStringBuilder) -> Unit) =
    append(SpannableStringBuilder().also(spans))

fun SpannableStringBuilder.block(text: String) = append(SpannableString(text))

fun SpannableStringBuilder.block(text: String, spans: SpannableString.() -> Unit) =
    append(SpannableString(text).apply(spans))

fun SpannableStringBuilder.space() = block(" ")

fun SpannableStringBuilder.newLine(count: Int = 1) = block("\n".repeat(count))

/**
 * https://developer.android.com/reference/android/text/style/package-summary
 */

var SpannableStringBuilder.textStyle: Int
    get() = getSpans<StyleSpan>().firstOrNull()?.style ?: Typeface.NORMAL
    set(value) {
        setSpan(StyleSpan(Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.textStyle: Int
    get() = getSpans<StyleSpan>().firstOrNull()?.style ?: Typeface.NORMAL
    set(value) {
        setSpan(StyleSpan(Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.relativeSize: Float
    get() = getSpans<RelativeSizeSpan>().firstOrNull()?.sizeChange ?: 1f
    set(value) {
        setSpan(RelativeSizeSpan(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.scaleX: Float
    get() = getSpans<ScaleXSpan>().firstOrNull()?.scaleX ?: 1f
    set(value) {
        setSpan(ScaleXSpan(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.textSize: Int
    get() = getSpans<AbsoluteSizeSpan>().firstOrNull()?.size ?: 1
    set(value) {
        setSpan(AbsoluteSizeSpan(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.textSizeDp: Int
    get() = getSpans<AbsoluteSizeSpan>().firstOrNull()?.size ?: 1
    set(value) {
        setSpan(AbsoluteSizeSpan(value, true), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.textColor: Int
    get() = getSpans<ForegroundColorSpan>().firstOrNull()?.foregroundColor ?: Color.TRANSPARENT
    set(value) {
        setSpan(ForegroundColorSpan(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.backgroundColor: Int
    get() = getSpans<BackgroundColorSpan>().firstOrNull()?.backgroundColor ?: Color.TRANSPARENT
    set(value) {
        setSpan(BackgroundColorSpan(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.underlined: Boolean
    get() = getSpans<UnderlineSpan>().isNotEmpty()
    set(value) {
        if (value) setSpan(UnderlineSpan(), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        else removeSpan(UnderlineSpan())
    }

var SpannableString.strikeThrough: Boolean
    get() = getSpans<StrikethroughSpan>().isNotEmpty()
    set(value) {
        if (value) setSpan(StrikethroughSpan(), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        else removeSpan(StrikethroughSpan())
    }

var SpannableString.subscript: Boolean
    get() = getSpans<SubscriptSpan>().isNotEmpty()
    set(value) {
        if (value) setSpan(SubscriptSpan(), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        else removeSpan(SubscriptSpan())
    }

var SpannableString.superscript: Boolean
    get() = getSpans<SuperscriptSpan>().isNotEmpty()
    set(value) {
        if (value) setSpan(SuperscriptSpan(), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        else removeSpan(SuperscriptSpan())
    }

var SpannableString.tabStop: Int
    get() = getSpans<TabStopSpan>().firstOrNull()?.tabStop ?: 1
    set(value) {
        setSpan(TabStopSpan.Standard(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.url: String
    get() = getSpans<URLSpan>().firstOrNull()?.url ?: ""
    set(value) {
        setSpan(URLSpan(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

var SpannableString.alignment: Layout.Alignment
    get() = getSpans<AlignmentSpan>().firstOrNull()?.alignment ?: Layout.Alignment.ALIGN_NORMAL
    set(value) {
        setSpan(AlignmentSpan.Standard(value), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

fun SpannableString.setSpan(span: Any) {
    setSpan(span, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableString.setMargin(first: Int, rest: Int) {
    setSpan(LeadingMarginSpan.Standard(first, rest), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableString.setTextAppearance(context: Context, @StyleRes textAppearanceRes: Int) {
    val textAppearanceSpan = TextAppearanceSpan(context, textAppearanceRes)
    setSpan(textAppearanceSpan, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableString.setFont(context: Context, @FontRes fontRes: Int) {
    val typeface = ResourcesCompat.getFont(context, fontRes) ?: throw IllegalArgumentException()
    setSpan(TypefaceSpanCompat(typeface), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

@RequiresApi(Build.VERSION_CODES.P)
fun SpannableString.setTypeface(typeface: String) {
    setSpan(TypefaceSpan(typeface), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

@RequiresApi(Build.VERSION_CODES.P)
fun SpannableString.bullet(gapWidth: Int, @ColorInt color: Int, bulletRadius: Int) {
    setSpan(BulletSpan(gapWidth, color, bulletRadius), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

@RequiresApi(Build.VERSION_CODES.P)
fun SpannableString.quote(@ColorInt color: Int) {
    setSpan(QuoteSpan(color), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

@RequiresApi(Build.VERSION_CODES.P)
fun SpannableString.quote(@ColorInt color: Int, stripeWidth: Int = 1, gapWidth: Int) {
    setSpan(QuoteSpan(color, stripeWidth, gapWidth), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun SpannableString.onClick(action: () -> Unit) {
    setSpan(SimpleClickableSpan(action), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

class SimpleClickableSpan(private val action: () -> Unit) : ClickableSpan() {
    override fun onClick(widget: View) {
        action()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        with(ds) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                underlineColor = Color.TRANSPARENT
            }
            isUnderlineText = false
        }
    }
}

class TypefaceSpanCompat(
    private val mTypeface: Typeface
) : MetricAffectingSpan() {

    override fun updateDrawState(ds: TextPaint) {
        apply(ds, mTypeface)
    }

    override fun updateMeasureState(paint: TextPaint) {
        apply(paint, mTypeface)
    }

    private fun apply(paint: Paint, typeface: Typeface) {
        val old = paint.typeface
        val oldStyle = old?.style ?: 0
        val fake = oldStyle and typeface.style.inv()
        if (fake and Typeface.BOLD != 0) {
            paint.isFakeBoldText = true
        }
        if (fake and Typeface.ITALIC != 0) {
            paint.textSkewX = -0.25f
        }
        paint.typeface = typeface
    }
}
