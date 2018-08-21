package x

import com.ecwid.consul.v1.agent.AgentConsulClient
import com.ecwid.consul.v1.agent.model.NewService
import com.github.golovnin.embedded.consul.ConsulAgentConfig
import com.github.golovnin.embedded.consul.ConsulAgentProcess
import com.github.golovnin.embedded.consul.ConsulAgentStarter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object ServiceLookerUpperSpec : Spek({
    describe("DNS SRV Record lookup") {

        on("A registered service") {
            val service = NewService()
            service.name = "some-service"
            service.port = 8975
            service.tags = mutableListOf("stage")

            val client = AgentConsulClient()
            client.agentServiceRegister(service)

            it("Should be able to lookup the service") {
                val serviceLookerUpper = ServiceLookerUpper()
                val address = serviceLookerUpper.lookThisUp("some-service")

                println("Address: ${address}")
            }
        }

        var consulProcess: ConsulAgentProcess? = null
        beforeGroup {
            println("START THIS!!!")
            val config = ConsulAgentConfig.Builder()
                    .build()
            val starter = ConsulAgentStarter.getDefaultInstance()
            val executable = starter.prepare(config)
            println("STARTING")
            consulProcess = executable.start()
        }

        afterGroup {
            consulProcess?.stop()
        }
    }
})