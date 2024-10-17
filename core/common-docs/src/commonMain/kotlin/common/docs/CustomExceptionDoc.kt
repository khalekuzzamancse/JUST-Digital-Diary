@file:Suppress("UnUsed")
package common.docs
//Creating this empty just provide the common document comment to the different UseCase
/**
 *  - Represents the `data structure` that will be used to send and receive `data` to and from the `:ui` and `:data` modules
 *  - Consumer modules should not directly use this for their own purposes; for example, the `view/UI` should not use it as `viewData`,
 *   and the `:data` module should use it as an `entity` or `schema` to avoid tight coupling with this module
 *   - This `model` is for the `domain` module only, where the `domain` module is the implemented version of Clean Architecture's `application` layer
 *   - The `:ui` module should convert this `model` to a UI-friendly model via a `Presenter` before using it
 * Why it returns an exception instead of throwing:
 *- If we were to throw an exception, it could be any type of [Exception], which might be difficult to handle uniformly
 */
 class CustomExceptionDoc