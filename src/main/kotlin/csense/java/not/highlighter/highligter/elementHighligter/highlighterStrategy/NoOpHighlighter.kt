package csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import csense.java.not.highlighter.bll.*
import csense.java.not.highlighter.highligter.elementHighligter.*

object NoOpHighlighter : ElementHighlighter {
    override fun mayHighlight(): Boolean {
        return false
    }

    override fun highlight(holder: AnnotationHolder) {
        noOp()
    }
}