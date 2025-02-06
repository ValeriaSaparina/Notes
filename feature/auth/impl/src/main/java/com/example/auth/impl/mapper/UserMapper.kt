package com.example.auth.impl.mapper

import com.example.db.room.entity.UserEntity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot

fun FirebaseUser.toUserModel(): UserEntity =
    UserEntity(id = uid, email = email!!, name = "", password = "")


fun DocumentSnapshot.toUserModel(): UserEntity =
    UserEntity(
        id = id,
        email = getString("email")!!,
        name = getString("name")!!,
        password = ""
    )