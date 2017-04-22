package financialcore.account;

public enum NotificationTran {

	INSTANCE;

	public void sendNotifictation(Object pData) {
		System.out.println("Send to email: " + "\n");
		System.out.println(pData.toString());
	}

	public void sendStatement(Object pData) {
		System.out.println("Send to email: " + "\n");
		System.out.println(pData.toString());
	}

}
