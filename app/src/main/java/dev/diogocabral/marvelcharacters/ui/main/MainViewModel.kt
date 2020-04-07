package dev.diogocabral.marvelcharacters.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.diogocabral.marvelcharacters.data.model.MarvelResponse
import dev.diogocabral.marvelcharacters.repo.MarvelRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val marvelRepository: MarvelRepository) : ViewModel() {

    private var disposable: CompositeDisposable? = CompositeDisposable()

    val marvelCharacters: MutableLiveData<MarvelResponse> = MutableLiveData<MarvelResponse>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun fetchMarvelCharacters() {
        loading.value = true
        disposable?.add(marvelRepository.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<MarvelResponse>() {
                override fun onSuccess(value: MarvelResponse?) {
                    loadError.value = false
                    marvelCharacters.value = value
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    loadError.value = true
                    loading.value = false
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.clear()
        disposable = null
    }
}
