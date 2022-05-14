package com.axuca.todo.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.axuca.todo.R
import com.axuca.todo.adapter.ToDoAdapter
import com.axuca.todo.databinding.FragmentHomeBinding
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.axuca.todo.util.generateData
import com.axuca.todo.viewmodels.HomeVM
import com.axuca.todo.viewmodels.provider.VMProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeVM by viewModels {
        VMProvider(requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    /**
     * This is the appropriate place to set up the initial state of your view,
     * to start observing LiveData instances whose callbacks update the fragment's view,
     * and to set up adapters on any RecyclerView or ViewPager2 instances in your fragment's view.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ToDoAdapter(requireContext(),viewModel)
        adapter.submitList(viewModel.toDos.value)
        binding.recycler.adapter = adapter

        binding.fabButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel.toDos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar, menu)

        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                viewModel.searchToDos(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                viewModel.searchToDos(p0)
                return true
            }
        })

//        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}