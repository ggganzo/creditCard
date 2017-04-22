package financialcore.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionTemplate {

	public int accountNo;
	public BigDecimal amount;
	public String description;
	public LocalDate dueDate = null;

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("accountNo: " + accountNo + "\n");
		sb.append("amount: " + amount + "\n");
		sb.append("description: " + description + "\n");

		if (dueDate != null)
			sb.append("dueDate: " + description + "\n");

		return sb.toString();
	}
}
