package com.pintu.kotlin_task.view.sqllite

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pintu.kotlin_task.R
import kotlinx.android.synthetic.main.activity_sql_lite.*

class SqlLite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_lite)

    }

    fun saveRecord(view: View) {
        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()
        val databaseHandler:DatabaseHandler = DatabaseHandler(this)
        if(id.trim()!=""&&name.trim()!=""&&email.trim()!=""){
            val status = databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id),name,email))
            if(status > -1){
                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }
    }


    fun viewRecord(view: View) {

        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
            Log.d("Error","main ${e.userEmail}")

        }
        val recyclerView = findViewById(R.id.recycler_view_sql)as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SqlRVAdapter(this,emp)
        recyclerView.adapter =adapter

    }

    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView =inflater.inflate(R.layout.update_dialog,null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId)as EditText
        val edtName = dialogView.findViewById(R.id.updateName)as EditText
        val edtEmail = dialogView.findViewById(R.id.updateEmail)as EditText

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update",DialogInterface.OnClickListener { dialog, which ->

            val updateId = edtId.text.toString()
            val updateName =  edtName.text.toString()
            val updateEmail = edtEmail.text.toString()

            val databaseHandler : DatabaseHandler= DatabaseHandler(this)
            if(updateId.trim()!="" && updateName.trim()!="" && updateEmail.trim()!=""){

                val status = databaseHandler.updateEmployee(EmpModelClass(Integer.parseInt(updateId),updateName,updateEmail))

                if(status > -1){
                    Toast.makeText(applicationContext,"Record Updated",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"Id or Name or Emial Cannot be Blank",Toast.LENGTH_SHORT).show()
            }
        })

        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->  })
        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord(view: View) {

        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val diaogView = inflater.inflate(R.layout.delete_dialog,null)
        dialogBuilder.setView(diaogView)

        val delId = diaogView.findViewById(R.id.deleteId)as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete",DialogInterface.OnClickListener { dialog, which ->

            val deleteId = delId.text.toString()

            val databaseHandler : DatabaseHandler= DatabaseHandler(this)
            if(deleteId.trim()!=""){

                val status = databaseHandler.deleteEmployee(EmpModelClass(Integer.parseInt(deleteId),"",""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record Deleted",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(applicationContext,"Id or Name or Emial Cannot be Blank",Toast.LENGTH_SHORT).show()
            }
        })
        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->  })
        val b = dialogBuilder.create()
        b.show()
    }

}