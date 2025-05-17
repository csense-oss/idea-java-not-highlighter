package csense.java.not.highlighter

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.java.not.highlighter.bll.*
import csense.java.not.highlighter.highligter.*
import csense.java.not.highlighter.highligter.elementHighligter.*
import csense.java.not.highlighter.settings.*

class NotNameAnnotator : Annotator {

    private val settings: NotHighlighterSettings by lazy {
        NotHighlighterSettings.instance
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val highlighter: ElementHighlighter = getHighlighterFrom(element = element)
        if (highlighter.mayNotHighlight()) {
            return
        }
        highlighter.highlight(holder)
    }

    private fun getHighlighterFrom(element: PsiElement): ElementHighlighter {
        val highlighterStrategy: AnnotationHolderHighlighterStrategy = settings.toHighlighterStrategy(element.project)
        return ElementHighlighter.from(
            element = element,
            settings = settings,
            highlighterStrategy = highlighterStrategy
        )
    }
}