package com.moor.shelflyfe.ui.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ListItem(val key:String,val value:String):Parcelable