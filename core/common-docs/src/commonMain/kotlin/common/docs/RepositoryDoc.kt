@file:Suppress("UnUsed")
package common.docs
//Creating this empty just provide the common document comment to the different UseCase
/**
 * - This interface should be implemented by the `:data` module, which corresponds to the `infrastructure` layer in Clean Architecture
 * - It should only be used by the `Use Cases` defined within the `:domain` module
 * - The `:ui` module should not directly interact with this interface. Instead, the `:ui` module should interact with the `Use Cases`
 * - For each operation defined in this interface, there should be a corresponding `Use Case` that delegates the operation
 *   to the appropriate method in this interface
 * - The concrete implementations of this interface are allowed to directly communicate with backend services or databases
 *   However, they are not responsible for validating or verifying the data
 *   Validation and verification should be performed via `Services` before the operation is delegated to this implementation
 * - This Interface `instance` should be provided via constructor injection in the `UseCase`, allowing for
 * easy switching between automatic and manual `dependency injection`, and vice versa
 */
 class RepositoryDoc