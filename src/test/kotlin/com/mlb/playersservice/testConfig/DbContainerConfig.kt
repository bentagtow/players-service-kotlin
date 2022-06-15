package com.mlb.playersservice.testConfig

import org.testcontainers.containers.MongoDBContainer

class DbContainerConfig: MongoDBContainer(imageName) {
    companion object{
        const val imageName = "mongo:4.4.1"
        private val container: DbContainerConfig = DbContainerConfig()
        fun getInstance(): DbContainerConfig {
            return container
        }
    }
}