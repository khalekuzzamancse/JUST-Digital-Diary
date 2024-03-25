package administration.data

@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "This is intended for package-level use only and should not be used outside of its defining package.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class PackageLevelAccess
