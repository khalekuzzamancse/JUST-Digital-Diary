package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NonCompactScreenLayout(

    facultyDestinations: @Composable () -> Unit,
    departmentDestinations: (@Composable () -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
){
    Row(modifier = Modifier.fillMaxWidth()) {
       facultyDestinations()
        Spacer(Modifier.width(16.dp))
        Box(Modifier.weight(1f)) {
            //do not recompose the content so that it not again animated when back from department list
            if (content != null) {
                content()
            }
            if (departmentDestinations!=null) {
                AnimatedContent(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    targetState = true
                ) {
                    departmentDestinations()
                }
            }
        }


    }

}