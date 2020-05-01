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

    @GET("lists/overview.json")
    fun getOverview(): Call<NytResponse<OverviewResult>>

    @GET("lists/names.json")
    fun getLists():Call<NytResponse<List<SellerListInfo>>>

    @GET("lists/current/{type}.json")
    fun getListByType(@Path("type") type:String):Call<NytResponse<ListResult>>
}