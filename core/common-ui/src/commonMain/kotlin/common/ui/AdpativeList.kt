package common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import common.newui.EmptyContentScreen

@Composable
fun <T> AdaptiveList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues=PaddingValues(16.dp),
    verticalItemSpacing: Dp =24.dp,
    horizontalArrangement: Arrangement.Horizontal=Arrangement.spacedBy(16.dp),
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
) {
    val state = rememberLazyGridState()

        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Adaptive(300.dp),
            contentPadding = contentPadding,
            horizontalArrangement = horizontalArrangement,
            state = state,
        ){
            items(
                items =items,
                itemContent = {item->
                    itemContent(item)
                }
            )
        }



}
