package com.lain.beerapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.repository.BEER_START_PAGE
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.network.repository.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

class BeerDataSource (private val beerApiRepository: BeerApiRepository) : PagingSource<Int, Beer>(){

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { _anchorPosition ->
            state.closestPageToPosition(anchorPosition = _anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition = _anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {

        val position = params.key ?: BEER_START_PAGE
        return try {
            val beers = beerApiRepository.findAll(page = position)

            val nextKey = if(beers.isEmpty()){
                null
            }else{
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = beers,
                prevKey = if(position == BEER_START_PAGE) null else position - 1,
                nextKey = nextKey
            )

        } catch (exception : IOException){
            return LoadResult.Error(exception)
        } catch (exception : HttpException){
            return LoadResult.Error(exception)
        }

    }


}