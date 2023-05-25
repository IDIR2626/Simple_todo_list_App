package com.example.todo
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class TodoAdapter (
    private val todo_list: MutableList<Todo>
        ) : RecyclerView.Adapter<TodoAdapter.ViewHolder>()
{


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val todoTitle: TextView = itemView.findViewById(R.id.todoTitle)
        val todoCheckbox: CheckBox = itemView.findViewById(R.id.todoCheckbox)
    }

     fun addTodo(todo: Todo){
        todo_list.add(todo)
        notifyItemInserted(todo_list.size - 1)
    }

     fun deleteCheckedTodos() {
        todo_list.removeAll { it.isChecked }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.layout_items, parent, false)
        return ViewHolder(view)
    }

    private fun strickTrough(todoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            todoTitle.paintFlags = todoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            todoTitle.paintFlags = todoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = todo_list[position]
        holder.apply{

            todoTitle.text = currentTodo.title
            todoCheckbox.isChecked = currentTodo.isChecked
            strickTrough(todoTitle, todoCheckbox.isChecked)
            todoCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
                strickTrough(todoTitle, isChecked)
                currentTodo.isChecked = isChecked
            }

        }
    }

    override fun getItemCount(): Int {
        return todo_list.size
    }
}