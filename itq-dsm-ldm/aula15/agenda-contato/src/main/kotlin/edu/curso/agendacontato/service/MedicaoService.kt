package edu.curso.agendacontato.service

import edu.curso.agendacontato.model.Medicao
import edu.curso.agendacontato.repository.MedicaoRepository
import jakarta.annotation.PostConstruct
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.stereotype.Service

@Service
class MedicaoService(
    private val repository : MedicaoRepository
) {

    @PostConstruct
    fun inicializar() {
        val clientId = MqttClient.generateClientId()
        val serverURI = "tcp://10.196.121.237:1883"
        val mqttClient = MqttClient(serverURI, clientId)
        mqttClient.connect()
        mqttClient.subscribe("sensor/dht11") {
                topico : String, msg : MqttMessage ->
            try {
                val texto: String = msg.toString()
                // {"temp": 21.8, "umd": 43.5}
                val jsonObj = Json.decodeFromString<JsonObject>(texto)
                val medicao = Medicao(
                    id = 0,
                    temperatura = jsonObj.get("temp").toString().toFloat(),
                    umidade = jsonObj.get("umid").toString().toFloat(),
                    topico = topico
                )
                repository.save(medicao)
            } catch ( exception : Exception ) {
                exception.printStackTrace()
            }
        }
    }
}