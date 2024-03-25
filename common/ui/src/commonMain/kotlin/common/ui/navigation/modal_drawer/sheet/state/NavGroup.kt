package common.ui.navigation.modal_drawer.sheet.state

import androidx.compose.runtime.Immutable
import common.ui.navigation.modal_drawer.sheet.NavItem

/**
 * @param label is nullable because there are some use cases when label is not needed,only divider is enough
 */
@Immutable
data class NavGroup(
    val label:String?=null,
    val items:List<NavItem>
)
