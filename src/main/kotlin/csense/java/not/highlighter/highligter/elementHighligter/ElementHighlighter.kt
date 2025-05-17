package csense.java.not.highlighter.highligter.elementHighligter

import com.intellij.lang.annotation.*
import com.intellij.psi.*
import csense.java.not.highlighter.bll.*
import csense.java.not.highlighter.highligter.*
import csense.java.not.highlighter.highligter.elementHighligter.highlighterStrategy.*
import csense.java.not.highlighter.settings.*

interface ElementHighlighter {

    fun mayHighlight(): Boolean
    fun highlight(holder: AnnotationHolder)

    companion object
}


fun ElementHighlighter.mayNotHighlight(): Boolean =
    !mayHighlight()

fun ElementHighlighter.Companion.from(
    element: PsiElement,
    settings: NotHighlighterSettings,
    highlighterStrategy: AnnotationHolderHighlighterStrategy
): ElementHighlighter = when (settings.isDisabled) {
    true -> NoOpHighlighter
    false -> highlighterBy(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )
}

private fun ElementHighlighter.Companion.highlighterBy(
    element: PsiElement,
    settings: NotHighlighterSettings,
    highlighterStrategy: AnnotationHolderHighlighterStrategy
): ElementHighlighter = when (element) {

    is PsiMethod -> PsiMethodHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiComment -> PsiCommentHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiClass -> PsiClassHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )


    is PsiVariable -> PsiVariableHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiTypeElement -> PsiTypeElementHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiReferenceExpression -> PsiReferenceExpressionHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiLiteralExpression -> PsiLiteralExpressionHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    is PsiJavaToken -> PsiJavaTokenHighlighter(
        element = element,
        settings = settings,
        highlighterStrategy = highlighterStrategy
    )

    else -> NoOpHighlighter
}