package common.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onValueChanged: (String) -> Unit,
    trailingIcon: (@Composable (Modifier) -> Unit)? = null
) {
    _BasicAuthTextField(
        modifier = modifier,
        enabled = enabled,
        label = label,
        value = value,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        keyboardType = keyboardType,
        onValueChanged = onValueChanged,
        readOnly = readOnly,
        trailingIcon = trailingIcon
    )
}

@Composable
private fun _BasicAuthTextField(
    modifier: Modifier,
    label: String,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: ImageVector?,
    keyboardType: KeyboardType,
    onValueChanged: (String) -> Unit,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    trailingIcon: (@Composable (Modifier) -> Unit)? = null
) {

    val borderColor = if (enabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)

    val placeholderColor = if (enabled) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)

    val textColor = if (enabled) MaterialTheme.colorScheme.onSurface
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)

    val iconTint = if (enabled) MaterialTheme.colorScheme.tertiary
    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

    val fontSize = 15.sp

    BasicTextField(
        enabled = enabled,
        value = value,
        onValueChange = onValueChanged,
        textStyle = TextStyle(fontSize = fontSize, color = textColor),
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        cursorBrush = if (enabled) SolidColor(MaterialTheme.colorScheme.primary) else SolidColor(Color.Transparent), // Hide cursor when disabled
        decorationBox = { innerText ->
            Row(
                modifier
                    .border(
                        width = 2.dp,
                        color = borderColor,
                        shape = CircleShape
                    )
                    .padding(vertical = 10.dp, horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        tint = iconTint,
                        contentDescription = "leading icon",
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(22.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                }

                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        _Placeholder(label, fontSize, placeholderColor)
                    }
                    // Call innerText in both cases to ensure the cursor is shown (if enabled)
                    innerText()
                }

                if (trailingIcon != null) {
                    Spacer(Modifier.width(8.dp))
                    trailingIcon(Modifier.padding(end = 8.dp))
                }
            }
        }
    )
}

@Composable
fun _Placeholder(text: String, fontSize: TextUnit, placeholderColor: Color) {
    Text(
        text = text,
        fontSize = fontSize,
        color = placeholderColor
    )
}


@Composable
private fun _Placeholder(
    text: String,
    fontSize: TextUnit,
    color: Color,
    fontWeight: FontWeight = FontWeight.W600,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight
    )

}

//TODO: Dropdown sections


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
            value = if (selected == null) "" else {
                if (selected >= 0 && selected < options.size)//Otherwise can cause out of bounds exception
                    options[selected]
                else ""
            },
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
