package com.example.recyclerviewsearch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsearch.databinding.ItemCityBinding
import java.util.*

class CityListAdapter : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    private val cityList: MutableList<CityData> = mutableListOf()
    private val cacheCityList: MutableList<CityData> = mutableListOf()

    fun setupCityList(list: List<CityData>) {
        cityList.clear()
        cacheCityList.clear()
        cityList.addAll(list)
        cacheCityList.addAll(list)
        notifyDataSetChanged()
    }

    fun cityFilter(): Filter =
        object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterList = mutableListOf<CityData>()
                val filterResult = FilterResults()
                if (constraint.isNullOrBlank()) {
                    filterList.addAll(cacheCityList)
                } else {
                    val query = constraint.toString().toLowerCase(Locale.ROOT)
                    filterList.addAll(cacheCityList.filter {
                        it.cityName.toLowerCase(Locale.ROOT).contains(query)
                    })
                }

                filterResult.values = filterList
                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values is MutableList<*>) {
                    cityList.clear()
                    cityList.addAll(results.values as MutableList<CityData>)
                    notifyDataSetChanged()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindView(cityList[position])
    }

    override fun getItemCount(): Int = cityList.size

    class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(cityData: CityData) {
            binding.tvCityCode.text = cityData.cityCode
            binding.tvCityName.text = cityData.cityName
        }
    }
}