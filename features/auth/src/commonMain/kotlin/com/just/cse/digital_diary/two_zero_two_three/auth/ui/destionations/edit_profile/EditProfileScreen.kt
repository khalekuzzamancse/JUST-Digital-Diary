package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile.form.EditForm

@Composable
internal fun EditProfileScreen(
    onExitRequest:()->Unit,
) {
    val viewModel = remember { EditProfileViewModel() }
    WindowSizeDecorator(
        showProgressBar = viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.snackBarMessage.collectAsState().value,
        onCompact = {
            Scaffold(
                topBar = {
                    AuthTopBar(
                        title = "Edit Profile",
                        onNavigationIconClick = onExitRequest,
                        navigationIcon = Icons.Default.Menu
                    )
                },
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = viewModel::onEditRequest
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = null
                            )
                            Spacer(Modifier.width(8.dp))
                            Text("Edit Profile")
                        }

                    }
                }

            ) {
                EditForm(
                    modifier = Modifier.padding(it).padding(16.dp).verticalScroll(rememberScrollState()),
                    fieldModifier = Modifier.fillMaxWidth(),
                    data = viewModel.data.collectAsState().value,
                    onNameChanged = viewModel::onFullNameChanged,
                    onUserNameChanged = viewModel::onUsernameChanged,
                    onDeptChanged = viewModel::onDeptChanged,
                )
            }
        },
        onNonCompact = {
            Box(Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState())){
                Surface (modifier = Modifier.widthIn(max=500.dp).align(Alignment.TopCenter), shadowElevation = 6.dp){
                    Column(
                        modifier =  Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        EditForm(
                            modifier = Modifier.padding(16.dp),
                            fieldModifier = Modifier.fillMaxWidth(),
                            data = viewModel.data.collectAsState().value,
                            onNameChanged = viewModel::onFullNameChanged,
                            onUserNameChanged = viewModel::onUsernameChanged,
                            onDeptChanged = viewModel::onDeptChanged,
                        )
                        Spacer(Modifier.width(12.dp))

                        Row(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                        ) {
                            TextButton(
                                onClick = onExitRequest
                            ){
                                Text(text="Cancel")

                            }
                            Spacer(Modifier.width(8.dp))
                            TextButton(
                                onClick = onExitRequest
                            ){
                                Text(text="Save")

                            }

                        }


                    }
                }
            }


        }
    )

}