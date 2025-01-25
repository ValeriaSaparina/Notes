package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navigation.Navigation.Args.FOLDER_ID
import com.example.navigation.Navigation.Args.NOTES
import com.example.navigation.Navigation.Args.NOTE_ID
import com.example.navigation.Navigation.Routes.FOLDERS_PATH
import com.example.navigation.Navigation.Routes.NOTES_PATH
import com.example.navigation.destination.FoldersScreenDestination
import com.example.navigation.destination.NotesScreenDestination

@Composable
fun NavigationApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = FOLDERS_PATH) {
        composable(route = FOLDERS_PATH) {
            FoldersScreenDestination(navController = navController)
        }

        composable(
            route = NOTES_PATH,
            arguments = listOf(
                navArgument(name = FOLDER_ID) {
                    type = NavType.LongType
                }
            )
        ) { navBackStackEntry ->
            val folderId =
                requireNotNull(navBackStackEntry.arguments?.getLong(FOLDER_ID)) { "Folder id is required as an argument" }
            NotesScreenDestination(navController = navController, folderId = folderId)
        }
//
//        composable(
//            route = NOTE_PATH,
//            arguments = listOf(
//                navArgument(name = FOLDER_ID) { type = NavType.StringType }
//            )
//        ) { navBackStackEntry ->
//            val folderId =
//                requireNotNull(navBackStackEntry.arguments?.getString(FOLDER_ID)) { "Folder id is required as an argument" }
//            val noteId =
//                requireNotNull(navBackStackEntry.arguments?.getString(FOLDER_ID)) { "Note id is required as an argument" }
////            NoteScreenDestination(
////                navController = navController,
////                folderId = folderId,
////                noteId = noteId
////            )
//        }
    }

}


object Navigation {

    object Args {
        const val FOLDER_ID = "folderId"
        const val NOTE_ID = "note_id"
        const val NOTES = "notes"
    }

    object Routes {
        const val FOLDERS_PATH = "folders"
        const val NOTE_PATH = "${FOLDER_ID}/$NOTES/${NOTE_ID}"
        const val NOTES_PATH = "$FOLDERS_PATH/{${FOLDER_ID}}"

    }
}

fun NavController.navigateToNotes(folderId: Long) {
    navigate(route = "$FOLDERS_PATH/$folderId")
}

fun NavController.navigateToNote(folderId: Long, noteId: Long) {
    navigate(route = "$folderId/$NOTES/$noteId")
}

fun NavController.navigateToFoldersList() {
    navigate(route = FOLDERS_PATH)
}
