package com.linya.memorandum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.linya.memorandum.db.DataBaseHelper
import com.linya.memorandum.logic.model.LoginResponse
import com.linya.memorandum.logic.network.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        this.title = "登录"

        findViewById<Button>(R.id.login_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?){
        when(v?.id){
            //点击登录按钮
            R.id.login_btn -> {
                val username = findViewById<EditText>(R.id.loginAccount).text.toString()
                val password = findViewById<EditText>(R.id.loginPassword).text.toString()
                if (username == "admin" && password == "admin") {
                    // 存储登录状态
                    val sharedPref = getSharedPreferences("app_settings", MODE_PRIVATE)
                    sharedPref.edit()
                        .putBoolean("is_logged_in", true)
                        .putString("username", username)
                        .apply()
                    // 跳转到主界面
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    AlertDialog.Builder(this@LoginActivity).apply {
                        setTitle("用户名或密码错误")
                        setMessage("用户名和密码都必须是 admin")
                        setCancelable(true)
                        setPositiveButton("OK", null)
                        show()
                    }
                }
//                val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")//("http://10.83.130.5:8080/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                val loginService = retrofit.create(LoginService::class.java)
//                loginService.getLoginResponse(username, password).enqueue(object : Callback<LoginResponse> {
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val r = response.body()
//                        if(r!!.message == "参数缺失"){
//                            AlertDialog.Builder(this@LoginActivity).apply{
//                                setTitle("用户名或密码不能为空！")
//                                setMessage("请在用户名和密码栏输入完整的字段！")
//                                setCancelable(true)
//                                setPositiveButton("OK"){
//                                        _, _ ->
//                                }
//                                setNegativeButton("Cancel"){
//                                    _, _ ->
//                                }
//                                show()
//
//                            }
//                        }else if(r.message == "账号密码错误"){
//                            AlertDialog.Builder(this@LoginActivity).apply {
//
//                                setTitle("用户名或密码错误！！")
//                                setMessage("用户名和密码都得是admin！（误）！请重新输入用户名和密码！")
//                                setCancelable(true)
//                                setPositiveButton("OK") { _, _ ->
//                                }
//                                setNegativeButton("Cancel") { _, _ ->
//                                }
//                                show()
//
//                            }
//                        }else{
//                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                            val dbHelper = DataBaseHelper(this@LoginActivity, "note.db", 1)
//                            dbHelper.writableDatabase
//                            // 新增保存登录状态
//                            val sharedPref = getSharedPreferences("app_settings", MODE_PRIVATE)
//                            sharedPref.edit()
//                                .putBoolean("is_logged_in", true)
//                                .putString("username", username)
//                                .apply()
//                            finish()
//                            startActivity(intent)
////                            finish()
////                            startActivity(intent)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        Toast.makeText(this@LoginActivity, "网络请求错误！",Toast.LENGTH_SHORT).show()
//                        t.printStackTrace()
//                    }
//                })

            }
        }
    }

}