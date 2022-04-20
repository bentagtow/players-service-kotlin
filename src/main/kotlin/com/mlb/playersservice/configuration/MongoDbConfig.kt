package com.mlb.playersservice.configuration

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate

@Configuration
class MongoDbConfig : AbstractReactiveMongoConfiguration(){
    @Bean
    fun mongoClient(): MongoClient{
        return MongoClients.create()
    }

    override fun getDatabaseName(): String {
        return "mlb-db"
    }

    @Bean
    fun mongoTemplate(): ReactiveMongoTemplate?{
        return ReactiveMongoTemplate(mongoClient(), this.databaseName)
    }

}