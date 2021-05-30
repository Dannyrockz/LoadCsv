package com.example.loadcsv.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.loadcsv.model.UserDetails
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.util.ArrayList

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application.applicationContext
    private val dataList = MutableLiveData<List<UserDetails>>()

    //read Data From csv file
    fun getDataFromCSV(): LiveData<List<UserDetails>> {
        viewModelScope.launch {
            val fileReader: BufferedReader
            var line: String
            val userList = ArrayList<UserDetails>()
            try {
                fileReader = BufferedReader(InputStreamReader(context.assets.open("issues.csv")))
                // Read CSV header
                fileReader.readLine()
                // Read the file line by line starting from the second line
                line = fileReader.readLine()
                while (line != null) {
                    val tokens = line.split(",")
                    if (tokens.isNotEmpty()) {
                        val userDetails = UserDetails(
                            tokens[0],
                            tokens[1],
                            tokens[2],
                            tokens[3]
                        )
                        userList.add(userDetails)
                    }
                    line = fileReader.readLine()
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
            dataList.postValue(userList)
        }
        return dataList
    }
}