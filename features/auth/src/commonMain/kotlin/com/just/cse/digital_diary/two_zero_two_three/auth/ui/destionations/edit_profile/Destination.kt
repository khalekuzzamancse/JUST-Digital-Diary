package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import javax.swing.Icon

@Composable
internal fun EditProfileDestination(
    onExitRequest:()->Unit,
) {
    val viewModel = remember { EditProfileViewModel() }
    ProgressBarNSnackBarDecorator(
        modifier = Modifier,
        showProgressBar = viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.snackBarMessage.collectAsState().value
    ) {
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
                modifier = Modifier.padding(it).padding(16.dp),
                viewModel = viewModel,
                fieldModifier = Modifier,
            )


        }
    }


}