package com.example.mytunes.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytunes.models.BitModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):AndroidViewModel(application) {
    private var status = false
    private var bits:MutableLiveData<List<BitModel>> = MutableLiveData()
    fun addBits(bitModel: BitModel):Boolean{
        FirebaseDatabase.getInstance().getReference("/bits/${FirebaseAuth.getInstance().uid}")
            .setValue(bitModel)
            .addOnSuccessListener {
                status = true
            }.addOnFailureListener {
                status = false
            }
        return status
    }

    fun getBits(uid:String):LiveData<List<BitModel>>{
        val list:ArrayList<BitModel> = ArrayList()
        viewModelScope.launch {
            FirebaseDatabase.getInstance().getReference("/bits/$uid")
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds in snapshot.children){
                            val bit = ds.getValue(BitModel::class.java)
                            if (bit!=null){
                                list.add(bit)
                            }
                        }
                        bits.postValue(list)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(TAG, "onCancelled: ${error.message}", )
                    }

                })
        }
        return bits
    }
    companion object{
        private val TAG = HomeViewModel::class.java.simpleName
    }

}