package common.ui.search_bar.decorator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.ui.list.AdaptiveList
import common.ui.search_bar.MySearchBar

@Composable
fun <T> SearchSection(
    items: List<T>,
    filterPredicate: (T, String) -> Boolean,
    barLeadingIcon: @Composable ()->Unit={},
    onSearch:(String)->Unit={},
    onExitRequest:()->Unit,
    searchedItemDecorator: @Composable (T, highLightedText: String) -> Unit,
) {
    val uiState = remember(items) {
        SearchDecoratorState(
            items,
            filterPredicate
        )
    }
    val active = uiState.active.collectAsState().value
        SearchSection(
            query = uiState.query.collectAsState().value,
            onQueryChanged = uiState::onQueryChanged,
            onActiveChanged = {
                if (!it)
               onExitRequest()
            },
            result = uiState.results.collectAsState().value,
            searchedItemDecorator = searchedItemDecorator,
            barLeadingIcon = barLeadingIcon,
            onSearch = onSearch
        )



}

@Composable
private fun <T> SearchSection(
    query: String,
    onQueryChanged: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    result: List<T>,
    barLeadingIcon: @Composable ()->Unit={},
    onSearch:(String)->Unit={},
    searchedItemDecorator: @Composable (T, String) -> Unit,
) {
    MySearchBar(
        query = query,
        onQueryChange = onQueryChanged,
        active = true,
        barLeadingIcon =barLeadingIcon,
        onSearch =onSearch,
        onActiveChanged = onActiveChanged,
        modifier = Modifier,
    ) {
        AdaptiveList(
            modifier = Modifier,
            items = result
        ) { item ->
            searchedItemDecorator(item, query)
        }


    }


}

