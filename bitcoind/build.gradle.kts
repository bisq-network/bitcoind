plugins {
    id("bisq.java-library")
    id("bisq.java-integration-tests")
}

dependencies {
    implementation(project(":bitcoind-core"))
    implementation(project(":json-rpc"))

    implementation(libs.jeromq)

    integrationTestImplementation(project(":regtest"))
}
