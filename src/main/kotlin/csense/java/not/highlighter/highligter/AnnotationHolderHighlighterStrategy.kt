package csense.java.not.highlighter.highligter

import com.intellij.lang.annotation.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import csense.idea.base.bll.*
import csense.idea.base.bll.string.*
import csense.java.not.highlighter.bll.*
import csense.java.not.highlighter.highligter.elementHighligter.settings.*

class AnnotationHolderHighlighterStrategy(
    private val textHighlightDecider: TextHighlightDecider
) {
    fun highlightNameIdentifier(
        element: PsiClass,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        highlightTextIn(
            element = element.nameIdentifier ?: return,
            holder = holder,
            setting = setting
        )
    }

    fun highlightNameIdentifier(
        element: PsiVariable,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        highlightTextIn(
            element = element.nameIdentifier ?: return,
            holder = holder,
            setting = setting
        )
    }

    fun highlightNameIdentifier(
        element: PsiMethod,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        highlightTextIn(
            element = element.nameIdentifier ?: return,
            holder = holder,
            setting = setting
        )
    }

    fun highlightTextIn(
        element: PsiElement,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        element.camelCase.forEachCamelCaseWordWithTextRange { originalRange: TextRange, string: String ->
            if (textHighlightDecider.shouldNotHighlight(string)) {
                return@forEachCamelCaseWordWithTextRange
            }

            val rangeToHighlight: TextRange = textHighlightDecider.getRangeToHighligt(
                text = string,
                originalTextRange = originalRange
            )

            highlightRange(
                range = rangeToHighlight,
                holder = holder,
                setting = setting
            )
        }
    }

    fun highlightRange(
        range: TextRange,
        holder: AnnotationHolder,
        setting: HighlighterSetting
    ) {
        holder.highlightTextRange(
            range = range,
            withStyle = setting.textAttributes()
        )
    }
}