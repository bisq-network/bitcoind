pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("build-logic") {
        name = "bitcoind-build-logic"
    }
}

include("bitcoind-core")
include("bitcoind")
include("json-rpc")
include("regtest")

rootProject.name = "bitcoind"
