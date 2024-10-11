package common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.toSize


/**
 * - Must override the toSting because to string will be shown as option
 */
@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    options: List<String>,
    label: String = "",
    selected: Int?,
    onOptionSelected: (Int) -> Unit,
    leadingIcon: ImageVector? = null,
    color: TextFieldColors = TextFieldDefaults.colors()
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Box {
        CustomTextField(
            modifier = modifier.onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
            readOnly = true,
            value = if (selected == null) "" else options[selected],
            onValueChanged = {

            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        isExpanded = true
                    }
                )
            },
            leadingIcon = leadingIcon,
            label = label
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            offset = DpOffset.Zero.copy(
                y = -((with(LocalDensity.current) { textFieldSize.height.toDp() }))
            )
        ) {
            options.forEachIndexed { index, value ->
                DropdownMenuItem(
                    text = {
                        Text(text = value)
                    },
                    onClick = {
                        onOptionSelected(index)
                        isExpanded = false
                    }
                )
            }
        }
    }


}
