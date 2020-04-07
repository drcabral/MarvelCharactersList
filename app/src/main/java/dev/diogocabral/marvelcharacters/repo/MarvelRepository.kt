package dev.diogocabral.marvelcharacters.repo

import dev.diogocabral.marvelcharacters.data.model.MarvelResponse
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getCharacters() : Single<MarvelResponse>
}