package auth.data

@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "Only to be used in package")
@Retention(AnnotationRetention.BINARY)
annotation class PackageLevelAccess