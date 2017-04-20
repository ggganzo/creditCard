package financialcore.account;

import java.math.BigDecimal;

public class TransactionTemplate {

	public int accountNo;
	public BigDecimal amount;
	public String description;

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Send to email: " + "\n");
		sb.append("accountNo: " + accountNo + "\n");
		sb.append("amount: " + amount + "\n");
		sb.append("description: " + description + "\n");

		return sb.toString();
	}
}
