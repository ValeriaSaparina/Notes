pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Notes"
include(":app")

/* Folders module */
include(":feature:folders:api")
include(":feature:folders:impl")

/* Core module */
include(":core:common")
include(":core:db")
include(":core:utils")
include(":core:viewmodel")
include(":core:designsystem")
include(":core:navigation")
include(":feature:notes:api")
include(":feature:notes:impl")
include(":feature:note:api")
include(":feature:note:impl")
include(":feature:auth:api")
include(":feature:auth:impl")
