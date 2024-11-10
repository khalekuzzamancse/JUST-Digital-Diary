package navigation.hall.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


internal data class MealModel(
    val type: TimeType,
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val imageLink: String,
    val servingTime: String
)

@Composable
internal fun MealItem(
    meal: MealModel,
    enableOrder: Boolean,
    onOrder: () -> Unit,
) {
    val background = meal.type.cardBackground
    val contentColor = remember { if (background.luminance() > 0.5f) Color.Black else Color.White }
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        modifier = Modifier,
        colors = CardDefaults.cardColors(containerColor = background)
    ) {
        MealLayoutStrategy(
            modifier = Modifier.widthIn(max = 180.dp).padding(8.dp),
            image = { mod ->
                Box(
                    modifier = mod
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    when (meal.type) {
                        TimeType.BreakFast -> MorningIcon(
                            sunColor = meal.type.symbolColor,
                            modifier = Modifier.padding(8.dp).size(80.dp)
                        )

                        TimeType.Launch -> NoonIcon(
                            modifier = Modifier.padding(8.dp).size(80.dp),
                            sunColor = meal.type.symbolColor,
                            rayColor = meal.type.symbolColor
                        )

                        TimeType.Dinner -> NightIcon(
                            modifier = Modifier.padding(8.dp).size(80.dp),
                            moonColor = meal.type.symbolColor,
                            starColor = Color.Red,
                            background = meal.type.cardBackground,
                        )
                    }
                }
            },
            title = {
                Text(
                    text = meal.name,
                    fontSize = 16.sp,
                    color = contentColor
                )
            },
            price = {
                Text(
                    text = "${meal.price} Tk",
                    fontSize = 16.sp,
                    color = contentColor
                )
            },
            serviceTime = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Schedule,
                        contentDescription = "serve time",
                        modifier = Modifier,
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = meal.servingTime,
                        fontSize = 15.sp,
                        color = Color.Gray,
                        modifier = Modifier
                    )

                }


            },
            orderControl = {
                IconButton(
                    onClick = onOrder,
                    enabled = enableOrder,
                    modifier = Modifier
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "order",
                        tint = if (enableOrder) contentColor else Color.Gray//since click-able
                    )
                }
            })
    }

}


@Composable
fun MealLayoutStrategy(
    modifier: Modifier = Modifier,
    image: @Composable (Modifier) -> Unit,
    title: @Composable () -> Unit,
    price: @Composable () -> Unit,
    serviceTime: @Composable () -> Unit,
    orderControl: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        // Meal Image
        image(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(12.dp))
        title()
        Spacer(modifier = Modifier.height(8.dp))
        serviceTime()
        //Less space because the button has already padding
        //If more padding the vertical gap will be more
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            price()
            Spacer(Modifier.weight(1f))
            orderControl()
        }
    }
}
