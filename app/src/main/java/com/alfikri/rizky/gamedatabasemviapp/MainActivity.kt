package com.alfikri.rizky.gamedatabasemviapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfikri.rizky.gamedatabasemviapp.data.datasource.NetworkRepositoryImpl
import com.alfikri.rizky.gamedatabasemviapp.data.network.RetrofitService
import com.alfikri.rizky.gamedatabasemviapp.databinding.ActivityMainBinding
import com.alfikri.rizky.gamedatabasemviapp.intent.MainIntent
import com.alfikri.rizky.gamedatabasemviapp.state.MainState
import com.alfikri.rizky.gamedatabasemviapp.ui.GamesAdapter
import com.alfikri.rizky.gamedatabasemviapp.ui.MainViewModel
import com.alfikri.rizky.gamedatabasemviapp.utils.ViewModelFactory
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val gamesAdapter: GamesAdapter by lazy { GamesAdapter() }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initViewModel()
        loadGameData()
        observerTextInput()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun initView() {
        binding.rvGame.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            adapter = gamesAdapter
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(NetworkRepositoryImpl(RetrofitService.apiService))
        )[MainViewModel::class.java]
    }

    private fun observerTextInput() {
        disposable =
            RxTextView.textChanges(binding.edtSearchGame).throttleLast(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    lifecycleScope.launch {
                        mainViewModel.queryGameIntent.send(MainIntent.FetchGames(it.toString()))
                    }
                }
    }

    private fun loadGameData() {
        lifecycleScope.launch {
            mainViewModel.queryGameState.collect {
                when (it) {
                    is MainState.Idle -> {
                        binding.run {
                            tvIdleInfo.text = getString(R.string.let_s_search_your_favorite_game)
                            showAndHideView(tvIdleInfo, rvGame, progressBar)
                        }
                    }

                    is MainState.Loading -> {
                        binding.run {
                            showAndHideView(progressBar, tvIdleInfo, rvGame)
                        }
                    }

                    is MainState.Empty -> {
                        binding.run {
                            tvIdleInfo.text = getString(R.string.game_not_found)
                            showAndHideView(tvIdleInfo, rvGame, progressBar)
                        }
                    }

                    is MainState.Failed -> {
                        binding.run {
                            tvIdleInfo.text = it.errorMessage
                            showAndHideView(tvIdleInfo, rvGame, progressBar)
                        }
                    }

                    is MainState.Success -> {
                        binding.run {
                            showAndHideView(rvGame, tvIdleInfo, progressBar)
                            gamesAdapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun showAndHideView(showedView: View, vararg hiddenView: View) {
        showedView.isVisible = true
        hiddenView.onEach { it.isVisible = false }
    }
}