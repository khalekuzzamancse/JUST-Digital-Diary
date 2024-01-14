package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.TypeWriter
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home.HomeTopAppbar
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home.UserInfo

private val features = listOf(
    "Create notes with rich text formatting",
    "Add photos from your gallery or camera",
    "Record voice memos and play them back",
    "Sync your data across devices with cloud storage",
    "Search and filter your notes by date, title, or tag",
    "Protect your diary with a password or fingerprint"
)

@Composable
fun AboutUs(
    onExitRequest:()->Unit,
) {
    Scaffold(
        topBar = {
            AboutUsTopBar(
                onNavigationIconClick = onExitRequest,
                title = "About Us"
            )
        },

        floatingActionButtonPosition = FabPosition.Center
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()

                .verticalScroll(rememberScrollState())
        ) {
            AppName()
            AppDescription()
            FeaturesSection()
            ContactSection()
        }


    }

}

@Composable
fun ColumnScope.AppName() {
    Text(
        text = "Just Digital Diary",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun AppDescription() {

    val text = buildAnnotatedString {
        append("Just Digital Diary is a multi-platform app that lets you create and manage your personal digital diary. You can write notes, add photos, record voice memos, and sync your data across Android, iOS, desktop, and web devices. Just Digital Diary is developed by the ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        ) {
            append("Computer Science and Engineering Department")
        }
        append(" of ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        ) {
            append("Jashore University of Science and Technology")
        }
        append(".")
    }

    TypeWriter(
        text=text,
        delay = 10){
        Text(
            text = it,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun ColumnScope.FeaturesSection() {
    Text(
        text = "Features:",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.height(8.dp))
    FeaturesList()
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun FeaturesList() {
    val symbols = listOf("\u25CF", "\u2713", "\u2605", "\u2764", "\u262F", "\u263A")
    val textColor = MaterialTheme.colorScheme.onSurface

    Column {
        features.forEachIndexed { index, feature ->
            BulletPointText(symbol = symbols.getOrNull(index) ?: "\u2022", feature = feature, textColor = textColor)
        }
    }
}

@Composable
fun BulletPointText(symbol: String, feature: String, textColor: Color) {
    // Display a text with the symbol and the feature

    TypeWriter(text=feature, delay = 10){
        Text(
            text = "$symbol $it",
            style = TextStyle(
                color = textColor,
                fontWeight = FontWeight.Normal,
                textDecoration = TextDecoration.None
            ),
            textAlign = TextAlign.Justify
        )
    }

}




@Composable
fun ColumnScope.ContactSection() {
    Text(
        text = "Contact:",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.height(8.dp))
    ContactInfo()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColumnScope.ContactInfo() {
    FlowRow(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            ContactIcon(imageVector = Icons.Default.Email, contentDescription = "Email")
            TypeWriter(text="justdigitaldiary@gmail.com", delay = 250){
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }
       Row {  ContactIcon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
           TypeWriter(text="+880-1234-5678", delay = 250){
               Text(
                   text = it,
                   style = MaterialTheme.typography.bodyLarge
               )
           }

       }
    }
}

@Composable
fun ContactIcon(imageVector: ImageVector, contentDescription: String) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.primary
    )
}
