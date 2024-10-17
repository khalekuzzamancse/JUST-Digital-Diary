package common.docs

//Creating this empty just provide the common document comment to the different UseCase
/**
 * --- Understanding the Use Case--
 * - By definition Use cases expect pre-validated input and do not handle validation themselves, but they may perform authorization checks
 * - It ensures separation of concerns by encapsulating one specific operation
 * - They encapsulate business logic and prevent the `UI` or `Controller` from interacting directly with the repository
 * - It can implemented as  `command pattern`, to provides additional flexibility such as the ability(ex: undo/redo)
 * - Mapping 'Repository's`  one operation to a `UseCase` to prevent `:ui` `Controller ` to access the repository ensuring the
 * The `controller` does not have access to operations that it does not need
- Should Designed to allow constructor-based dependency injection, enabling easy switching between automatic and manual injection
-Instances should be created by DI frameworks or in the DI module via a `factory method `to ensure a single source of truth
- The `factory method` will  simplifies swapping implementations without exposing the repository or injected dependencies to the `consumer`
 */
 class UseCaseDoc