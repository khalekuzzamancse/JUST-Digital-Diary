package domain.model

/**
 * @property holiday must list of size=12 , where index=0 represents holiday of january and so on so forth
 * if a month has no holiday assign an empty list
 */
data class AcademicCalender2(
    val year: Int,
    val holiday: List<List<Holiday>>
)
