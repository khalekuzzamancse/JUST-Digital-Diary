package com.just.cse.digital_diary.two_zero_two_three.common_ui.search.search_bar

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit={},
    active: Boolean,
    onActiveChanged: (Boolean)-> Unit,
    barLeadingIcon: @Composable ()->Unit={},
    onSearch:(String)->Unit={},
    content:@Composable ColumnScope.()->Unit={},
) {
    SearchBar(
        modifier = modifier.fillMaxWidth(),
        query = query,
        enabled = true,
        onQueryChange =onQueryChange,
        onSearch = onSearch,
        active = active,
        onActiveChange = onActiveChanged,
        placeholder = {
            Text(text = "Type here  to to search")
        },
        shape = RectangleShape,
        leadingIcon = barLeadingIcon, trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(
                    onClick = {
                        onQueryChange("")
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.Clear,
                        null
                    )
                }
            }

        },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit={},
    query: String,
    onQueryChange: (String) -> Unit={},
    active: Boolean,
    onActiveChanged: (Boolean)-> Unit,
    content:@Composable ColumnScope.()->Unit={},
) {

    SearchBar(
        modifier = modifier.fillMaxWidth(),
        query = query,
        onQueryChange =onQueryChange,
        onSearch = {
           // onActiveChanged(false)
        },
        active = active,
        onActiveChange = onActiveChanged,
        placeholder = {
            Text(text = "Type here  to to search")
        },
        shape = RectangleShape,
        leadingIcon = {
           IconButton(
                onClick = {
                   // onActiveChanged(false) #disabling the exits
                    onGoBack()
                }
            ){
               Icon(
                   imageVector = Icons.Default.ArrowBack,
                   null
               )
           }

        }, trailingIcon = {
            if (query.isNotBlank()) {
               IconButton(
                    onClick = {
                       onQueryChange("")
                    }
                ){
                   Icon(
                       imageVector = Icons.Default.Clear,
                       null
                   )
               }
            }

        },
        content = content
    )
}
