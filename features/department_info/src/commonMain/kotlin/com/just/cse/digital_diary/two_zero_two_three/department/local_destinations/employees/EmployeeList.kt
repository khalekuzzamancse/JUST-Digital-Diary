package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.employees

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */


@Composable
internal fun AdaptiveEmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>
) {
    AdaptiveList(
        modifier = modifier,
        items = employees
    ) { teacher ->
        EmployeeCard(
            modifier = Modifier.padding(8.dp),
            employee = teacher
        )
    }
}

@Composable
fun EmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    title: String? = null,
    enableBackNavigation: Boolean = true,
    onDismissRequest: () -> Unit = {},
) {
    Surface(
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
                AnimatedVisibility(enableBackNavigation) {
                    IconButton(
                        onClick = onDismissRequest
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )

                    }
                }
                if (title != null) {
                    Box(Modifier) {
                        Text(text = title)
                    }
                }

            }
            AdaptiveEmployeeList(
                modifier = Modifier.wrapContentWidth(),
                employees = employees
            )


        }
    }


}

