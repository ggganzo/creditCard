package financialcore.account;


public enum NotificationReport {

	INSTANCE;

	public void sendNotifictation(Object pData) {

		System.out.println(pData.toString());
	}

}
