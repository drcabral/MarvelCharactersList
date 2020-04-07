package dev.diogocabral.marvelcharacters.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import dev.diogocabral.marvelcharacters.R
import dev.diogocabral.marvelcharacters.ui.adapter.MarvelCharacterListAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var marvelCharacterListAdapter: MarvelCharacterListAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpObservers()
        marvelCharacterListAdapter = MarvelCharacterListAdapter(null, requireContext())
        marvel_character_list.adapter = marvelCharacterListAdapter
        mainViewModel.fetchMarvelCharacters()
    }

    private fun setUpObservers() {
        mainViewModel.marvelCharacters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                marvelCharacterListAdapter.updateData(it.data.results)
            }
        })

        mainViewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                if (isLoading) {
                    main_loader.visibility = View.VISIBLE
                } else {
                    main_loader.visibility = View.GONE
                }
            }
        })

        mainViewModel.loadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                if(isError) {
                    Toast.makeText(context, "An error has ocurred", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
