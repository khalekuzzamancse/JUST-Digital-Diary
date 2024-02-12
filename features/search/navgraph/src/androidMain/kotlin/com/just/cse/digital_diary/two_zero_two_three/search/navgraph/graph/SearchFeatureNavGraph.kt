package com.just.cse.digital_diary.two_zero_two_three.search.navgraph.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.search.functionalities.event.SearchFunctionalityEvent
import com.just.cse.digital_diary.two_zero_two_three.search.functionalities.searchable_employee_list.SearchableEmployeeList

/**
 * Separate between the lower level event,used so that client does need to depends of transient event,
 * the client can depends only this module event
 */
interface SearchFeatureEvent {
    data class CallRequest(val number: String) : SearchFeatureEvent
    data class MessageRequest(val number: String) : SearchFeatureEvent
    data class EmailRequest(val email: String) : SearchFeatureEvent
    data object ExitRequest : SearchFeatureEvent
}

object SearchFeatureNavGraph {
    private const val EMPLOYEES = "NoteListScreen"
    /**
     * * [SearchBar] active parameter consume the back button handle.
     * * that is why back button press event is not propagate up to parent.
     * * that is why notify parent that back button is pressed so that it pop the destination
     */
    @Composable
    fun Graph(
        navController: NavHostController = rememberNavController(),
        onEvent: (SearchFeatureEvent) -> Unit,
        onBackPress: () -> Unit,
    ) {
        NavHost(
            navController = navController,
            startDestination = EMPLOYEES
        ) {
            composable(EMPLOYEES) {
                SearchableEmployeeList(
                    barLeadingIcon = {
                        IconButton(onClick = {
                            onEvent(SearchFeatureEvent.ExitRequest)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu Button"
                            )
                        }
                    },
                    onEvent = { event ->
                        if (event is SearchFunctionalityEvent.ExitRequest)
                            onBackPress()
                        else
                            convertEvent(event)?.let { onEvent(it) }
                    },
                )
            }

        }


    }
}

private fun convertEvent(event: SearchFunctionalityEvent): SearchFeatureEvent? {
    val ev: SearchFeatureEvent? = when (event) {
        is SearchFunctionalityEvent.CallRequest -> SearchFeatureEvent.CallRequest(event.number)
        is SearchFunctionalityEvent.MessageRequest -> SearchFeatureEvent.MessageRequest(event.number)
        is SearchFunctionalityEvent.EmailRequest -> SearchFeatureEvent.EmailRequest(event.email)
        else -> null
    }
    return ev

}