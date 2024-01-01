package com.just.cse.digital_diary.two_zero_two_three.department.department_info

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.list.GenericListScreen
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Department
import kotlin.random.Random


@Composable
fun DepartmentList(
    modifier: Modifier,
    departments: List<Department>
) {
    GenericListScreen(
        modifier = modifier,
        items = departments
    ) { mod, value ->
        DepartmentCard(
            modifier = mod.padding(8.dp).fillMaxWidth(),
            fullName=value.fullName,
            shortName=value.shortName,
            showFullName= true,
        )
    }


}

@Composable
fun DepartmentCard(
    modifier: Modifier,
    fullName: String,
    shortName: String,
    showFullName: Boolean = false,
    employeeCount: Int = Random.nextInt(5, 20),
    icon: ImageVector = Icons.Outlined.People
) {
    val interactionSource = remember { MutableInteractionSource() }

    val indication = LocalIndication.current
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .animateContentSize()
            .clickable(onClick = {  })
            .indication(interactionSource,indication),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Add some padding inside the card
        ) {
            Text(
                text = if (showFullName) fullName else shortName,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier) {
                Icon(imageVector = icon, null) // Set the color of the icon to green
                Spacer(Modifier.width(4.dp))
                Text(text = "$employeeCount Teacher(s)") // Set the color of the text to blue
            }
        }
    }
}


