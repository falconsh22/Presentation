package com.shahin.presentation.data.sources.remote.artists.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.shahin.presentation.data.models.local.ArtistEntity
import com.shahin.presentation.data.models.local.RemoteKey
import com.shahin.presentation.data.sources.local.LocalRepository
import com.shahin.presentation.network.NetworkResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArtistsPagingRepository @Inject constructor(
    private val artistsDataSource: ArtistsDataSource,
    private val localRepository: LocalRepository,
    private val query: String,
    private val onError: (String?) -> Unit
) : RemoteMediator<Int, ArtistEntity>() {

    private val startPage = 0
    private val limit = 40

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ArtistEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = localRepository.remoteKeyByQuery(query)
                Log.e("ARTISTS", "------------------------> REFRESH: ${remoteKey?.nextKey?.minus(1) ?: startPage}")
                remoteKey?.nextKey?.minus(1) ?: startPage
            }
            LoadType.PREPEND -> {
                val remoteKey = localRepository.remoteKeyByQuery(query)
                if (remoteKey?.prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
                Log.e("ARTISTS", "------------------------> PREPEND: ${remoteKey.prevKey}")
                remoteKey.prevKey
            }
            LoadType.APPEND -> {
                val remoteKey = localRepository.remoteKeyByQuery(query)
                if (remoteKey?.nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                Log.e("ARTISTS", "------------------------> APPEND: ${remoteKey}")
                remoteKey.nextKey
            }
        }
        Log.w("ARTISTS", "------------------------> PAGE: ${page}")
        return try {
            when (val response = artistsDataSource.searchArtists(query, page * limit, limit)) {
                is NetworkResponse.Success -> {
                    val body = response.data ?: run {
                        onError.invoke("No Data Found")
                        return MediatorResult.Error(Throwable("No Data Found"))
                    }
                    val artists = response.data.data
                    val totalResults = body.total
                    val previousKey = if (page == startPage) null else page - 1
                    val nextKey = if (artists.isEmpty() || page * limit > totalResults) null else page + 1
                    localRepository.clearRemoteKeys()
                    localRepository.insertOrReplaceRemoteKey(RemoteKey(label = query, prevKey = previousKey, nextKey = nextKey))
                    Log.d("ARTISTS", "------------------------> NEW REMOTE KEY: ${RemoteKey(label = query, prevKey = previousKey, nextKey = nextKey)}")
                    localRepository.insertArtists(artists = artists, query = query)
                    MediatorResult.Success(endOfPaginationReached = artists.isEmpty() || page * limit > totalResults)
                }

                is NetworkResponse.GenericError -> {
                    onError.invoke(response.error.message)
                    MediatorResult.Error(Throwable(response.error.message))
                }

                is NetworkResponse.NetworkError -> {
                    onError.invoke("Network Error")
                    MediatorResult.Error(Throwable("Network Error"))
                }
            }
        } catch (exception: IOException) {
            onError.invoke("Network Error")
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            onError.invoke("Network Error")
            MediatorResult.Error(exception)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

}
