package notebook.ui.notedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.ui.animation.TypeWriter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * * Show the details of a a selected note.
 * * It is it is internal to the module can outer module can not directly access it.
 * * It this can handle both compact,medium,expanded window size.
 * * It take the [Note] and break it down to primitive and delegate to the [Note]
 * * So this is coupled with the [Note]
 * @param note that details is going to show
 */
@Composable
fun DetailsOfNote(
    modifier: Modifier=Modifier,
    note: Note,
) {
    NoteDetails(
        modifier = modifier,
        title = note.title,
        description = note.description,
        timeStamp = note.timeStamp,

    )

}


/**
 * * Show the details of a a selected note.
 * * It is it is internal to the module can outer module can not directly access it.
 * * It this can handle both compact,medium,expanded window size.
 * * It take the notes details as primitive and display the details
 * * It is not coupled with any custom data types that is why it is easy to test and change
 * @param modifier
 * @param  title [String]
 * @param description[String]
 * @param timeStamp[String]

 */
@Composable
internal fun NoteDetails(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    timeStamp: String,

) {
    var showCreatorInfo by remember { mutableStateOf(false) }
    var showDivider by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        Note(
            title = title,
            description = description,
            onFinish = {
                scope.launch {
                    showDivider = true
                    delay(1000)
                    showCreatorInfo = true
                }

            }
        )
        Spacer(Modifier.height(4.dp))
        AnimatedVisibility(
            visible = showDivider
        ) {
            HorizontalDivider()
            Spacer(Modifier.height(4.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AnimatedVisibility(
                visible = showCreatorInfo,
                modifier = Modifier.align(Alignment.End)
            ) {
                CreationDetails(
                    timeStamp = timeStamp,
                )
            }
        }


    }

}

@Composable
private fun Note(
    title: String,
    description: String,
    onFinish: () -> Unit = {},
) {
    val charDelay = remember { 5L }
    LaunchedEffect(Unit) {
        val n = description.length * charDelay * 1L
        val delay = n * 2
        delay(delay)
        onFinish()
    }
    Column {
        TypeWriter(
            text = title,
            delay = 10
        ) {
            Text(
                text = it,
                style = NoteTextStyle.title
            )
        }

        Spacer(Modifier.height(8.dp))
        TypeWriter(
            text = description,
            delay = charDelay
        ) {
            Text(
                text = it,
                style = NoteTextStyle.description,
                textAlign = TextAlign.Justify
            )
        }

    }

}

@Composable
private fun CreationDetails(
    timeStamp: String,
) {
    Column(
        modifier = Modifier.wrapContentSize()
    ) {

        Text(text = timeStamp, style = NoteTextStyle.creatorName)

    }

}