@file:Suppress("UnUsed")
package schedule.presentationlogic.model

/**
 * - Defined for type safety. If we used a string instead, it could be empty or blank,
 * which might cause rendering issues. This is because the text size is measured before
 * being laid out, and an empty or blank string could result in a size of 0 or an unexpected size.
 *
 * - In cases like editing, such as when adding a new schedule, the string could be empty.
 * This is why type safety is used to prevent such issues.
 */
enum class DayOfWeekModel {
    SAT {
        override fun toString() = "Sat"
    },
    SUN {
        override fun toString() = "Sun"
    },
    MON {
        override fun toString() = "Mon"
    },
    TUE {
        override fun toString() = "Tue"
    },
    WED {
        override fun toString() = "Wed"
    },
    THU {
        override fun toString() = "Thu"
    },
    FRI {
        override fun toString() = "Fri"
    };

    abstract override fun toString(): String
}
