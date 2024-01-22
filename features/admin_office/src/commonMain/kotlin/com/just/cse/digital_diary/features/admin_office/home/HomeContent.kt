package com.just.cse.digital_diary.features.admin_office.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.animation.TypeWriter
import com.just.cse.digitaldiary.twozerotwothree.data.repository.faculties_info.FacultiesInfoRepository

@Composable
fun HomeContent() {
    val info= FacultiesInfoRepository.getInfo()
    Info(info=info.info,
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()))
}

@Composable
private fun Info(
    info: String,
    modifier: Modifier=Modifier,
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Welcome to to Admin Offices",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )



    }


}