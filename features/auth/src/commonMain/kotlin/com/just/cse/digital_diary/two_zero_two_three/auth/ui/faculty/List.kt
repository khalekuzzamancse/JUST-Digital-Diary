package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.unit.dp

/*
1:Types of faculty ->List
2:Faculty   ->List of dept
3:Members ->List of  members
4:A Member details
4 th layer can be made optional by showing details in a pop up
So we need a Common List.
In Expanded:
NavRail=Faculty Types,
Pane 1=Department
Pane 2=Teacher


2 Panes(except the nav rails),
one pans show list of item and other is details
because min width of expanded=800dp,
details on dialog or separate screen

In Medium:
NavRail=Faculty Types
Faculty types:Nav Rails:
Dept in a List

Nav rails+GridView for list items
details on dialog or separate screen

In Compact:
Drawer+ListView
details on dialog or separate screen

 */


