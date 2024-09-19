package domain.docs

//Creating this empty just provide the common document comment to the different UseCase
/**
 * --- Understanding  Services According to Clean Architecture principles
 * - [UseCaseDoc] should  not responsible for input validation
 * - Before invoking a [UseCaseDoc]  the `consumer` must validate the input , which is the responsibility of the services
 * - Typically Services contain methods for performing specific validation tasks, such as verifying data integrity
 * - In the `:domain`, only the interfaces for these services are defined, while the implementation is handled in the 'data` module
 * - The services are defined as interfaces because certain validations, such as authenticity checks, may require database access,
 *   which will be handled in the implementation by the data layer
 *
 * Who should use it:
 * - The `Controller` in the `UI` layer should invoke the service to validate data before passing it to the use case
 * - Any `consumer` of the `UseCase` should use the services to ensure that the input  is valid before executing the use case
 */
internal class ServiceDoc