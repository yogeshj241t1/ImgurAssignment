package com.imgur.assignment.ui.topimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.imgur.assignment.ImgurApplication
import com.imgur.assignment.data.model.Data
import com.imgur.assignment.databinding.ActivityTopWeeklyImagesBinding
import com.imgur.assignment.di.component.DaggerActivityComponent
import com.imgur.assignment.di.module.ActivityModule
import com.imgur.assignment.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopWeeklyImagesActivity : AppCompatActivity() {

    @Inject
    lateinit var topWeeklyImagesViewModel: TopWeeklyImagesViewModel

    @Inject
    lateinit var topSearchImageViewModel: TopSearchImageViewModel

    @Inject
    lateinit var adapter: TopWeeklyImagesAdapter

    private lateinit var binding: ActivityTopWeeklyImagesBinding

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private var isGridView = false


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopWeeklyImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObserver()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topWeeklyImagesViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.e("Data Act", Gson().toJson(it.data))
                            renderList(it.data)
                            binding.linearView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.linearView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@TopWeeklyImagesActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }


    private fun renderList(data: List<Data>) {
        adapter.addData(data)
        adapter.notifyDataSetChanged()
    }

    private fun setUpUI() {

        binding.ivSearch.setOnClickListener {
            if (binding.etSearch.text?.isEmpty() == true) {
                Toast.makeText(this, "Enter text to search", Toast.LENGTH_SHORT).show()
            } else {


                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {

                        binding.recyclerView.removeAllViewsInLayout()
                        topSearchImageViewModel.fetchTopWeeklyImages(binding.etSearch.text)

                        topSearchImageViewModel.uiState.collect {
                            when (it) {
                                is UiState.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    Log.e("Data Act", Gson().toJson(it.data))
                                    renderList(it.data)
                                    binding.linearView.visibility = View.VISIBLE
                                }

                                is UiState.Loading -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                    binding.linearView.visibility = View.GONE
                                }

                                is UiState.Error -> {
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(
                                        this@TopWeeklyImagesActivity,
                                        it.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }

        binding.tvListType.setOnClickListener {

            // Update the isGridView boolean flag based on its current state
            isGridView = !isGridView

            // Update the text on the "tvListType" TextView based on the new view type
            binding.tvListType.text = if (isGridView) "Grid" else "List"

            // Update the layout manager based on the new view type (implementation dependent)
            updateLayoutManager()
        }

        val recyclerView = binding.recyclerView

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as ImgurApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    private fun updateLayoutManager() {
        layoutManager = if (isGridView) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
        binding.recyclerView.layoutManager = layoutManager
    }

}