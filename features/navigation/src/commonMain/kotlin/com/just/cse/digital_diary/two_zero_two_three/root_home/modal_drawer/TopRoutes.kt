package com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.NavItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.NavGroup

/**
 * Instead of using [NavItem] direcly we used this extra class's[NavDestination]
 * so that it make sure that drawer or nav item are must be the instance of this class.
 * it will give the type safety,since [NavItem] is a global class as a result
 * it can have multiple instance some instance that are declared outside,which may lead wanted result
 * to the drawer.
 * to make the type safe we wrap the destination into a sealed class so that we can make sure
 * this TopRoute instance is not created somewhere else,only here is created,so it maintain single source
 * of truth
 */


object TopRoutes {
    sealed class NavDestination(
        val label: String,
        val icon: ImageVector
    )

    data object Home : NavDestination(label = "Home", icon = Icons.Default.Home)

    data object Faculties : NavDestination(label = "Faculty List", icon = Icons.Outlined.School)
    data object AdminOffice :
        NavDestination(label = "Admin Offices", icon = Icons.Outlined.AdminPanelSettings)

    data object MessageFromVC :
        NavDestination(label = "Message From VC", icon = Icons.AutoMirrored.Outlined.Message)

    data object Search : NavDestination(label = "Search", icon = Icons.Outlined.Search)
    data object EventGallery : NavDestination(label = "Event Gallery", icon = Icons.Outlined.Image)

    data object ExploreJUST : NavDestination(label = "Explore JUST", icon = Icons.Outlined.Explore)
    data object Notes : NavDestination(label = "My Notes", icon = Icons.AutoMirrored.Outlined.Notes)
    data object AboutUS : NavDestination(label = "About Us", icon = Icons.Outlined.Info)

    private val group1 = listOf(Home).makeGroup()
    private val group2 = listOf(Faculties, AdminOffice).makeGroup()
    private val group3 = listOf(Search, EventGallery, ExploreJUST).makeGroup()
    private val group4 = listOf(Notes).makeGroup()
    private val group5 = listOf(MessageFromVC, AboutUS).makeGroup()

    val navGroups = listOf(group1, group2, group3, group4, group5)

    private val destinations = listOf(
        Home,
        Faculties,
        AdminOffice,
        MessageFromVC,
        Search,
        EventGallery,
        ExploreJUST,
        Notes,
        AboutUS
    )

    fun getDestination(id: String): NavDestination? {
        return destinations.find { it.label == id }
    }

    private fun List<NavDestination>.makeGroup(name: String? = null) = NavGroup(
        label = name, items = this.map {
            NavItem(label = it.label, unselectedIcon = it.icon)
        })


}
