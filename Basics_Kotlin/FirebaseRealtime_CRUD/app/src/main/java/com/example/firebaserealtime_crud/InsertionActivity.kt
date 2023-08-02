package com.example.firebaserealtime_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaserealtime_crud.databinding.ActivityInsertionBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: ActivityInsertionBinding

class InsertionActivity : AppCompatActivity() {

    //Bằng cách khai báo dbRef với kiểu dữ liệu DatabaseReference,
    // bạn có thể sử dụng nó để đọc hoặc ghi từ vị trí tương ứng trong Realtime Database.
    lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        //khi an Save
        binding.btnSave.setOnClickListener {
            saveEmployeeData()
        }
    }

    private fun saveEmployeeData() {
        //get values data
        val empName = binding.edtEmpName.text.toString()
        val empAge = binding.edtEmpAge.text.toString()
        val empSalary = binding.edtEmpSalary.text.toString()

        //day du lieu
        //Trong Kotlin:  !! được sử dụng để khẳng định giá trị trả về sẽ không bị null
        // nếu bị null thì tóan tử !! sẽ ném ra 1 exception  NullPointerException.
        val empId = dbRef.push().key!!

        val employee = EmployeeModel(empId, empName, empAge, empSalary)

        //kiem tra dieu kien xem da co du lieu hay chua
        if(empName.isEmpty()){
            binding.edtEmpName.error = "Please enter name"
            return
        }
        if(empAge.isEmpty()){
            binding.edtEmpAge.error = "Please enter age"
            return
        }
        if(empSalary.isEmpty()){
            binding.edtEmpSalary.error = "Please enter salary"
            return
        }


        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data insert completed!", Toast.LENGTH_SHORT ).show()
                binding.edtEmpName.setText("")
                binding.edtEmpAge.setText("")
                binding.edtEmpSalary.setText("")
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error message ${err.message}", Toast.LENGTH_SHORT).show()
            }

    }
}