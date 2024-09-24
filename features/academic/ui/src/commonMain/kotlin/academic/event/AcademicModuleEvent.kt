package academic.event

/**
 * - Defines the events that should be exposed from this module to the consumer module
 * - If some events are internal, mark them as `internal`
 * - In the future, if new events need to be added, simply add them here to maintain the `single source` of truth` for events
 */
sealed interface AcademicModuleEvent {
}