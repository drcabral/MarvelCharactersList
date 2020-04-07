package dev.diogocabral.marvelcharacters.repo

import dev.diogocabral.marvelcharacters.BuildConfig
import dev.diogocabral.marvelcharacters.data.model.MarvelResponse
import dev.diogocabral.marvelcharacters.data.rest.MarvelService
import dev.diogocabral.marvelcharacters.extensions.toMD5
import io.reactivex.rxjava3.core.Single
import java.util.*

class MarvelRepositoryApi(private val marvelService: MarvelService) : MarvelRepository {

    private val charactersLimit = 100
    private val avengersEvent = 271

    override fun getCharacters(): Single<MarvelResponse> {
        val timeStamp = Calendar.getInstance().timeInMillis.toString()
        val marvelPublicKey = BuildConfig.MARVEL_API_KEY
        val marvelPrivateKey = BuildConfig.MARVEL_API_KEY_PRIVATE

        val hash = (timeStamp + marvelPrivateKey + marvelPublicKey).toMD5()
        return marvelService.getCharacters(
            BuildConfig.MARVEL_API_KEY,
            hash,
            timeStamp,
            charactersLimit,
            avengersEvent)
    }
}