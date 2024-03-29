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
    }
}

rootProject.name = "Xero Android Exercise 2023"
include(":app")
include(":core:design")
include(":core:utils")
include(":core:database")
include(":core:navigation")
include(":feature:home")
include(":feature:account-records")
include(":feature:find-matches")
include(":core:common")
