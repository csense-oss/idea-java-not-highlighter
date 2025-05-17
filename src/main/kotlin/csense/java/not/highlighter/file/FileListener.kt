package csense.java.not.highlighter.file

import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.newvfs.*
import com.intellij.openapi.vfs.newvfs.events.*
import csense.java.not.highlighter.repo.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.primitives.*

class FileContentListener(
    val project: Project
) : BulkFileListener {

    val nameEndingsToWatchFor: List<String> by lazy {
        listOf(
            NamesHighlighterRepo.namesFileName
        )
    }

    override fun after(events: MutableList<out VFileEvent>) {
        super.after(events)
        val anyChanged: Boolean = events.any { it: VFileEvent ->
            it.path.endsWithAny(nameEndingsToWatchFor)
        }
        if (!anyChanged) {
            return
        }
        tryAndLog {
            NamesHighlighterRepo.from(project).reload()
        }
    }
}