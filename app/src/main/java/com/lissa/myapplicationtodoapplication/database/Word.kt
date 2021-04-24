/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lissa.myapplicationtodoapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "word_table")
data class Word(
    /*  @PrimaryKey @ColumnInfo(name = "word") val word: String,
      @ColumnInfo(name = "desc")
      var description: String? = null*/

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "word")
    val word: String? = null,

    @ColumnInfo(name = "desc")
    val description: String? = null,

    @ColumnInfo(name = "finish_by")
    val finishBy: String? = null,

    @ColumnInfo(name = "finished")
    val finished: Boolean = false
)
