package navigation.hall.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun StudentNavGraph() {
    val sampleMeal = MealModel(
        id = 1,
        name = "Dinner",
        description = "A delicious, hearty meal to end your day.",
        price = 45,
        imageLink = "https://media.istockphoto.com/id/1625128632/photo/most-common-allergy-food-shot-from-above.jpg?b=1&s=612x612&w=0&k=20&c=jy8uEBErKnHmnunQ3xe-vetl65EGf__ZKOOs_bjCAaY=",
        servingTime = "7:00 PM - 9:00 PM",
        type = TimeType.Launch
    )

    val mealModels = listOf(
        sampleMeal.copy(type = TimeType.BreakFast, name = "BreakFast"),
        sampleMeal.copy(type = TimeType.Launch, name = "Launch"),
        sampleMeal.copy(type = TimeType.Dinner, name = "Dinner"),
    )
    val controller= remember { ProfileControllerImpl() }
    val profile=controller.profile.collectAsState().value

    val navController = rememberNavController()

    val adminItems = listOf(
        NavigationItem("Home", Icons.Default.Home),
        NavigationItem("Ordered", Icons.AutoMirrored.Filled.List),
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
                        0->navController.navigate("home")
                        1->navController.navigate("ordered")
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = "home",
        ) {
            composable(route="home"){
                Column(
                    modifier = Modifier
                ) {
                    UserProfileCard(
                        modifier = Modifier,
                        user = profile!!,
                        isAdmin = false,
                        onUpdateBalanceRequest = {}
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(text = "Available meals", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(12.dp))
                    LazyRow(
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        itemsIndexed(mealModels) { index, item ->
                            val isNotLastItem = (index != mealModels.lastIndex)
                            MealItem(
                                meal = item,
                                enableOrder = profile.balance >= item.price,
                                onOrder = {
                                    val newBalance=profile.balance-item.price
                                    controller.onUpdateBalanceRequest(newBalance)
                                }
                            )
                            if (isNotLastItem)
                                Spacer(Modifier.width(8.dp))
                        }
                    }
                }
            }
            composable(route = "ordered") {
                OrderMealByStudent()
            }
        }
    }



}