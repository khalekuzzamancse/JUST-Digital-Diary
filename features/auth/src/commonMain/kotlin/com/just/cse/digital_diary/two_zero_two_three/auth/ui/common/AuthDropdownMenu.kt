package com.just.cse.digital_diary.two_zero_two_three.auth.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.MyDrop

@Composable
fun AuthDropDownMenu(
    modifier: Modifier,
    options:List<String>,
    onOptionSelected: (String) -> Unit,
    leadingIcon:ImageVector?=null,
    label: String,
    selected: String,
) {
    Column {
        Text(
            text = label,
        )
        Spacer(Modifier.height(8.dp))
        MyDrop(
            modifier=modifier,
            options = options,
            selected = selected, onOptionSelected = onOptionSelected,
            leadingIcon = leadingIcon
        )
    }


}