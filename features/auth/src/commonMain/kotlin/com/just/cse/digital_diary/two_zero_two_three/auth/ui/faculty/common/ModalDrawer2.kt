package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.GroupSection
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerTheme


/*
Passing clicked group,
passing clicked item.
do don't need to pass group item group index when passing clicked item index
because parent already know which group is selected/clicked.
But what if the case,if all group item are shown at once,in that case we need to pass
group index and item index when passing which item is clicked
 */
data class NavGroupSelectedItem(
    val groupIndex: Int = -1,
    val itemIndex: Int = -1
)

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    name: String,
    department: String,

) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Blue, Color.Magenta),
        start = Offset(0f, 0f),
        end = Offset(300f, 300f)
    )

   Surface (
       modifier=Modifier.padding(bottom = 4.dp),
       shadowElevation = 8.dp
   ){
       Box(modifier = modifier.background(brush = gradientBrush)) {
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp),
           ) {
               Box(
                   modifier = Modifier
                       .size(64.dp)
                       .clip(CircleShape)
               )
               Spacer(modifier = Modifier.width(16.dp))

               Column {
                   Text(
                       text = name,
                       style = MaterialTheme.typography.headlineSmall,
                       color = Color.White
                   )
                   Text(
                       text = department,
                       style = MaterialTheme.typography.labelMedium,
                       color = Color.White
                   )
               }
           }
       }
   }
}


@Composable
fun HomeScreenDrawerSheet(
    selectedItemIndex: NavGroupSelectedItem = NavGroupSelectedItem(),//group index,item index
    groups: List<ModalDrawerGroup>,
    onGroupClicked: (Int) -> Unit,
    onItemClicked: (groupIndex: Int, index: Int) -> Unit
) {

        DrawerSheet2(
            header = {
                    DrawerHeader(
                        name="Md Abul Kalam",
                        department = "CSE"
                    )
            },
            footer = null,
            groups = groups,
            groupDecorator = { groupIndex ->
                groups.getOrNull(groupIndex)?.let { group ->
                    GroupSection(
                        groupName = group.name,
                        onClick = { onGroupClicked(groupIndex) }
                    )
                }
            },
            itemDecorator = { groupIndex, itemIndex ->
                val isSelected= selectedItemIndex== NavGroupSelectedItem(
                    groupIndex = groupIndex,
                    itemIndex = itemIndex
                )

                groups.getOrNull(groupIndex)?.let { group ->
                    group.members.getOrNull(itemIndex)?.let { navigationItem ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            icon = {
                                Icon(
                                    navigationItem.unFocusedIcon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    text = navigationItem.label,


                                    )
                            },
                            selected = isSelected ,
                            onClick = {
                                onItemClicked(groupIndex, itemIndex)
                            },
                        )

                    }
                }
            })



}
@Composable
fun DrawerSheet2(
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    groups: List<ModalDrawerGroup>,
    groupDecorator: @Composable (Int) -> Unit,
    itemDecorator: @Composable (groupIndex: Int, index: Int) -> Unit,
) {
        ModalDrawerSheet(
            modifier = Modifier,
        ) {
            LazyColumn(
                modifier = Modifier,
            ) {
                if (header != null) {
                    item {
                        header()
                    }
                }
                groups.forEachIndexed { groupIndex, group ->
                    item {
                        groupDecorator(groupIndex)
                    }
                    itemsIndexed(group.members) { index, _ ->
                        itemDecorator(groupIndex, index)
                    }
                }
                if (footer != null) {
                    item {
                        footer()
                    }
                }
            }

        }

}


@Composable
fun DrawerSheet(
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    groups: List<ModalDrawerGroup>,
    groupDecorator: @Composable (Int) -> Unit,
    itemDecorator: @Composable (groupIndex: Int, index: Int) -> Unit,
) {
    ModalDrawerSheet(
        modifier = Modifier,
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        drawerTonalElevation = 1.dp
    ) {
        LazyColumn(
            modifier = Modifier,
        ) {
            if (header != null) {
                item {
                    header()
                }
            }
            groups.forEachIndexed { groupIndex, group ->
                item {
                    groupDecorator(groupIndex)
                }
                itemsIndexed(group.members) { index, _ ->
                    itemDecorator(groupIndex, index)
                }
            }
            if (footer != null) {
                item {
                    footer()
                }
            }
        }

    }

}

@Composable
fun ModalDrawer(
    modifier: Modifier,
    drawerState: DrawerState,
    sheet: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = sheet,
        content = content,
    )
}