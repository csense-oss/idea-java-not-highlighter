package csense.java.not.highlighter.repo

import com.intellij.openapi.project.*
import csense.idea.base.files.*
import csense.java.not.highlighter.settings.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.collections.*
import java.nio.file.*
import java.util.*

class NamesHighlighterRepo private constructor(
    private val storage: CachedFileInMemory<List<String>>?,
) {

    private val builtInNotTexts: List<String> by lazy {
        val builtIn: List<String> = tryAndLog {
            ResourceBundle.getBundle("texts.builtin-not-names").keys.toList()
        } ?: emptyList()
        return@lazy listOf("!") + builtIn
    }

    private val builtInNegativeNames: List<String> by lazy {
        tryAndLog {
            ResourceBundle.getBundle("texts.builtin-negative-names").keys.toList()
        } ?: emptyList()
    }

    fun reload() {
        tryAndLog {
            storage?.reload()
        }
    }

    fun getNames(settings: NotHighlighterSettings): List<String> {
        return builtInNotTexts + builtInNegativeNames + storage?.getCurrentValue().orEmpty()
    }

    fun save() {
        tryAndLog {
            storage?.save()
        }
    }

    companion object {
        val namesFileName = ".namesToHighlight"
        private val cachedProjectToRepo: MutableMap<Project, NamesHighlighterRepo> = mutableMapOf()

        fun from(project: Project): NamesHighlighterRepo {
            return cachedProjectToRepo.getOrPut(key = project, defaultValue = {
                NamesHighlighterRepo(fileCache(project))
            })
        }

        private fun fileCache(
            project: Project
        ): CachedFileInMemory<List<String>>? {
            val rootPath: String = project.basePath ?: return null
            val file: Path = Paths.get(rootPath, namesFileName)

            return CachedFileInMemory(initial = listOf(),
                filePath = file,
                serialization = { it.joinToStringNewLine() },
                deserialization = { it.lines() })
        }

        fun allOpened(action: (repo: NamesHighlighterRepo) -> Unit) {
            cachedProjectToRepo.values.forEach { repo: NamesHighlighterRepo ->
                action(repo)
            }
        }
    }

}