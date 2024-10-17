package common.docs

/**
 * # Naming Conventions for Controller
 *
 * The **Controller** should be framework-independent and not tightly coupled to any specific UI framework or library.
 * This ensures that the controller can be reused and adapted across various UI platforms without modification.
 *
 * ## Guidelines:
 *
 * 1. **Avoid UI-specific terminology**:
 *    - The controller should avoid terms tied to a specific UI framework, such as "Toast", "Snackbar", or "Dialog".
 *    - Instead, use neutral terms that describe the purpose rather than the implementation.
 *      - **Good Example**: Use `screenMessage` or `errorMessage` to describe messages shown on the screen.
 *      - **Bad Example**: Avoid terms like `toastMessage` or `snackbarMessage`.
 *
 * 2. **Generic Loading State**:
 *    - Avoid specific loading-related terms tied to UI implementations like progress bars or loading spinners.
 *      - **Good Example**: Use `isFetching` to describe a loading state.
 *      - **Bad Example**: Avoid terms like `isLoading`, which may imply a specific visual representation in the UI.
 *
 * 3. **Framework-agnostic Terms**:
 *    - The controller should not make assumptions about how the data will be displayed. Keep the naming neutral so that it can support various UI layers (e.g., Android, web, or desktop).
 *      - **Good Example**: `dataReceived`, `onError`, `screenMessage`.
 *      - **Bad Example**: `showToast`, `displaySnackbar`, `openDialog`.
 *
 * 4. **Consistency and Clarity**:
 *    - Ensure naming conventions across controllers follow these principles to maintain consistency and clarity within the project, allowing the UI logic to evolve independently from the controller.
 *
 * ## Note on Validators:
 *
 * - **Validator as a Nested Interface**:
 *   - Declaring a **validator** as a separate nested interface within the controller keeps the validation logic decoupled from the controller itself. This forces the implementer to provide a dedicated implementation for validation, ensuring **single responsibility** and **separation of concerns**.
 *   - This design allows the validation logic to be updated or replaced independently of the controller. If there is a need to change the validation rules or implement a new validation strategy, the controller remains untouched, adhering to the **Open-Closed Principle**.
 *   - By defining the validator interface within the controller, it also makes it clear which **fields** or data within the controller need validation. This keeps the contract for validation well-defined and limits the scope of what the validator is responsible for.
 *
 * ## Why ViewModel is not a Controller:
 *
 * - **ViewModel is Framework-dependent**:
 *   - The **ViewModel** is a part of the Android framework and is tightly coupled to the lifecycle of Android components, especially for preserving state during configuration changes (e.g., screen rotations). Since **ViewModel** is framework-dependent, it violates the principle of keeping the **interface-presenter layer** framework- and UI-independent. This is why **ViewModel** should **never** be treated as a **Controller** in Clean Architecture.
 *
 * - **ViewModel's Role in Preserving UI State**:
 *   - The primary role of a **ViewModel** is to preserve UI state across configuration changes and manage the data for the UI. It converts state, events, or messages from the **Controller** into UI-friendly formats, such as updating LiveData for views to observe.
 *   - The **ViewModel** should act as a middle layer that handles how **UI state** is maintained or updated, but the actual **business logic** should still reside in the **Controller**.
 *
 * - **ViewModel Creation in Interface-Adapter Layer**:
 *   - The **ViewModel** instance should be created in the **interface-adapter layer** (i.e., in the `activity` or `fragment`), where it can interact with both the UI and **Controller/Presenter** layers. This keeps the **Controller-Presenter** layer free of framework-specific components.
 *
 * - **ViewModel in the UI Layer**:
 *   - Since the **ViewModel** is a framework-dependent class, it should be part of the **UI layer** (e.g., in the `ui` package). It should not be created or managed in the **controller-presenter layer** or its factories.
 *   - The **ViewModel** can hold and manage multiple **Controller** instances, facilitating communication between the UI and the **Controllers**, but it should never replace the **Controller's** responsibilities.
 *
 */

interface ControllerDocs
