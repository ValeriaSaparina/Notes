package com.example.db.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "notes",
    foreignKeys = [ForeignKey(
        entity = FolderEntity::class,
        parentColumns = ["id"],
        childColumns = ["folder_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val text: String,
    @ColumnInfo(name = "create_date") val createDate: Long,
    @ColumnInfo(name = "edit_date") val editDate: Long,
    @ColumnInfo(name = "folder_id") val folderId: Long
)

data class NoteWithFolderEntity(
    @Embedded val note: NoteEntity,
    @Relation(
        parentColumn = "folder_id",
        entityColumn = "id"
    )
    val folder: FolderEntity
)