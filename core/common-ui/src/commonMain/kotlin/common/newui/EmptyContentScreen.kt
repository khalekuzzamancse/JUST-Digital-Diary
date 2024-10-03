package common.newui
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyContentScreen(
    message:String="No content found",
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),  // Add padding if needed
        contentAlignment = Alignment.Center  // Center content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Large Icon to denote empty state
            Icon(
                imageVector = Icons.Default.HourglassEmpty,
                contentDescription = "No content icon",
                modifier = Modifier.size(128.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                fontSize = 20.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}