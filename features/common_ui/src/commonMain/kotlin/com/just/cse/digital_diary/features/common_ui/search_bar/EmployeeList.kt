package com.just.cse.digital_diary.features.common_ui.search_bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.network_image.ImageLoader
import com.just.cse.digital_diary.features.common_ui.list.GenericListScreen
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.employees

@Composable
fun EmployeeListDemo() {
    EmployeeList(
        employees = employees
    )
}


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */
@Composable
fun EmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    highlightedText: String,
) {
    GenericListScreen(
        modifier = modifier,
        items = employees
    ) { mod, teacher ->
        EmployeeCard(
            modifier = mod.padding(8.dp),
            employee = teacher,
            highlightedText = highlightedText

        )
    }
}

@Composable
fun EmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>
) {
    GenericListScreen(
        modifier = modifier,
        items = employees
    ) { mod, teacher ->
        EmployeeCard(
            modifier = mod.padding(8.dp),
            employee = teacher
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmployeeCard(
    modifier: Modifier,
    employee: Employee
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ImageLoader(
                url = "http://tinyurl.com/4m2eayaj",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
            )

            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = employee.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = employee.phone,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                        contentDescription = null,
                        modifier= Modifier.clickable {
                            expanded = !expanded
                        }
                    )

                }
                if (expanded) {
                    ContentAnimationDecorator(
                        state = expanded
                    ) {
                        EmployeeCardExpandAblePart(employee)
                    }
                }

            }


        }

    }

}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EmployeeCard(
    modifier: Modifier,
    highlightedText: String,
    employee: Employee
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            ImageLoader(
                url = "http://tinyurl.com/4m2eayaj",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
            )
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = getHighLightedString(employee.name,highlightedText),
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = getHighLightedString(employee.phone,highlightedText),
                        style = MaterialTheme.typography.bodySmall,
                    )


                }

                EmployeeCardExpandAblePart(employee,highlightedText)
            }


        }

    }

}

@Composable
fun ContentAnimationDecorator(
    state: Boolean,
    content: @Composable () -> Unit,
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn(animationSpec = tween(150, 150)) togetherWith
                    fadeOut(animationSpec = tween(150)) using
                    SizeTransform { initialSize, targetSize ->
                        if (targetState) {
                            keyframes {
                                IntSize(targetSize.width, initialSize.height) at 150
                                durationMillis = 300
                            }
                        } else {
                            keyframes {
                                IntSize(initialSize.width, targetSize.height) at 150
                                durationMillis = 300
                            }
                        }
                    }
        }) {
        content()
    }

}

@Composable
fun EmployeeCardExpandAblePart(
    employee: Employee
) {

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = employee.designations,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = "${employee.deptSortName} - ${employee.roomName}",
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = employee.email,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = employee.additionalEmail,
            style = MaterialTheme.typography.bodySmall,
        )


        Text(
            text = employee.achievements,
            style = MaterialTheme.typography.bodySmall,
        )
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )

            }
        }

    }

}
@Composable
fun EmployeeCardExpandAblePart(
    employee: Employee,
    highlightedText: String,
) {

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = getHighLightedString(employee.designations,highlightedText),
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = getHighLightedString("${employee.deptSortName} - ${employee.roomName}",highlightedText),
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = getHighLightedString(employee.email,highlightedText),
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = getHighLightedString(employee.additionalEmail,highlightedText),
            style = MaterialTheme.typography.bodySmall,
        )


        Text(
            text = getHighLightedString(employee.achievements,highlightedText),
            style = MaterialTheme.typography.bodySmall,
        )
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )

            }
        }

    }

}


fun generateDummyEmployeeList(numberOfEmployees: Int): List<Employee> {
    val employeeNames = listOf("John", "Jane", "Bob", "Alice", "Eva", "David", "Emily", "Michael", "Olivia", "Daniel")
    val departmentNames = listOf("IT", "HR", "Marketing", "Finance", "Engineering", "Sales")

    return List(numberOfEmployees) {
        Employee(
            name = employeeNames.random(),
            email = "example${it + 1}@company.com",
            additionalEmail = "additional${it + 1}@company.com",
            profileImageLink = "https://example.com/image${it + 1}.png",
            achievements = "Achievements for Employee $it",
            phone = "+1 555-1234-${String.format("%04d", it)}",
            designations = "Designation $it",
            deptName = departmentNames.random(),
            deptSortName = "DeptSort$it",
            roomName = "Room$it"
        )
    }
}
