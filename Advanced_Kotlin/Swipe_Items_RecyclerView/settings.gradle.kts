pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io" )
        }
//        maven { url "https://jitpack.io" }
    }
}

rootProject.name = "Swipe_Items_RecyclerView"
include(":app")
 