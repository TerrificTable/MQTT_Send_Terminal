package util

import Main
import java.io.*


class SaveConfig {
    companion object {

        var ip = ""
        var username = ""
        var password = ""
        var channel = ""

        fun save() {

            var _ConfigFile: File = File("C:/Users/Admin/Desktop/")

            val save_file = File(_ConfigFile.absolutePath, "Config.txt")
            val save_out = BufferedWriter(FileWriter(save_file))

            save_out.write("IP:" + Send.IP)
            save_out.write("\r\n")
            save_out.write("USERNAME:" + Send.USERNAME)
            save_out.write("\r\n")
            save_out.write("PASSWORD:" + Send.PASSWORD)
            save_out.write("\r\n")
            save_out.write("CHANNEL:" + Send.RECEIVE)
            save_out.write("\r\n")

            save_out.close()
        }

        fun load() {

            var _ConfigFile: File = File("C:/Users/Admin/Desktop/")

            val file = File(_ConfigFile.absolutePath, "Config.txt")
            val fstream = FileInputStream(file.absolutePath)
            val file_in = DataInputStream(fstream)
            val br = BufferedReader(InputStreamReader(file_in))


            var line: String? = br.readLine()
            while (line != null) {
                val content = line.split(":")

                if (content[0] == "IP") {
                    ip = content[1]
                }
                else if (content[0] == "USERNAME") {
                    username = content[1]
                }
                else if (content[0] == "PASSWORD") {
                    password = content[1]
                }
                else if (content[0] == "CHANNEL") {
                    channel = content[1]
                }

                Send.IP = ip
                Send.USERNAME = username
                Send.PASSWORD = password
                Send.RECEIVE = channel

                line = br.readLine()
            }

            println("Configs loaded: ")
            println(" - IP:       ${Send.IP}")
            println(" - USERNAME: ${Send.USERNAME}")
            println(" - PASSWORD: ${Send.PASSWORD}")
            println(" - CHANNEL:  ${Send.RECEIVE}")
            print("to change configs type 'c': ")
        }

    }
}
