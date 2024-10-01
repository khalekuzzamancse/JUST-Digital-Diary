package common.newui
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
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
 fun ErrorText(
    modifier: Modifier = Modifier,
    errorMessages: List<String>
) {
     Column {
         errorMessages.forEach { msg->
             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = modifier.padding(4.dp)
             ) {
                 Icon(
                     imageVector = Icons.Default.Error,
                     contentDescription = "Error",
                     tint = Color.Red,
                     modifier = Modifier.size(20.dp)
                 )
                 Spacer(modifier = Modifier.width(8.dp))
                 Text(
                     text = msg,
                     color = Color.Red,
                     fontSize = 14.sp,
                     fontWeight = FontWeight.Bold
                 )
             }
             Spacer(modifier = Modifier.height(4.dp))

         }

     }

}
