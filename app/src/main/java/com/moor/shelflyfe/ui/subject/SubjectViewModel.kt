package com.moor.shelflyfe.ui.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.moor.shelflyfe.api.BookRepository
import kotlinx.coroutines.launch

class SubjectViewModel(var repository: BookRepository) : ViewModel() {

    fun loadSubject(subject:String)= liveData{
        emit(repository.getSubjectDetails(subject))
    }



}
