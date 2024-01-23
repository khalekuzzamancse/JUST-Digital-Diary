package com.just.cse.digital_diary.two_zero_two_three.employee_list.employee_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.model.Employee


@Composable
internal fun EmployeeCard(
    modifier: Modifier,
    employee: Employee,
    expandMode: Boolean = true,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {
        println("EmployeeCard:$expandMode:$employee")
        Column(
            modifier = modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ImageLoader(
                url = employee.profileImageLink,
                modifier = Modifier
                    .heightIn(max = 100.dp)
                    .widthIn(max = 150.dp)
                    .background(Color.Red)
                    .align(Alignment.CenterHorizontally),
            )
            if (expandMode) {
                ExpandAbleInfo(
                    employee = employee
                )
            } else {
                EmployeeName(
                    name = employee.name,
                    modifier = Modifier
                )
                EmployeeDetails(
                    employee = employee,
                    modifier = Modifier
                )
            }
            Spacer(Modifier.height(8.dp))
            Controls(
                onCallRequest=onCallRequest,
                onMessageRequest = onMessageRequest,
                onEmailRequest = onEmailRequest
            )


        }


    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ExpandAbleInfo(
    modifier: Modifier = Modifier,
    employee: Employee
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        FlowRow(
            modifier = Modifier,
//            verticalAlignment = Alignment.CenterVertically
        ) {
            //what if the name doesn't fit into one Row?,then button will be hidden
            EmployeeName(
                name = employee.name,
                modifier = Modifier
            )
            Icon(
                imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                contentDescription = null,
                modifier = Modifier.clickable {
                    expanded = !expanded
                }
            )
        }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            exit= shrinkOut() + fadeOut()
        ) {
            EmployeeDetails(
                modifier = Modifier,
                employee = employee
            )
        }

    }

}

@Composable
private fun EmployeeName(
    modifier: Modifier,
    name: String,
) {
    Text(
        modifier = modifier,
        text = name,
        style = CardTypography.title
    )

}

@Composable
private fun EmployeeDetails(
    modifier: Modifier,
    employee: Employee
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = employee.achievements,
            style = CardTypography.subTitle
        )
        Text(
            text = employee.designations,
            style = CardTypography.title2
        )
        Text(
            text = employee.email,
            style = CardTypography.contactStyle
        )
        Text(
            text = employee.additionalEmail,
            style = CardTypography.contactStyle
        )
        Text(
            text = employee.phone,
            style = CardTypography.contactStyle
        )


    }
}

