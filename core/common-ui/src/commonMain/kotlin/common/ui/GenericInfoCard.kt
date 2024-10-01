package common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CardInfoState(
    val name: String,
    val shortName: String,
    val count: String,
    val isSelected: Boolean,
    val backgroundColorSelected: Color,
    val backgroundColorUnselected: Color,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val countLabel: String
)

@Composable
fun GenericInfoCard(
    modifier: Modifier=Modifier,
    state: CardInfoState,
    onSelect: () -> Unit
) {
    val backgroundColor = if (state.isSelected) state.backgroundColorSelected else state.backgroundColorUnselected
    val contentColor = MaterialTheme.colorScheme.contentColorFor(backgroundColor)

    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
            .selectable(selected = state.isSelected, onClick = onSelect, role = Role.Tab),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (state.isSelected) 4.dp else 2.dp,
            pressedElevation = 8.dp
        ),
        colors = CardDefaults.cardColors().copy(containerColor = backgroundColor),
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(
                text = state.name,
                style = MaterialTheme.typography.titleMedium,
                color = contentColor
            )
            Spacer(Modifier.height(8.dp))
            _GenericInfoRow(
                shortName = state.shortName,
                count = state.count,
                cardBackgroundColor = backgroundColor,
                isSelected = state.isSelected,
                iconSelected = state.iconSelected,
                iconUnselected = state.iconUnselected,
                countLabel = state.countLabel
            )
        }
    }
}
@Composable
private fun _GenericInfoRow(
    shortName: String,
    count: String,
    cardBackgroundColor: Color,
    isSelected: Boolean,
    iconSelected: ImageVector,
    iconUnselected: ImageVector,
    countLabel: String
) {
    val icon = if (isSelected) iconSelected else iconUnselected
    val iconTint = if (!isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val textColor = MaterialTheme.colorScheme.contentColorFor(cardBackgroundColor)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = shortName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            letterSpacing = 1.5.sp
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = countLabel,
                tint = iconTint
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "$count $countLabel",
                style = MaterialTheme.typography.bodySmall,
                color = textColor
            )
        }
    }
}