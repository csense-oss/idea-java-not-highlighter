package csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.java.not.highlighter.highligter.*
import csense.java.not.highlighter.highligter.elementHighligter.*
import csense.java.not.highlighter.highligter.elementHighligter.settings.*
import csense.java.not.highlighter.settings.*

class PsiReferenceExpressionHighlighter(
    private val element: PsiJavaCodeReferenceElement,
    private val settings: NotHighlighterSettings,
    private val highlighterStrategy: AnnotationHolderHighlighterStrategy
) : ElementHighlighter {

    private val resolved: PsiElement? by lazy { element.resolve() }
    private val isClass: Boolean by lazy { resolved is PsiClass }
    private val isMethod: Boolean by lazy { resolved is PsiMethod }
    private val isVariable: Boolean by lazy { resolved is PsiVariable }
    override fun mayHighlight(): Boolean = when {
        isVariable -> settings.highlightVariableNames
        isClass -> settings.highlightClassNames
        isMethod -> settings.highlightFunctionNames
        else -> false
    }

    override fun highlight(holder: AnnotationHolder): Unit = when {
        isVariable -> highlightVariable(holder)
        isMethod -> highlightMethod(holder)
        isClass -> highlightClass(holder)
        else -> Unit
    }

    private fun highlightVariable(holder: AnnotationHolder) {
        highlightByNameSettings(holder)
    }

    private fun highlightMethod(holder: AnnotationHolder) {
        highlightByNameSettings(holder)
    }

    private fun highlightByNameSettings(holder: AnnotationHolder) {
        highlighterStrategy.highlightTextIn(
            element = element.referenceNameElement ?: return,
            holder = holder,
            setting = HighlighterSettings.NameSettings
        )
    }

    private fun highlightClass(holder: AnnotationHolder) {
        highlighterStrategy.highlightTextIn(
            element = element.referenceNameElement ?: return,
            holder = holder,
            setting = HighlighterSettings.ClassSettings
        )
    }
}