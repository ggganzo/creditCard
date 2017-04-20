package financialcore.account;

import financialcore.interfaces.NotificationI;

public class NotificationTran implements NotificationI {

	public void sendNotifictation(Object pData) {

		System.out.println(pData.toString());
	}

}
