package financialcore.account;

import financialcore.interfaces.NotificationI;

public class NotificationReport implements NotificationI {

	public void sendNotifictation(Object pData) {

		System.out.println(pData.toString());
	}

}
