package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemSeriousCrimeBinding



// TODO Define CrimeHolder ViewHolder
class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
// TODO Define SeriousCrimeHolder ViewHolder
class SeriousCrimeHolder(
    private val binding: ListItemSeriousCrimeBinding        // TODO Replaced old binding with binding for list_item_serious_crime.xml layout
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

/* Tutorial I followed: https://blog.devgenius.io/recyclerview-with-multiple-views-in-kotlin-bffe299c1994
*   Honorable mentions that were helpful as well:
*   https://stackoverflow.com/questions/72984762/recyclerview-card-change-size-randomly-while-scrolling/72994172#72994172
*   https://stackoverflow.com/questions/71123468/recyclerview-with-multiple-viewholder-types */


// TODO: Create constants to store viewType (takes an int)
private const val TYPE_REGULAR_CRIME: Int = 0
private const val TYPE_SERIOUS_CRIME: Int = 1


class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // TODO: create binding variables for each layout file
    private lateinit var bindingCrime: ListItemCrimeBinding
    private lateinit var bindingSeriousCrime: ListItemSeriousCrimeBinding


    // TODO: Override getItemViewType() and return an integer based on the type of viewType (crime)
    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) TYPE_SERIOUS_CRIME else TYPE_REGULAR_CRIME
    }

    /* TODO: Edit onCreateViewHolder() to where it takes a generic ViewHolder, which sets the binding variable depending on the viewType.
    *   When the viewType is TYPE_REGULAR_CRIME, inflate and bind ListItemCrimeBinding
    *       and pass the resulting bindingCrime to a new instance of CrimeHolder
    *   When the viewType is TYPE_SERIOUS_CRIME, inflate and bind ListItemSeriousCrimeBinding
    *       and pass the resulting bindingSeriousCrime to a new instance of SeriousCrimeHolder*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_REGULAR_CRIME -> {
                bindingCrime =  ListItemCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CrimeHolder(bindingCrime)
            }
        else -> {
            bindingSeriousCrime =  ListItemSeriousCrimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            SeriousCrimeHolder(bindingSeriousCrime)
        }
        }

    /* TODO: Edit onBindViewHolder() to where it takes a generic ViewHolder called holder,
            set holder as CrimeHolder or SeriousCrimeHolder based on if the crime requiresPolice,
            and use .bind to bind the crime to either SeriousCrimeHolder or CrimeHolder*/
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(crimes[position].requiresPolice){
            (holder as SeriousCrimeHolder).bind(crimes[position])
            }
        else {
            (holder as CrimeHolder).bind(crimes[position])
        }
    }

    override fun getItemCount() = crimes.size
}
