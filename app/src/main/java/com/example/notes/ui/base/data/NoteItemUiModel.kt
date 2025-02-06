package com.example.notes.ui.base.data

data class NoteItemUiModel(
    val id: String,
    val title: String,
    val text: String,
    val dateOfEditing: String,
    val nameFolder: String
) : SectionItem {
    companion object {
        fun getDefault() =
            NoteItemUiModel(
                id = "",
                title = "",
                text = "",
                dateOfEditing = "",
                nameFolder = ""
            )

        fun getDefaultList() =
            listOf(
                getDefault().copy(id = "1"),
                getDefault().copy(id = "19"),
                getDefault().copy(id = "12"),
                getDefault().copy(id = "13"),
                getDefault().copy(id = "14"),
                getDefault().copy(id = "15"),
                getDefault().copy(id = "17"),
                getDefault().copy(id = "16"),
                getDefault().copy(id = "18"),
                getDefault().copy(id = "9"),
                getDefault().copy(id = "10"),
            )
    }
}
