package administration.ui.public_

import administration.controller_presenter.controller.OfficeController
import administration.controller_presenter.controller.SubOfficeController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class OfficeScreenViewModel internal constructor(
    val officeController: OfficeController,
    val subOfficeController: SubOfficeController
) {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            officeController.fetch()
        }
    }

    private val _showSubOffice = MutableStateFlow(false)
    val showSubOffice = _showSubOffice.asStateFlow()
    fun onSubOfficeClose() {
        _showSubOffice.update { false }
        //clear the faculty selection
        officeController.onSelected(null)

    }

    val isLoading: Flow<Boolean> =
        combine(subOfficeController.isFetching, officeController.isFetching)
        { isLogging, isRegistering ->
            isLogging || isRegistering
        }
    val screenMessage: Flow<String?> =
        combine(subOfficeController.errorMessage, officeController.errorMessage)
        { loginMsg, registerMsg ->
            loginMsg ?: registerMsg
        }

    init {

        CoroutineScope(Dispatchers.Default).launch {
            officeController.selected.collect { facultyIndex ->
                //TODO: a faculty is selected,find the faculty id and load it dept
                subOfficeController.sobOffices
                //Right now just pretending that department is loaded
                if (facultyIndex != null) {
                    try {
                        val facultyId = officeController.offices.value[facultyIndex].id
                        subOfficeController.fetch(facultyId)
                        _showSubOffice.update { true }
                    } catch (_: Exception) {

                    }

                }

            }

        }
    }


}
