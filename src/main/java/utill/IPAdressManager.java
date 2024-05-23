package utill;

import java.net.InetAddress;

public class IPAdressManager {
	public static String ADMIN_KEY = "ADMIN JU7Spt9DHvLaiHcxTD4h";
	public static String getLocalAddress() {
		String ip = "";
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
			ip += "http://" + local.getHostAddress() + ":3000";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ip;
	}
}
