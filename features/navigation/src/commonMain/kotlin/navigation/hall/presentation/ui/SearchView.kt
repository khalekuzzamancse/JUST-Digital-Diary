package navigation.hall.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import common.ui.EmptyContentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * - SearchBar , used Strategy Design pattern so need to fill some slot in order to use
 * - It is reusable across different project that is why putting all component in a single file so that easily can copy-paste to other project
 * - Usage Example:
 *
 * ```kotlin
 * SearchView(
 *     onExitRequest = {},
 *     barLeadingIcon = {},
 *     items = listOf(
 *         Employee("Mr John", "CSE Student"),
 *         Employee("Mr Bean", "EEE Teacher")
 *     ),
 *     filterPredicate = { employee, queryText ->
 *         val filter = employee.name.contains(queryText, ignoreCase = true) ||
 *                      employee.details.contains(queryText, ignoreCase = true)
 *         filter
 *     },
 *     searchedItemDecorator = { employee, queryText ->
 *         Column {
 *             Text(SearcherHighlightedText().getHighLightedString(employee.name, queryText))
 *             Text(SearcherHighlightedText().getHighLightedString(employee.details, queryText))
 *         }
 *     }
 * )
 * ```
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SearchView(
    items: List<T>,
    filterPredicate: (T, String) -> Boolean,
    background:Color= MaterialTheme.colorScheme.background,
    barLeadingIcon: @Composable () -> Unit = {
        DefaultLeadingIcon(
            color = if (background.luminance() > 0.5f) Color.Black else Color.White
        )
    },
    onSearch: (String) -> Unit = {},
    onExitRequest: () -> Unit,
    content: @Composable (result: List<T>, highLightedText: String) -> Unit,
) {
    val uiState = remember(items) {
        SearchDecoratorState(
            items,
            filterPredicate
        )
    }

    val contentColor = remember {
        if (background.luminance() > 0.5f) Color.Black else Color.White
    }
    val active = uiState.active.collectAsState().value
    val query = uiState.query.collectAsState().value
    val result = uiState.results.collectAsState().value

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        colors = SearchBarDefaults.colors(
            containerColor =background,
            inputFieldColors = TextFieldDefaults.colors().copy(
                focusedTextColor = contentColor,
                unfocusedTextColor = contentColor,
                focusedIndicatorColor = contentColor,
                unfocusedContainerColor = contentColor
            )
        ),
        query = query,
        enabled = true,
        onQueryChange = uiState::onQueryChanged,
        onSearch = onSearch,
        active = true,
        onActiveChange = {
            if (!it)
                onExitRequest()
        },
        placeholder = {
            Text(text = "Type here  to to search", color = contentColor)
        },
        shape = RectangleShape,
        leadingIcon = barLeadingIcon,
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(
                    onClick = {
                        uiState.onQueryChanged("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = "clear",
                        tint = contentColor
                    )
                }
            }

        },
        content = {
            if (result.isEmpty())
                EmptyContentScreen(message = "Not Found")
            else
                content(result, query)
        }
    )



}

@Composable
fun DefaultLeadingIcon(
    modifier: Modifier = Modifier,
    color: Color=Color.Unspecified
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Outlined.Search,
        contentDescription = "Search Icon",
        tint = color
    )

}
class SearchDecoratorState<T>(
    private val items: List<T>,
    val predicate: (T, String) -> Boolean,
) {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()
    private val _results = MutableStateFlow(items)
    val results = _results.asStateFlow()
    fun onQueryChanged(query: String) {
        _query.update { query }
        _results.value = results.value.filter { predicate(it, query) }
        if (query == "") {
            _results.value = items
        }
    }

    private val _active = MutableStateFlow(true)
    val active = _active.asStateFlow()
    fun onActiveChanged(status: Boolean) {
        _active.value = status

    }

    private val _showSearch = MutableStateFlow(true)
    fun onSearchChanged(status: Boolean) {
        _showSearch.value = status
        if (!status) {
            clear()
        }
    }

    private fun clear() {
        _results.value = items
        _query.value = ""

    }

}


/**
 * - Used to highlight text
 * - Helpful for finding searched text
 * - Can be used for other purpose to highlighting text
 * - Example uses
 * ```kotlin
 *    Text(SearcherHighlightedText().getHighLightedString(wholeText, queryText))
 * ```
 */

class SearcherHighlightedText {
    fun getHighLightedString(text: String, highLightedText: String): AnnotatedString {
        val annotatedEmailString: AnnotatedString = buildAnnotatedString {
            append(text)
            val urls = findText(text, highLightedText)
            urls.forEach { pair ->
                addStyle(
                    style = SpanStyle(
                        background = Color.Yellow.copy(alpha = 0.4f),//light yellow for this app
                        textDecoration = TextDecoration.None
                    ),
                    start = pair.first,
                    end = pair.second + 1
                )
            }
        }
        return annotatedEmailString
    }

    private fun findText(text: String, key: String): List<Pair<Int, Int>> {

        val originalText = text.lowercase()
        val searchText = key.lowercase().toRegex()
        val indices = mutableListOf<Pair<Int, Int>>()

        searchText.findAll(originalText).forEach { matchResult ->
            val startIndex = matchResult.range.first
            val endIndex = matchResult.range.last
            indices.add(startIndex to endIndex)
        }
        return indices
    }
}