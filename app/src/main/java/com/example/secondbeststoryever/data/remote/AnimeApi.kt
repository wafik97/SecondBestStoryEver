package com.example.secondbeststoryever.data.remote

//import com.example.secondbeststoryever.data.dto.CharacterResponse
import com.example.secondbeststoryever.data.dto.CharactersInfoResponse
import com.example.secondbeststoryever.data.dto.CharactersResponse
import com.example.secondbeststoryever.data.dto.MangaInfoResponse
import com.example.secondbeststoryever.data.dto.MangaPicturesResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface AnimeApi {


    @GET("manga/{id}/characters")
    suspend fun getMangaCharacters( @Path("id") mangaId: Int ): CharactersResponse

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") characterId: Int): CharactersInfoResponse

    @GET("manga/{id}")
    suspend fun getMangaInfo( @Path("id") mangaId: Int): MangaInfoResponse


    @GET("manga/{id}/pictures")
    suspend fun getMangaPictures(@Path("id") mangaId: Int): MangaPicturesResponse


}