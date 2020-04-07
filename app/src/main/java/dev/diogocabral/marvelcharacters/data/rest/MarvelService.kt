package dev.diogocabral.marvelcharacters.data.rest

import dev.diogocabral.marvelcharacters.data.model.MarvelResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") timeStamp: String,
        @Query("limit") limit: Int,
        @Query("events") events: Int): Single<MarvelResponse>
}