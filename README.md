# BSpan
### Project in beta
Kotlin extentions for [text spans](https://developer.android.com/guide/topics/text/spans)

## Usage
Start building text by call `buildSpannableText` or `buildClickableText` on TextView object.

Than call `block` to add spannable text. You also can use special blocks like `newLine` or `space`.

Every block can be midified by [spans](https://developer.android.com/reference/android/text/style/package-summary):
|Extension|Span|Description|
| ------------- | --------------------| ------------------------------------------------------|
|textStyle|StyleSpan|Span that allows setting the style of the text it's attached to.|
|relativeSize|RelativeSizeSpan|Uniformly scales the size of the text to which it's attached by a certain proportion.|
|scaleX|ScaleXSpan|Scales horizontally the size of the text to which it's attached by a certain factor.|
|textSize|AbsoluteSizeSpan|A span that changes the size of the text it's attached to.|
|textSizeDp|AbsoluteSizeSpan|A span that changes the size of the text it's attached to (Using DP value).|
|textColor|ForegroundColorSpan|Changes the color of the text to which the span is attached.|
|backgroundColor|BackgroundColorSpan| Changes the background color of the text to which the span is attached.|
|underlined|UnderlineSpan|A span that underlines the text it's attached to.|
|strikeThrough|StrikethroughSpan|A span that strikes through the text it's attached to.|
|subscript|SubscriptSpan|The span that moves the position of the text baseline lower.|
|superscript|SuperscriptSpan|The span that moves the position of the text baseline higher.|
|tabStop|TabStopSpan|Paragraph affecting span that changes the position of the tab with respect to the leading margin of the line.|
|url|URLSpan|Implementation of the ClickableSpan that allows setting a url string.|
|alignment|AlignmentSpan|Span that allows defining the alignment of text at the paragraph level.|
|setMargin|LeadingMarginSpan|A paragraph style affecting the leading margin.|
|setTextAppearance|TextAppearanceSpan|Sets the text appearance using the given TextAppearance attributes.|
|setFont|TypefaceSpanCompat|Implementation of TypefaceSpan for lower API.|
|setTypeface|TypefaceSpan|Span that updates the typeface of the text it's attached to.|
|bullet|BulletSpan|A span which styles paragraphs as bullet points (respecting layout direction).|
|quote|QuoteSpan|A span which styles paragraphs by adding a vertical stripe at the beginning of the text (respecting layout direction).|
|onClick|ClickableSpan|Implementation of ClickableSpan that call action by pressing on part of text.|
|setSpan|Custom span||


### Example:
```
exampleText.buildClickableText {
    newLine()
    block("Plain text")
    block("StyleSpan") { textStyle = Typeface.BOLD_ITALIC }
    newLine()
    block("CustomTypefaceSpan") { setFont(this@MainActivity, R.font.nautilus_pompilius) }
    newLine()
    block("UnderlineSpan") { underlined = true }
    newLine()
    block("It's clickable") {
        textSizeDp = 24
        alignment = Layout.Alignment.ALIGN_CENTER
        textColor = Color.MAGENTA
        onClick { Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show() }
    }
```
