package com.omertex.test.app.domain

import com.omertex.test.app.core.datatype.SingleResult
import com.omertex.test.app.data.model.MergeModel
import com.omertex.test.app.data.model.MergeModelMapper
import com.omertex.test.app.data.model.placeholder.User
import com.omertex.test.app.data.model.unsplash.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception

class GetMergeListUseCase(
    private val oxTestRepository: OxTestRepository,
    private val mapper: MergeModelMapper
) :
    BaseUseCase<SingleResult<List<MergeModel>>> {

    private suspend fun users(): List<User> = when (val result = oxTestRepository.getUsers()) {
        is SingleResult.Success -> result.data
        else -> emptyList()
    }

    private fun mergeList(users: List<User>, photos: List<Photo>): SingleResult<List<MergeModel>> {
        val mergeList = mutableListOf<MergeModel>()
        return if (users.isNotEmpty() && photos.isNotEmpty()) {
            val size = users.size - 1
            for (i in 0..size) {
                mergeList.add(i, mapper.map(users[i], photos[i]))
            }
            SingleResult.Success(mergeList)
        } else {
            SingleResult.Error(Exception("some lis empty"))
        }

    }

    private val mergeList = flow {
        emit(users())
    }
        .map {
            when (val result = oxTestRepository.getPhoto(it.size)) {
                is SingleResult.Success -> mergeList(it,result.data)
                else -> SingleResult.Error(Exception("Photo lis empty"))
            }
        }
        .flowOn(Dispatchers.IO)


    override suspend fun execute(): Flow<SingleResult<List<MergeModel>>> {
        return mergeList
    }
}