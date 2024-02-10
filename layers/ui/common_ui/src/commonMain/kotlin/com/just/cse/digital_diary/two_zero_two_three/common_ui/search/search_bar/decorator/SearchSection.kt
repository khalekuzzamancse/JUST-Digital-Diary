package com.just.cse.digital_diary.two_zero_two_three.common_ui.search.search_bar.decorator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.search.search_bar.MySearchBar

@Composable
fun <T> SearchSection(
    items: List<T>,
    filterPredicate: (T, String) -> Boolean,
    barLeadingIcon: @Composable ()->Unit={},
    onSearch:(String)->Unit={},
    searchedItemDecorator: @Composable (T, highLightedText: String) -> Unit,
) {
    val uiState = remember(items) {
        SearchDecoratorState(
            items,
            filterPredicate
        )
    }
    val active = uiState.active.collectAsState().value
    AnimatedVisibility(
        visible = active
    ) {
        SearchSection(
            query = uiState.query.collectAsState().value,
            onQueryChanged = uiState::onQueryChanged,
            active = active,
            onActiveChanged = uiState::onActiveChanged,
            result = uiState.results.collectAsState().value,
            searchedItemDecorator = searchedItemDecorator,
            barLeadingIcon = barLeadingIcon,
            onSearch = onSearch
        )

    }


}

@Composable
private fun <T> SearchSection(
    query: String,
    onQueryChanged: (String) -> Unit,
    active: Boolean,
    onActiveChanged: (Boolean) -> Unit,
    result: List<T>,
    barLeadingIcon: @Composable ()->Unit={},
    onSearch:(String)->Unit={},
    searchedItemDecorator: @Composable (T, String) -> Unit,
) {
    MySearchBar(
        query = query,
        onQueryChange = onQueryChanged,
        active = active,
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

