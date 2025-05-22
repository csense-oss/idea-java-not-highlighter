package csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.psi.*
import com.intellij.psi.tree.*
import csense.java.not.highlighter.settings.*

object PsiOperatorHighlighter {

    fun mayHighlight(operationSign: PsiJavaToken, settings: NotHighlighterSettings): Boolean {
        return operationSign.tokenType in tokensToHighlight && settings.highlightOperators
    }

    val tokensToHighlight: Set<IElementType> = setOf(JavaTokenType.EXCL, JavaTokenType.NE)

}