package com.example.auth.impl.datasource.remote

import com.example.auth.api.datasource.RemoteDataSource
import com.example.auth.impl.mapper.toUserModel
import com.example.db.room.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
) : RemoteDataSource {
    override suspend fun signUp(name: String, password: String, email: String): UserEntity {
        val user = auth.createUserWithEmailAndPassword(email, password).await().user!!.toUserModel()
        saveUserToFireStore(user)
        return user
    }

    override suspend fun signIn(email: String, password: String): UserEntity {
        return auth.signInWithEmailAndPassword(email, password).await().user!!.toUserModel()
    }

    override suspend fun getCurrentUser(): UserEntity {
        return fireStore.collection(USERS_COLLECTION_PATH).document(auth.currentUser!!.uid).get()
            .await().toUserModel()
    }

    override suspend fun updateCurrentUser(user: UserEntity) {
        saveUserToFireStore(user)
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    private suspend fun saveUserToFireStore(user: UserEntity) {
        fireStore.collection(USERS_COLLECTION_PATH).document("${user.id}").set(user).await()
    }

    companion object {
        private const val USERS_COLLECTION_PATH = "users"
    }
}