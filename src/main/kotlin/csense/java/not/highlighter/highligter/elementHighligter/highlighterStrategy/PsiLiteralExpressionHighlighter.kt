package csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.java.not.highlighter.highligter.*
import csense.java.not.highlighter.highligter.elementHighligter.*
import csense.java.not.highlighter.highligter.elementHighligter.settings.*
import csense.java.not.highlighter.settings.*

class PsiLiteralExpressionHighlighter(
    private val element: PsiLiteralExpression,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy
) : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return settings.highlightStrings
    }

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightTextIn(
            element = element,
            holder = holder,
            setting = HighlighterSettings.NameSettings
        )
    }

}