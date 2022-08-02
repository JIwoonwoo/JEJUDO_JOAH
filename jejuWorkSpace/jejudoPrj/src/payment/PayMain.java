package payment;

public class PayMain {

	public static void main(String[] args) {
		
		System.out.println("결제를 시작");
		
		new PayController().payment();
		
	}

}
