package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Employee(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptName: String,
    val deptSortName: String,
    val roomName: String,
)


    val employees = listOf(
        Employee(
            name = "Alice Smith",
            email = "alice@khulna.edu",
            additionalEmail = "alice.smith@gmail.com",
            profileImageLink = "https://i.imgur.com/1.jpg",
            achievements = "Best Teacher Award 2022",
            phone = "+880123456789",
            designations = "Assistant Professor",
            deptName = "Computer Science",
            deptSortName = "CS",
            roomName = "A-101"
        ),
        Employee(
            name = "Bob Jones",
            email = "bob@khulna.edu",
            additionalEmail = "bob.jones@yahoo.com",
            profileImageLink = "https://i.imgur.com/2.jpg",
            achievements = "Best Research Paper 2023",
            phone = "+880987654321",
            designations = "Associate Professor",
            deptName = "Mathematics",
            deptSortName = "Math",
            roomName = "B-202"
        ),
        Employee(
            name = "Charlie Brown",
            email = "charlie@khulna.edu",
            additionalEmail = "charlie.brown@hotmail.com",
            profileImageLink = "https://i.imgur.com/3.jpg",
            achievements = "Best Student Mentor 2021",
            phone = "+880456789123",
            designations = "Lecturer",
            deptName = "Physics",
            deptSortName = "Phys",
            roomName = "C-303"
        )
    )

@Composable
fun EmployeeListDemo() {
    EmployeeList(
       employees=employees
    )
}

@Composable
fun EmployeeList(
    modifier: Modifier=Modifier,
    employees: List<Employee>
) {
    GenericListScreen(
        modifier = modifier,
        items = employees
    ) {mod,teacher->
        EmployeeCard(
            modifier = mod.padding(8.dp),
            employee = teacher
        )
    }
}


@Composable
fun EmployeeCard(
    modifier: Modifier,
    employee: Employee
) {
    Surface(
        modifier = modifier,
        shadowElevation = 4.dp
    ) {
        val infoAlignment=Alignment.Start
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Load the profile image from the link
//            Image(
//                painter = rememberImagePainter(teacher.profileImageLink),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(CircleShape)
//                    .align(Alignment.CenterHorizontally)
//            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = employee.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(infoAlignment)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = employee.designations,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(infoAlignment)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${employee.deptSortName} - ${employee.roomName}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(infoAlignment)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = employee.email,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(infoAlignment)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = employee.additionalEmail,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(infoAlignment)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = employee.phone,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(infoAlignment)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = employee.achievements,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(infoAlignment)
            )
            // Add a row of buttons at the bottom
            Row(
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Add a button to send email to the teacher's email

                // Add a button to send email to the teacher's additional email
                IconButton(
                    onClick = {
                        // Open the email app with the teacher's additional email
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                }
                // Add a button to call the teacher's phone number
                IconButton(
                    onClick = {
                        // Call the teacher's phone number
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
}
