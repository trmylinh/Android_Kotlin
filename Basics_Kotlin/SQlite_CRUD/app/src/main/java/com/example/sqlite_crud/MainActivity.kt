package com.example.sqlite_crud

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.example.sqlite_crud.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    lateinit var adapter: SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val helper = MyDBHelper(applicationContext)
        db = helper.readableDatabase

        rs = db.rawQuery("select * from user", null)
        // sql-line
        //selectionArgs: chon loc du lieu voi dieu kien nao do

        //btnFirst
        binding.btnFirst.setOnClickListener {
            if (rs.moveToFirst()) {
                binding.edtUser.setText(rs.getString(1))
                binding.edtEmail.setText(rs.getString(2))
            } else {
                Toast.makeText(applicationContext, "No data found!", Toast.LENGTH_LONG).show()
            }
        }

        //btnNext
        binding.btnNext.setOnClickListener {
            if (rs.moveToNext()) {
                binding.edtUser.setText(rs.getString(1))
                binding.edtEmail.setText(rs.getString(2))
            }
            // vong lap du lieu
            else if (rs.moveToFirst()) {
                binding.edtUser.setText(rs.getString(1))
                binding.edtEmail.setText(rs.getString(2))
            } else {
                Toast.makeText(applicationContext, "No data found!", Toast.LENGTH_LONG).show()
            }
        }

        //btnPrev
        binding.btnPrev.setOnClickListener {
            if (rs.moveToPrevious()) {
                binding.edtUser.setText(rs.getString(1))
                binding.edtEmail.setText(rs.getString(2))
            }
            // vong lap du lieu
            else if (rs.moveToLast()) {
                binding.edtUser.setText(rs.getString(1))
                binding.edtEmail.setText(rs.getString(2))
            } else {
                Toast.makeText(applicationContext, "No data found!", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLast.setOnClickListener {
            if (rs.moveToLast()) {
                binding.edtUser.setText(rs.getString(1))
                binding.edtEmail.setText(rs.getString(2))
            } else {
                Toast.makeText(applicationContext, "No data found!", Toast.LENGTH_LONG).show()
            }
        }


        //list view
        adapter = SimpleCursorAdapter(
            applicationContext,
            android.R.layout.simple_expandable_list_item_2,
            rs,
            arrayOf("username", "email"),
            intArrayOf(android.R.id.text1, android.R.id.text2),
            0
        )

        binding.lvFull.adapter = adapter

        //click 1 item hien thi thong tin
        binding.lvFull.setOnItemClickListener { adapterView, view, i, l ->
            binding.edtUser.setText(rs.getString(1))
            binding.edtEmail.setText(rs.getString(2))
        }

        // btnViewAll

        binding.btnViewAll.setOnClickListener {
            binding.searchView.visibility = View.VISIBLE
            binding.lvFull.visibility = View.VISIBLE

            adapter.notifyDataSetChanged()

            binding.searchView.queryHint = "Searching in ${rs.count} records"
        }


        // tim kiem noi dung
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                rs = db.rawQuery("select * from USER where username like '%${p0}%' or email like '%${p0}%' ", null)
                adapter.changeCursor(rs)
                return true
            }
        })


        binding.btnInsert.setOnClickListener {
            var contentValue = ContentValues()
            contentValue.put("username", binding.edtUser.text.toString())
            contentValue.put("email", binding.edtEmail.text.toString())

            db.insert("user", null, contentValue)

            rs.requery()

            // update data tren list view
            adapter.notifyDataSetChanged()

            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Add record")
            dialog.setMessage("Success!")
            dialog.setPositiveButton("OK", DialogInterface.OnClickListener{
                dialog, which ->
                binding.edtUser.setText("")
                binding.edtEmail.setText("")

                binding.edtUser.requestFocus()
            })
            dialog.show()
        }

        binding.btnUpdate.setOnClickListener {
            var contentValue = ContentValues()
            contentValue.put("username", binding.edtUser.text.toString())
            contentValue.put("email", binding.edtEmail.text.toString())

            db.update("user", contentValue, "_id=?", arrayOf(rs.getString(0)))

            rs.requery()

            // update data tren list view
            adapter.notifyDataSetChanged()

            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Update record")
            dialog.setMessage("Success!")
            dialog.setPositiveButton("OK", DialogInterface.OnClickListener{
                    dialog, which ->
                binding.edtUser.setText("")
                binding.edtEmail.setText("")

                binding.edtUser.requestFocus()
            })
            dialog.show()
        }


        //btnClear
        binding.btnClear.setOnClickListener {
            binding.edtUser.setText("")
            binding.edtEmail.setText("")

            binding.edtUser.requestFocus()
        }

        // btnDelete
        binding.btnDelete.setOnClickListener {
            db.delete("user", "_id=?", arrayOf(rs.getString(0)))
            rs.requery()

            adapter.notifyDataSetChanged()

            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("Delete record")
            dialog.setMessage("Success!")
            dialog.setPositiveButton("OK", DialogInterface.OnClickListener{
                    dialog, which ->
                if(rs.moveToFirst()){
                    binding.edtUser.setText("")
                    binding.edtEmail.setText("")

                    binding.edtUser.requestFocus()
                }
                else{
                    binding.edtUser.setText("No data found")
                    binding.edtEmail.setText("No data found")
                }
            })
            dialog.show()
        }

        // dang ky context menu
        registerForContextMenu(binding.lvFull)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(1,1,1, "Delete")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.itemId == 1){
            db.delete("user", "_id=?", arrayOf(rs.getString(0)))
            rs.requery()

            adapter.notifyDataSetChanged()

            binding.searchView.queryHint = "Searching in ${rs.count} records"
        }
        return super.onContextItemSelected(item)
    }
}