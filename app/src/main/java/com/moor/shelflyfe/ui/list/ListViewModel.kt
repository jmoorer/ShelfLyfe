package com.moor.shelflyfe.ui.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel:ViewModel() {

    private var selectedItem=  MutableLiveData<ListItem>()


    fun select(item: ListItem) {
        selectedItem.setValue(item)
    }

    fun getSelected(): LiveData<ListItem> {
        selectedItem = MutableLiveData<ListItem>()
        return selectedItem
    }
}