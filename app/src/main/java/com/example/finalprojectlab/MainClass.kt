package com.example.finalprojectlab

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainClass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_class)

        val txtName = findViewById<TextView>(R.id.txtName)
        val txtLastName = findViewById<TextView>(R.id.txtLastName)
        val txtPhone = findViewById<TextView>(R.id.txtPhone)

        val spMonth = findViewById<Spinner>(R.id.spMonth)
        val txtEditDay = findViewById<TextView>(R.id.txtEditDay)
        val txtEditYear = findViewById<TextView>(R.id.txtEditYear)

        val switch1 = findViewById<Switch>(R.id.switch1)
        val spCertificate = findViewById<Spinner>(R.id.spCerts)
        val spAssociates = findViewById<Spinner>(R.id.spAssociates)

        val btnNext = findViewById<Button>(R.id.btnNext)

        spCertificate.visibility = View.GONE

        txtName.requestFocus()

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                spCertificate.visibility = View.GONE
                spAssociates.visibility = View.VISIBLE
            } else {
                spCertificate.visibility = View.VISIBLE
                spAssociates.visibility = View.GONE
            }
        }

        btnNext.setOnClickListener {
            if (checkData()){
                var doBirth = ""
                doBirth = spMonth.selectedItem.toString() + "/" + txtEditDay.text.toString() + "/" + txtEditYear.text.toString()

                val nextScreen = Intent(this@MainClass, ChooseClass::class.java)
                nextScreen.putExtra("firstName", txtName.text.toString())
                nextScreen.putExtra("lastName", txtLastName.text.toString())
                nextScreen.putExtra("phone", txtPhone.text.toString())
                nextScreen.putExtra("birthdate", doBirth)

                if (spCertificate.visibility == View.VISIBLE){
                    nextScreen.putExtra("isDegreeCert", "Certificate")
                    nextScreen.putExtra("degreeCert", spCertificate.selectedItem.toString())
                } else {
                    nextScreen.putExtra("isDegreeCert", "Degree")
                    nextScreen.putExtra("degreeCert", spAssociates.selectedItem.toString())
                }

                startActivity(nextScreen)
            }
        }

        }

        private fun checkData(): Boolean {
            val txtName = findViewById<TextView>(R.id.txtName)
            val txtLastName = findViewById<TextView>(R.id.txtLastName)
            val txtPhone = findViewById<TextView>(R.id.txtPhone)

            val txtEditDay = findViewById<TextView>(R.id.txtEditDay)
            val txtEditYear = findViewById<TextView>(R.id.txtEditYear)

            if (txtName.text.toString().isEmpty()){
                txtName.error = "Invalid First Name, please retry."
                txtName.requestFocus()
                return false
            }

            if (txtLastName.text.toString().isEmpty()){
                txtLastName.error = "Invalid Last Name, please retry."
                txtLastName.requestFocus()
                return false
            }

            if (txtPhone.text.toString().isEmpty()){
                txtPhone.error = "Invalid Phone, please retry."
                txtPhone.requestFocus()
                return false
            }

            if (txtEditDay.text.toString().isEmpty()){
                txtEditDay.error = "Invalid Day, please retry."
                txtEditDay.requestFocus()
                return false
            }

            if (txtEditYear.text.toString().isEmpty()){
                txtEditYear.error = "Invalid Year, please retry."
                txtEditYear.requestFocus()
                return false
            }


            return true
        }
    }
