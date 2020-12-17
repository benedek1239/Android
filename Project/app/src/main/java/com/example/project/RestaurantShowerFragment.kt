package com.example.project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.RestaurantShowerFragmentBinding
import androidx.lifecycle.ViewModelProviders.of
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.project.PhotoGridAdapter

class RestaurantShowerFragment : Fragment() {


    private val viewModel: RestaurantShowerViewModel by lazy {
        ViewModelProvider(this).get(RestaurantShowerViewModel::class.java)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = RestaurantShowerFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if(null != it){
                this.findNavController().navigate(RestaurantShowerFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        //Vissza gomb deklarálása és listenerhez csatolása
        val backBTN = binding.root.findViewById<Button>(R.id.backButtonRestaurants)
        backBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_restaurantFragment_to_mainMenuFragment)
        }


        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = of(this).get(RestaurantShowerViewModel::class.java)

    }

}