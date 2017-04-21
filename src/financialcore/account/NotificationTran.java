package financialcore.account;

public enum NotificationTran {

	INSTANCE;

	public void sendNotifictation(Object pData) {

		System.out.println(pData.toString());
	}

}
