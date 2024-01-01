package com.just.cse.digital_diary.two_zero_two_three.root_home.ui.share_note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldProperties
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldState
import com.just.cse.digital_diary.features.common_ui.form.FormTextInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteCreationState {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()
    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()
    fun onTitleChanged(title: String) {
        _title.value = title
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

}

class CreateNoteScreen: Screen {
    @Composable
    override fun Content() {
        val navigator= LocalNavigator.current
       CreateNoteDestination(
           onBackArrowClick = {
               navigator?.pop()
           }
       )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteDestination(
    onBackArrowClick: () -> Unit = {},
) {
    val uiState = remember {
        NoteCreationState()
    }

    val properties = remember {
        FormTextFieldProperties(
            label = "Title",
        )
    }


    var state by remember {
        mutableStateOf(
            FormTextFieldState()
        )
    }
    val onValueChanged: (String) -> Unit = {
        state = state.copy(
            value = it,
        )
    }

    AuthTextField(
        properties = properties,
        state = state,
        onValueChanged = onValueChanged,
        isHorizontalOrientation = false,
        labelMinWidth = 100.dp
    )

    val title = "New Note"


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {

                },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(onClick = {

            }) {
                Text(text = "Create Note")
            }


        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                CreateNoteTextField(
                    label = "Title",
                    text = uiState.title.collectAsState().value,
                    onValueChanged = uiState::onTitleChanged,
                    singleLine = true
                )
                Spacer(Modifier.height(16.dp))
                CreateNoteTextField(
                    label = "Description",
                    onValueChanged = uiState::onDescriptionChanged,
                    text = uiState.description.collectAsState().value
                )

            }

        }

    }
}

@Composable
fun CreateNoteTextField(
    singleLine: Boolean = true,
    label: String,
    text: String,
    onValueChanged: (String) -> Unit,
) {
    FormTextInput(
        properties = FormTextFieldProperties(
            label = label,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Unspecified,
                unfocusedContainerColor = Color.Unspecified
            ),
            singleLine = singleLine
        ),
        value = text,
        onValueChanged = onValueChanged,
        labelFieldLayout = { label1, field ->
            Column {
                label1(Modifier)
                Spacer(Modifier.height(2.dp))
                field(Modifier)
            }

        }
    )
}