package dev.diogocabral.marvelcharacters.di

import dev.diogocabral.marvelcharacters.BuildConfig
import dev.diogocabral.marvelcharacters.data.rest.MarvelService
import dev.diogocabral.marvelcharacters.repo.MarvelRepository
import dev.diogocabral.marvelcharacters.repo.MarvelRepositoryApi
import dev.diogocabral.marvelcharacters.ui.main.MainViewModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var marvelModule = module {

    viewModel { MainViewModel(get()) }

    factory { provideMarvelService(get()) }
    single { provideRemoteDataSource(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.MARVEL_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}

fun provideRemoteDataSource(marvelService: MarvelService): MarvelRepository {
    return MarvelRepositoryApi(marvelService)
}

fun provideMarvelService(retrofit: Retrofit): MarvelService {
    return retrofit.create(MarvelService::class.java)
}