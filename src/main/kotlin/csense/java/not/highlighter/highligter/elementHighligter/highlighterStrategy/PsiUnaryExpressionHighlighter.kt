package csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.java.not.highlighter.highligter.*
import csense.java.not.highlighter.highligter.elementHighligter.*
import csense.java.not.highlighter.highligter.elementHighligter.settings.*
import csense.java.not.highlighter.settings.*

class PsiUnaryExpressionHighlighter(
    private val element: PsiUnaryExpression,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy,
) : ElementHighlighter {

    override fun mayHighlight(): Boolean {
        return PsiOperatorHighlighter.mayHighlight(element.operationSign, settings)
    }

    override fun highlight(holder: AnnotationHolder) {
        highlighterStrategy.highlightRange(
            range = element.operationSign.textRange,
            holder = holder,
            setting = HighlighterSettings.OperatorSettings
        )
    }
}