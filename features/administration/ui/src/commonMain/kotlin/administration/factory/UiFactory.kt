package administration.factory

import administration.factory.add_officers.OfficerFormControllerImpl
import administration.factory.add_officers.ValidatorImpl
import administration.ui.admin.add_officers.OfficerFormController

object UiFactory {

    fun createOfficersAddFormController(): OfficerFormController =
        OfficerFormControllerImpl(validator = ValidatorImpl())

}