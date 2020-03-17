package com.omertex.test.app.domain

import com.omertex.test.app.core.datatype.SingleResult

class GetMergeListUseCase(private val oxTestRepository: OxTestRepository) : BaseUseCase<List<String>> {
    override suspend fun execute(): SingleResult<List<String>> {
        TODO("Not yet implemented")
    }
}