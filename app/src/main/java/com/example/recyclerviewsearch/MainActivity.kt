package com.example.recyclerviewsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CityViewModel by viewModels()
    private val cityAdapter: CityListAdapter by lazy {
        CityListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observer()
        setupView()
    }

    private fun setupView() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                cityAdapter.cityFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityAdapter.cityFilter().filter(newText)
                return true
            }

        })

        with(binding.cityList) {
            val itemDecoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addItemDecoration(itemDecoration)
            adapter = cityAdapter
        }
    }

    private fun observer() {
        viewModel.cityLiveData.observe(this) {
            cityAdapter.setupCityList(it)
        }
    }
}