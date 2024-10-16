package domain.core

/**
 * These field are not present in write or read entities because these entities format are known to and used the client
 * - In some case we need to keep some extra information with the entity so that later we can query something or
 * do  some operations but we should not expose to them in client(either read or write entities)
 */
object EntityExtraField {
    /**
     * This field (column in relation database) should present for both class and exam schedule
     */
    const val SCHEDULE_ENTITY_FIELD_DEPT_ID="dept_id"
}