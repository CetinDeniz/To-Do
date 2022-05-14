package com.axuca.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.axuca.todo.databinding.FragmentUpdateBinding
import com.axuca.todo.viewmodels.AddVM
import com.axuca.todo.viewmodels.UpdateVM
import com.axuca.todo.viewmodels.provider.VMProvider

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UpdateVM by viewModels{
        VMProvider(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: UpdateFragmentArgs by navArgs()
        binding.toDo = bundle.toDo
        binding.viewModel = viewModel

        binding.buttonUpdate.setOnClickListener {
            viewModel.update(bundle.toDo.id, binding.title.text.toString())
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}