package com.just.cse.digital_diary.features.common_ui.search_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.list.GenericListScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


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

    private val _showSearch = MutableStateFlow(false)
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

@Composable
fun <T> SearchSection(
    items: List<T>,
    predicate: (T, String) -> Boolean,
    onSearchExitRequest:()->Unit={},
    searchedItemDecorator: @Composable (T, highLightedText: String) -> Unit,
) {
    val uiState = remember {
        SearchDecoratorState(
            items,
            predicate
        )
    }
    SearchSection(
        query = uiState.query.collectAsState().value,
        onQueryChanged = uiState::onQueryChanged,
        active = uiState.active.collectAsState().value,
        onActiveChanged = uiState::onActiveChanged,
        onSearchExitRequest = {
            uiState.onSearchChanged(false)
            onSearchExitRequest()
        },
        result = uiState.results.collectAsState().value,
        searchedItemDecorator = searchedItemDecorator,
    )
}

@Composable
fun <T> SearchSection(
    query: String,
    onQueryChanged: (String) -> Unit,
    active: Boolean,
    onSearchExitRequest:()->Unit={},
    onActiveChanged: (Boolean) -> Unit,
    result: List<T>,
    searchedItemDecorator: @Composable (T, String) -> Unit,
) {
    MySearchBar(
        query = query,
        onQueryChange = onQueryChanged,
        active = active,
        onActiveChanged = onActiveChanged,
        modifier = Modifier,
        onGoBack = onSearchExitRequest
    ) {
        GenericListScreen(
            modifier = Modifier,
            items = result,
            itemDecorator = { _, item ->
                searchedItemDecorator(item, query)
            }
        )


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SearchSection(
    showSearch: Boolean,
    onShowSearchChanged: (Boolean) -> Unit,
    query: String,
    onQueryChanged: (String) -> Unit,
    active: Boolean,
    onActiveChanged: (Boolean) -> Unit,
    result: List<T>,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    itemDecorator: @Composable (T, String) -> Unit,
    bottomNavbar: @Composable () -> Unit = {},
    contentOnNoSearch: @Composable (Modifier) -> Unit,
) {

    Box {
        if (!showSearch) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            if (navigationIcon != null) {
                                IconButton(
                                    onClick = {
                                        onNavigationClick?.invoke()
                                    }) {
                                    Icon(
                                        imageVector = navigationIcon,
                                        contentDescription = null
                                    )
                                }
                            }

                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    onShowSearchChanged(true)
                                    onActiveChanged(true)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
                        )
                    )

                },
                bottomBar = bottomNavbar
            ) {
                contentOnNoSearch(Modifier.padding(it))
            }
        } else {
            MySearchBar(
                query = query,
                onQueryChange = onQueryChanged,
                active = active,
                onActiveChanged = onActiveChanged,
                modifier = Modifier,
                onGoBack = {
                    onShowSearchChanged(false)
                },

                ) {
                GenericListScreen(
                    modifier = Modifier,
                    items = result,
                    itemDecorator = { _, item ->
                        itemDecorator(item, query)
                    }
                )
            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SearchDecoratorBottomSheet(
    showSearch: Boolean,
    onShowSearchChanged: (Boolean) -> Unit,
    query: String,
    onQueryChanged: (String) -> Unit,
    active: Boolean,
    onActiveChanged: (Boolean) -> Unit,
    result: List<T>,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    itemDecorator: @Composable (T, String) -> Unit,
    sheetContent: @Composable () -> Unit = {},
    contentOnNoSearch: @Composable (Modifier) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    Box {
        if (!showSearch) {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            if (navigationIcon != null) {
                                IconButton(
                                    onClick = {
                                        onNavigationClick?.invoke()
                                    }) {
                                    Icon(
                                        imageVector = navigationIcon,
                                        contentDescription = null
                                    )
                                }
                            }

                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    onShowSearchChanged(true)
                                    onActiveChanged(true)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
                        )
                    )

                },
                sheetContent = {
                    sheetContent()

                },
                sheetSwipeEnabled = true
            ) {
                contentOnNoSearch(Modifier.padding(it))
            }
        } else {
            MySearchBar(
                query = query,
                onQueryChange = onQueryChanged,
                active = active,
                onActiveChanged = onActiveChanged,
                modifier = Modifier,
                onGoBack = {
                    onShowSearchChanged(false)
                },

                ) {
                GenericListScreen(
                    modifier = Modifier,
                    items = result,
                    itemDecorator = { _, item ->
                        itemDecorator(item, query)
                    }
                )
            }
        }

    }


}
