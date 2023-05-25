package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn : Button
    private lateinit var deletBtn : Button
    private lateinit var editText: EditText
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recylerView)
        addBtn = findViewById(R.id.addBtn)
        deletBtn = findViewById(R.id.deleteBtn)
        editText = findViewById(R.id.editTxt)


        adapter = TodoAdapter(mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        addBtn.setOnClickListener{
            var todoTitle = editText.text.toString()
            var isChecke = false
            var todo = Todo(todoTitle, isChecke)
            if (todoTitle.isEmpty()){
                Toast.makeText(this, "Please set todo's tile", Toast.LENGTH_LONG).show()
            }else {
                adapter.addTodo(todo)
                editText.text.clear()
            }
        }

        deletBtn.setOnClickListener{
            adapter.deleteCheckedTodos()
        }





    }
}