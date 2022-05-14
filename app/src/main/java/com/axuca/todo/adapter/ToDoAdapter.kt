package com.axuca.todo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axuca.todo.databinding.ListItemBinding
import com.axuca.todo.model.ToDo
import com.axuca.todo.view.HomeFragmentDirections
import com.axuca.todo.viewmodels.HomeVM
import com.google.android.material.snackbar.Snackbar

class ToDoAdapter(private val mContext: Context, private val viewModel: HomeVM) :
    ListAdapter<ToDo, ToDoAdapter.ToDoViewHolder>(
        DiffUtilCallBack
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ToDoViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDo: ToDo) {
            binding.toDo = toDo
            binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(toDo)
                Navigation.findNavController(it).navigate(action)
            }

            binding.deleteTodo.setOnClickListener {
                Snackbar.make(it, "Delete '${toDo.title}' ?", Snackbar.LENGTH_LONG)
                    .setAction("Yes") {
                        viewModel.deleteToDo(toDo)
                    }
                    .show()
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem == newItem
        }
    }

}