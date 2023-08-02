package com.example.firebaserealtime_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firebaserealtime_crud.databinding.ActivityEmployeeDetailsBinding
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: ActivityEmployeeDetailsBinding
class EmployeeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showValueToView()

        //btnDelete
        binding.btnDelete.setOnClickListener {
            deleteRecord(intent.getStringExtra("empId").toString())
        }

        //btnUpdate
        binding.btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empName").toString()
            )
        }
    }

    private fun openUpdateDialog(id: String, name: String) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        //update thong tin
        val edtName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val edtAge = mDialogView.findViewById<EditText>(R.id.etEmpAge)
        val edtSalary = mDialogView.findViewById<EditText>(R.id.etEmpSalary)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        edtName.setText(intent.getStringExtra("empName").toString())
        edtAge.setText(intent.getStringExtra("empAge").toString())
        edtSalary.setText(intent.getStringExtra("empSalary").toString())

        mDialog.setTitle("Update Information Employee: ${name}")
        val alertDialog = mDialog.create()
        alertDialog.show()

        //btnUpdateData
        btnUpdateData.setOnClickListener {
            updateEmployeeData(id, edtName.text.toString(), edtAge.text.toString(), edtSalary.text.toString())
            Toast.makeText(applicationContext, "Update Success!", Toast.LENGTH_SHORT).show()

            //update lai data len employee detals activity
            binding.tvEmpName.text = edtName.text.toString()
            binding.tvEmpAge.text = edtAge.text.toString()
            binding.tvEmpSalary.text = edtSalary.text.toString()
            alertDialog.dismiss()
        }
    }

    private fun updateEmployeeData(id: String, name: String, age: String, salary: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val empInfoDetail = EmployeeModel(id, name, age, salary)
        dbRef.setValue(empInfoDetail)

    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mTask = dbRef.removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Delete Success!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FetchingActivity::class.java)
                finish()
                startActivity(intent)
            }
            .addOnFailureListener {err ->
                Toast.makeText(this, "Delete Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun showValueToView() {
        binding.tvEmpId.text = intent.getStringExtra("empId")
        binding.tvEmpAge.text = intent.getStringExtra("empAge")
        binding.tvEmpName.text = intent.getStringExtra("empName")
        binding.tvEmpSalary.text = intent.getStringExtra("empSalary")
    }
}