package com.omertex.test.app.domain

import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.data.model.MergeModelMapper
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetMergeListUseCase(
    private val oxTestRepository: OxTestRepository,
    private val mapper: MergeModelMapper
) :
    BaseUseCase<List<MergeModel>> {

    private var users = emptyList<User>()

    private fun mergeList(users: List<User>, photos: List<Photo>): List<MergeModel> {
        val result = mutableListOf<MergeModel>()
        for (i in 0..users.size) {
            result.add(i, mapper.map(users[i], photos[i]))
        }
        return result
    }

    private val mergeList = flow {
        when (val result = oxTestRepository.getUsers()) {
            is SingleResult.Success -> {
                users = result.data
                emit(result.data)
            }
            else -> {
                emit(emptyList())
            }
        }
    }
        .filter {
            it.isNotEmpty()
        }
        .map {
            when (val result = oxTestRepository.getPhoto(it.size)) {
                is SingleResult.Success -> result.data
                else -> emptyList()
            }
        }
        .map {
            mergeList(users, it)
        }
        .flowOn(Dispatchers.IO)


    override suspend fun execute(): SingleResult<List<MergeModel>> {
        return mergeList.map {
                SingleResult.Success(it)
            }
            .single()
    }
}