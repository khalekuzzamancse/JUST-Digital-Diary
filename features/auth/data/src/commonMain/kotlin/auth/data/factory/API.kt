package auth.data.factory

internal object API {
    private const val BASE_URL = "https://backend.rnzgoldenventure.com"

    const val REGISTER= "$BASE_URL/api/users/register"
    const val ACCOUNT_VERIFY="$BASE_URL/api/users/confirm-account"
}