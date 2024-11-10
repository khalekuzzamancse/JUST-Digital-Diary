package navigation.hall.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import common.ui.ImageLoader

@Composable
fun StudentListScreen(
    modifier: Modifier = Modifier,
    students: List<UserProfile>,
    onDetailsRequest: (id: String) -> Unit,
) {
    val background =
        remember { Color.White }////Right now supporting only light mode in both dark and light theme
    val contentColor = remember {
        if (background.luminance() > 0.5f) Color.Black else Color.White
    }
    Scaffold(
        modifier = Modifier,
        topBar = {
            SearchBar(
                background = background,
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = modifier.padding(innerPadding).background(Color.White),
            columns = GridCells.Adaptive(minSize = 180.dp),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(students) { student ->
                UserShortInfo(
                    modifier = Modifier.clickable {
                        onDetailsRequest(student.id)
                    },
                    user = student
                )
            }
        }

    }


}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    background: Color,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val contentColor = remember {
        if (background.luminance() > 0.5f) Color.Black else Color.White
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(background),
        placeholder = { Text(text = hint) },
        leadingIcon = {
            // If a leading icon is passed, display it
            leadingIcon?.invoke()
        },
        trailingIcon = {
            if (text.text.isEmpty()) {
                IconButton(onClick = { /* No action on search icon click */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = contentColor
                    )
                }
            } else {
                IconButton(onClick = { text = TextFieldValue("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Text",
                        tint = contentColor
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedTextColor = contentColor,
            focusedTextColor = contentColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        )
    )
}

fun Modifier.gradientBackground(): Modifier {
    val primaryColor = Color(0xFF008A4E) // Green Form JUST logo
    val secondaryColor = Color(0xFFDB372B) // Red from JUST logo
    val gradientBrush = Brush.linearGradient(
        colors = listOf(primaryColor, secondaryColor)
    )
    return this.background(brush = gradientBrush)
}
@Composable
fun UserShortInfo(
    modifier: Modifier = Modifier,
    name: AnnotatedString,
    avatar:String,
    id:AnnotatedString,
) {
    val contentColor =
        Color.White //as par the gradient background,color is white regardless of theme
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
    ) {
        UserShortInfoLayoutStrategy(
            modifier = Modifier.gradientBackground().padding(8.dp),
            name = { mod ->
                Row(mod, verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "name",
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = contentColor
                    )
                }
            },
            avatar = { mod ->
                ImageLoader(url = avatar, modifier = mod)
            },
            id = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Badge,
                        contentDescription = "id",
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = id,
                        style = MaterialTheme.typography.bodySmall,
                        color = contentColor
                    )
                }
            }
        )
    }
}
@Composable
fun UserShortInfo(
    modifier: Modifier = Modifier,
    user: UserProfile,
) {
    val contentColor =
        Color.White //as par the gradient background,color is white regardless of theme
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
    ) {
        UserShortInfoLayoutStrategy(
            modifier = Modifier.gradientBackground().padding(8.dp),
            name = { mod ->
                Row(mod, verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "name",
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = contentColor
                    )
                }
            },
            avatar = { mod ->
                ImageLoader(url = user.avatar, modifier = mod)
            },
            id = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Badge,
                        contentDescription = "id",
                        tint = contentColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = user.id,
                        style = MaterialTheme.typography.bodySmall,
                        color = contentColor
                    )
                }
            }
        )
    }
}

@Composable
fun UserShortInfoLayoutStrategy(
    modifier: Modifier = Modifier,
    name: @Composable (Modifier) -> Unit,
    avatar: @Composable (Modifier) -> Unit,
    id: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        avatar(Modifier.size(64.dp).clip(RoundedCornerShape(4.dp)))
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            name(Modifier)
            Spacer(modifier = Modifier.height(8.dp))
            id()


        }
    }

}
