package com.just.cse.digital_diary.features.faculty.faculty.dd.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeContent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.viewmodel.FacultyDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.repoisitory.FacultyListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

@Composable
fun FacultiesScreen() {
    val facultyViewModel= FacultyDestinationViewModel(repository = FacultyListRepositoryImpl())

    WindowSizeDecorator(
        onCompact = {
            FacultyListDestination(
                viewModel = facultyViewModel,
                homeContent = {
                    HomeContent()
                }

            )


        },
        onNonCompact = {
            TwoPaneLayout(
                secondaryPaneAnimationState = false,
                showTopOrRightPane = true,
                leftPane = {
                    FacultyListDestination(
                        viewModel =facultyViewModel ,
                        homeContent = {
                            HomeContent()
                        }

                    )
                },
                topOrRightPane = {
                    DepartmentList(facultyId = "01")
                }
            )
        }
    )


}