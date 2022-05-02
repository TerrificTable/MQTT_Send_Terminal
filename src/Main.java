import org.eclipse.paho.client.mqttv3.MqttException;
import util.SaveConfig;
import util.Send;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static boolean change = false;

    public static void main(String[] args) throws MqttException {

        Scanner scanner = new Scanner(System.in);

        try {
            SaveConfig.Companion.load();
            String ch = scanner.nextLine();
            if (ch.equals("c"))
                change = true;
        } catch (Exception e) {
            e.printStackTrace();
            change = true;
        }

        // =========
        if (change) {
            System.out.print("IP: ");
            String ip = scanner.nextLine();

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            System.out.print("Channel: ");
            String channel = scanner.nextLine();

            Send.Companion.setIP(ip);               // "192.168.100.75"
            Send.Companion.setRECEIVE(channel);     // "test"
            Send.Companion.setUSERNAME(username);   // "mqtt"
            Send.Companion.setPASSWORD(password);   // "terrific"
            SaveConfig.Companion.save();
        }
        Send.Companion.setPORT("1883");
        // =========

        System.out.println();
        Send.Companion.init();

        // =========
        System.out.print("Message: ");
        String input = scanner.nextLine();
        while (!Objects.equals(input, ".quit")) {
            System.out.println();
            SaveConfig.Companion.save();

            Send.Companion.send(input);

            System.out.print("Message: ");
            input = scanner.nextLine();
        }
        // =========

        Objects.requireNonNull(Send.Companion.getClient()).disconnect();

    }
}
