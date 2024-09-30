package miscellaneous.domain.usecase

import miscellaneous.domain.repoisitory.Repository

class GetVCInfoUseCase(
    private val repository:Repository
) {
    suspend fun execute()=repository.getVCInfo()
}