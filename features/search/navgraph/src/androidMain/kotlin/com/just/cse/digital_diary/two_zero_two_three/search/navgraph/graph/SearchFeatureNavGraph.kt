package com.just.cse.digital_diary.two_zero_two_three.search.navgraph.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
}

object SearchFeatureNavGraph {
    private const val EMPLOYEES = "NoteListScreen"
    @Composable
    fun Graph(navController: NavHostController = rememberNavController(),onEvent:(SearchFeatureEvent)->Unit) {
        NavHost(
            navController = navController,
            startDestination = EMPLOYEES
        ) {
            composable(EMPLOYEES) {
                SearchableEmployeeList(
                    barLeadingIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu Button"
                            )
                        }
                    },
                    onEvent = {event->
                        convertEvent(event)?.let { onEvent(it) }
                    }
                )
            }

        }


    }
}

private fun convertEvent(event: SearchFunctionalityEvent):SearchFeatureEvent?{
    val ev:SearchFeatureEvent?=when(event){
        is SearchFunctionalityEvent.CallRequest->SearchFeatureEvent.CallRequest(event.number)
        is SearchFunctionalityEvent.MessageRequest->SearchFeatureEvent.MessageRequest(event.number)
        is SearchFunctionalityEvent.EmailRequest->SearchFeatureEvent.EmailRequest(event.email)
        else ->null
    }
    return ev

}