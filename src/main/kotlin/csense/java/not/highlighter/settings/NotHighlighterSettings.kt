package csense.java.not.highlighter.settings

import com.intellij.openapi.application.*
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.*
import csense.idea.base.uicomponents.colorFont.*
import csense.kotlin.extensions.*
import java.awt.*


@State(name = "CsenseJavaNotHighlighter", storages = [(Storage("csense_java_not_highlighter.xml"))])
data class NotHighlighterSettings(
    var isEnabled: Boolean = true,

    var highlightOperators: Boolean = true,
    var highlightVariableNames: Boolean = true,
    var highlightFunctionNames: Boolean = true,
    var highlightClassNames: Boolean = true,
    var highlightComments: Boolean = true,
    var highlightStrings: Boolean = true,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForClass: ColorFontPanelData = defaultColorFontPanelData,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForOperators: ColorFontPanelData = defaultColorFontPanelData,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForNames: ColorFontPanelData = defaultColorFontPanelData,

    @OptionTag(converter = ColorFontPanelDataConverter::class)
    var colorFontForComments: ColorFontPanelData = defaultColorFontPanelData

) : PersistentStateComponent<NotHighlighterSettings> {


    override fun loadState(state: NotHighlighterSettings) {
        this.isEnabled = state.isEnabled

        this.highlightOperators = state.highlightOperators
        this.highlightFunctionNames = state.highlightFunctionNames
        this.highlightVariableNames = state.highlightVariableNames
        this.highlightComments = state.highlightComments
        this.highlightStrings = state.highlightStrings
        this.highlightClassNames = state.highlightClassNames

        this.colorFontForOperators = state.colorFontForOperators
        this.colorFontForNames = state.colorFontForNames
        this.colorFontForComments = state.colorFontForComments

    }

    override fun getState(): NotHighlighterSettings = this

    companion object {

        private val defaultForegroundColor: Color = Color(0xBD57FF)

        private const val defaultFontType: Int = Font.PLAIN.or(Font.BOLD).or(Font.ITALIC)

        private val defaultColorFontPanelData = ColorFontPanelData(
            foregroundColor = defaultForegroundColor,
            fontType = defaultFontType
        )

        val instance: NotHighlighterSettings
            get() = ApplicationManager.getApplication().getService(type())

    }
}