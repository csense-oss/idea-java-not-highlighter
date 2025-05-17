package csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.java.not.highlighter.highligter.*
import csense.java.not.highlighter.highligter.elementHighligter.*
import csense.java.not.highlighter.highligter.elementHighligter.settings.*
import csense.java.not.highlighter.settings.*

class PsiJavaTokenHighlighter(
    private val element: PsiJavaToken,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy,
) : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return element.tokenType == JavaTokenType.EXCL && settings.highlightOperators
    }

    override fun highlight(holder: AnnotationHolder) {
        return highlighterStrategy.highlightTextIn(
            element = element,
            holder = holder,
            setting = HighlighterSettings.OperatorSettings
        )
    }
}