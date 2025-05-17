package csense.java.not.highlighter.highligter.elementHighligter.settings

import com.intellij.openapi.editor.markup.*
import csense.idea.base.uicomponents.colorFont.*
import csense.java.not.highlighter.settings.*

object HighlighterSettings {
    val NameSettings: HighlighterSetting = HighlighterSettingName()
    val OperatorSettings: HighlighterSetting = HighlighterSettingOperators()
    val CommentSettings: HighlighterSetting = HighlighterSettingComments()
    val ClassSettings: HighlighterSetting = HighlighterSettingClass()
}


interface HighlighterSetting {
    fun textAttributes(): TextAttributes
}

class HighlighterSettingName : HighlighterSetting {
    override fun textAttributes(): TextAttributes {
        return NotHighlighterSettings
            .instance
            .colorFontForNames
            .toTextAttributes()
    }


}

class HighlighterSettingOperators : HighlighterSetting {
    override fun textAttributes(): TextAttributes {
        return NotHighlighterSettings
            .instance
            .colorFontForOperators
            .toTextAttributes()
    }
}

class HighlighterSettingComments : HighlighterSetting {
    override fun textAttributes(): TextAttributes {
        return NotHighlighterSettings
            .instance
            .colorFontForComments
            .toTextAttributes()
    }
}

class HighlighterSettingClass : HighlighterSetting {
    override fun textAttributes(): TextAttributes {
        return NotHighlighterSettings
            .instance
            .colorFontForClass
            .toTextAttributes()
    }
}