package com.moor.shelflyfe.api.nyt

import com.moor.shelflyfe.BuildConfig
import com.moor.shelflyfe.api.nyt.models.ListResult
import com.moor.shelflyfe.api.nyt.models.NytResponse
import com.moor.shelflyfe.api.nyt.models.OverviewResult
import com.moor.shelflyfe.api.nyt.models.SellerListInfo
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path


interface NytService {

    companion object{
        val baseUrl="https://api.nytimes.com/svc/books/v3/"
    }

    @GET("lists/overview.json")
    suspend fun getOverview(): NytResponse<OverviewResult>

    @GET("lists/names.json")
    suspend fun getLists():NytResponse<List<SellerListInfo>>

    @GET("lists/current/{type}.json")
    suspend fun getListByType(@Path("type") type:String):NytResponse<ListResult>
}