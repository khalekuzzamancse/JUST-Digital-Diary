package navigation.hall.presentation.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private val sampleUser = UserProfile(
    id = "190142",
    avatar = "https://avatars.githubusercontent.com/u/74848657?v=4",
    name = "Md Khalekuzzaman",
    email = "khalek@example.com",
    dept = "Computer Science and Engineering(CSE)",
    balance = 2500.50
)
 val students = List(20) { sampleUser }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminNavHost() {

    val navController = rememberNavController()

    val adminItems = listOf(
        NavigationItem("Ordered", Icons.Default.Home),
        NavigationItem("Summary", Icons.Default.Summarize),
        NavigationItem("Students", Icons.Default.List),
    )


    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomBar(
                items = adminItems,
                selected = selectedIndex,
                onSelected = {
                    selectedIndex = it
                    when(it){
                        0->navController.navigate("ordered-meal")
                        1->navController.navigate("summary")
                        2->navController.navigate("students")
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "ordered-meal",
        ) {
            composable(route = "ordered-meal") {
                OrderedMealScreen(
                    onProfileRequest = {
                        navController.navigate("profile")
                    }
                )
            }
            composable(route = "students") {
                StudentListScreen(
                    students = students,
                    onDetailsRequest = {
                        navController.navigate("profile")
                    }
                )
            }
            composable(route = "summary") {
                SummaryScreen()
            }
            composable(route = "profile") {
                ProfileScreen(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back",
                            )
                        }
                    },
                    controller = ProfileControllerImpl()
                )

            }

        }


    }



}

data class NavigationItem(
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    selected: Int,
    onSelected: (Int) -> Unit
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onSelected(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                label = { Text(text = item.label) }
            )
        }
    }
}
