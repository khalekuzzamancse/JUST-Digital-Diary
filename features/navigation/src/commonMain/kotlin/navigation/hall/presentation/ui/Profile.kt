package navigation.hall.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import common.ui.EmptyContentScreen
import common.ui.ImageLoader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface ProfileController : ICoreController {
    val profile: StateFlow<UserProfile?>
    val isAdmin: StateFlow<Boolean>
    fun onUpdateBalanceRequest(balance: Double)
}

internal class ProfileControllerImpl : ProfileController, CoreController() {
    private val _profile = MutableStateFlow<UserProfile?>(
        UserProfile(
            id = "190142",
            avatar = "https://avatars.githubusercontent.com/u/74848657?v=4",
            name = "Md Khalekuzzaman",
            email = "khalek@example.com",
            dept = "Computer Science and Engineering(CSE)",
            balance = 100.00
        )
    )
    private val _isAdmin= MutableStateFlow(true)
    override val isAdmin=_isAdmin.asStateFlow()
    override val profile = _profile.asStateFlow()
    override fun onUpdateBalanceRequest(balance: Double) {
        _profile.update { model ->
            model?.copy(balance = balance)
        }
    }


    override val isLoading = super._isLoading.asStateFlow()
    override val statusMessage = super._statusMessage.asStateFlow()
}


data class UserProfile(
    val avatar: String,
    val id: String,
    val name: String,
    val email: String,
    val dept: String,
    val balance: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigationIcon: @Composable () -> Unit,
    controller: ProfileController,
) {
    val model = controller.profile.collectAsState().value
    val isAdminMode=controller.isAdmin.collectAsState().value
    if (model == null) {
        EmptyContentScreen("No user found")
    } else {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (!isAdminMode)"✌️ Hey ${model.name}!" else model.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    },
                    navigationIcon = navigationIcon
                )
            }
        ) {
            UserProfileCard(
                modifier = Modifier.padding(it),
                user = model,
                isAdmin = isAdminMode,
                onUpdateBalanceRequest = controller::onUpdateBalanceRequest
            )
        }
    }


}


@Composable
fun UserProfileCard(
    modifier: Modifier = Modifier,
    user: UserProfile,
    isAdmin: Boolean,
    onUpdateBalanceRequest: (Double) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        UpdateBalanceDialog(
            currentBalance = user.balance.toString(),
            onDismissRequest = {
                showDialog = false

            },
            onUpdateClick = { balance ->
                onUpdateBalanceRequest(balance)
                showDialog = false
            }
        )
    }
    UserProfileLayoutStrategy(
        modifier = modifier.width(IntrinsicSize.Max),
        name = {},
        avatar = { mod ->
            ImageLoader(
                url = user.avatar,
                modifier = mod
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))

            )
        },
        email = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
            }
        },
        dept = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.School,
                    contentDescription = "Department",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user.dept)
            }
        },
        balance = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.AccountBalanceWallet,
                    contentDescription = "Balance",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${user.balance} Tk", style = MaterialTheme.typography.bodyLarge)
            }
        },
        id = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Badge,
                    contentDescription = "ID",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = user.id, style = MaterialTheme.typography.bodySmall)
            }
        },
        adminActions = {
            if (isAdmin) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Update Balance Button
                    Button(
                        onClick = {
                            showDialog = true
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                    ) {
                        Text(text = "Update Balance")
                    }

                    // Delete User Button
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                    ) {
                        Text(text = "Delete User")
                    }
                }
            }

        }
    )
}


@Composable
fun UserProfileLayoutStrategy(
    modifier: Modifier = Modifier,
    name: @Composable (Modifier) -> Unit,
    avatar: @Composable (Modifier) -> Unit,
    email: @Composable () -> Unit,
    id: @Composable () -> Unit,
    dept: @Composable () -> Unit,
    balance: @Composable () -> Unit,
    adminActions: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        name(Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(8.dp))
        avatar(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(8.dp))
        id()
        Spacer(modifier = Modifier.height(8.dp))
        email()
        Spacer(modifier = Modifier.height(8.dp))
        dept()
        Spacer(modifier = Modifier.height(8.dp))
        balance()
        Spacer(modifier = Modifier.height(16.dp))
        adminActions()
    }
}

@Composable
fun UpdateBalanceDialog(
    currentBalance: String,
    onDismissRequest: () -> Unit,
    onUpdateClick: (Double) -> Unit
) {
    var balanceInput by remember { mutableStateOf(currentBalance) }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Update Balance",
                    style = MaterialTheme.typography.headlineSmall
                )

                // Input field for numeric balance
                OutlinedTextField(
                    value = balanceInput,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() || char == '.' }) {
                            balanceInput = it
                        }
                    },
                    label = { Text("Enter new balance") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            val newBalance = balanceInput.toDoubleOrNull()
                            if (newBalance != null) {
                                onUpdateClick(newBalance)
                                onDismissRequest()
                            }
                        }
                    ) {
                        Text("Update")
                    }
                }
            }
        }
    }
}