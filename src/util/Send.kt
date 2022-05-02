package util

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.json.JSONObject

class Send {
    companion object {
        var IP: String = "localhost"
        var PORT: String = ""
        var RECEIVE: String? = null
        var USERNAME: String? = null
        var PASSWORD: String? = null
        var Client: MqttClient? = null;

        fun init() {
            Client = MqttClient("tcp://$IP:$PORT", MqttClient.generateClientId(), MemoryPersistence())
            val con = MqttConnectOptions()
            con.isCleanSession = true
            con.userName = USERNAME
            con.password = PASSWORD?.toCharArray() ?: CharArray(0)

            println("Connecting to: $IP")

            Client!!.connect(con)

        }


        fun send(msg: String) {

            val message = MqttMessage(JSONObject.stringToValue(msg).toString().toByteArray())
            message.qos = 2
            Client?.publish(RECEIVE, message)

            println("Message sent")

        }
    }
}
