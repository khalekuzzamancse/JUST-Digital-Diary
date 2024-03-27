package realm.test

import database.local.api.AdministrationAPIs
import database.local.schema.administration.AdminOfficeEntityLocal
import database.local.schema.administration.AdminOfficerEntityLocal
import database.local.schema.administration.AdminSubOfficeEntityLocal
import kotlinx.coroutines.runBlocking
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AdministrativeAPIsTest {
    @Test
    fun `add AdminOfficeEntityLocal test`() {
        runBlocking {
            val requestModel = AdminOfficeEntityLocal(
                id = 1, officeId = "1", name = "Test Office", subOfficesCount = 2
            )
            val responseModel = AdministrationAPIs.addAdminOffice(requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }

    @Test
    fun `retrieve all AdminOfficeEntityLocal test`() {
        runBlocking {
            val requestModel = AdminOfficeEntityLocal(
                id = 1, officeId = "1", name = "Test Office", subOfficesCount = 2
            )
            AdministrationAPIs.addAdminOffice(requestModel)
            val responseModel = AdministrationAPIs.retrieveAdminOffices()
            responseModel.getOrNull()?.let { assertTrue(it.isNotEmpty()) }
            println(responseModel)
        }
    }

    @Test
    fun `add AdminSubOfficeEntityLocal test`() {
        runBlocking {
            val requestModel = AdminSubOfficeEntityLocal(
                id = 2, subOfficeId = "S1", name = "Test Sub Office", officeMembersCount = 5
            )
            val responseModel = AdministrationAPIs.addSubOffice(requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }

    @Test
    fun `retrieve all AdminSubOfficeEntityLocal test`() {
        runBlocking {
            val requestModel = AdminSubOfficeEntityLocal(
                id = 2, subOfficeId = "S1", name = "Test Sub Office", officeMembersCount = 5
            )
            AdministrationAPIs.addSubOffice(requestModel)
            val responseModel = AdministrationAPIs.retrieveSubOffices("1")
            responseModel.getOrNull()?.let { assertTrue(it.isNotEmpty()) }
            println(responseModel)
        }
    }

    @Test
    fun `add AdminOfficerEntityLocal test`() {
        runBlocking {
            val requestModel = AdminOfficerEntityLocal(
                officeId = "01",
                uid = UUID.randomUUID().toString(),
                name = "Jane Doe",
                email = "janedoe@example.com",
                additionalEmail = "janedoealt@example.com",
                profileImage = "http://example.com/janedoe.jpg",
                achievement = "Employee of the Month",
                phone = "0987654321",
                designations = "Manager",
                roomNo = "202"
            )
            val responseModel = AdministrationAPIs.addOfficer(requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }

    @Test
    fun `retrieve all AdminOfficerEntityLocal test`() {
        runBlocking {
            val requestModel = AdminOfficerEntityLocal(
                officeId = "01",
                uid = UUID.randomUUID().toString(),
                name = "Jane Doe",
                email = "janedoe@example.com",
                additionalEmail = "janedoealt@example.com",
                profileImage = "http://example.com/janedoe.jpg",
                achievement = "Employee of the Month",
                phone = "0987654321",
                designations = "Manager",
                roomNo = "202"
            )
            AdministrationAPIs.addOfficer(requestModel)
            val responseModel = AdministrationAPIs.retrieveOfficers("01")
            println(responseModel)
            assertTrue(responseModel.getOrDefault(emptyList()).isNotEmpty())
        }
    }
}
