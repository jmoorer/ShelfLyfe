package com.moor.shelflyfe.api.openlib

import com.moor.shelflyfe.api.openlib.models.DataResponse
import com.moor.shelflyfe.api.openlib.models.DetailResponse
import com.moor.shelflyfe.api.openlib.models.subject.SubjectResponse
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenLibService {
    companion object{
        val baseUrl="https://openlibrary.org/"
    }


    @GET("api/books?&format=json")
    suspend fun  getBookByIsbn(@Query("bibkeys")keys:String):HashMap<String,Any>

    @GET("api/books?jscmd=details&format=json")
    suspend fun  getBookDetailsByIsbn(@Query("bibkeys")keys:String):DetailResponse

    @GET("api/books?jscmd=data&format=json")
    suspend fun  getBookDataByIsbn(@Query("bibkeys")keys:String):DataResponse


    @GET("subjects/{subject}.json?details=true")
    suspend fun  getSubjectDetails(@Path("subject")subject:String): SubjectResponse
}