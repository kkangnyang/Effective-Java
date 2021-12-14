package effective.java.item11;

public class HashCodeTest {

	public static void main(String[] args) {
		

	}
	
	
    public static class PhoneNumber {
        protected int firstNumber;
        protected int secondNumber;
        protected int thirdNumber;
        
        
        
        public PhoneNumber(int firstNumber, int secondNumber, int thirdNumber) {
			super();
			this.firstNumber = firstNumber;
			this.secondNumber = secondNumber;
			this.thirdNumber = thirdNumber;
		}



		@Override
        public boolean equals(Object o) {
            if (!(o instanceof PhoneNumber)) {
                return false;
            }

            PhoneNumber p = (PhoneNumber) o;
            return this.firstNumber == p.firstNumber &&
                    this.secondNumber == p.secondNumber &&
                    this.thirdNumber == p.thirdNumber;
        }
    }

    public static class ExtendedPhoneNumber extends PhoneNumber {

        public ExtendedPhoneNumber(int firstNumber, int secondNumber, int thirdNumber) {
            super(firstNumber, secondNumber, thirdNumber);
        }

        @Override
        public int hashCode() {
            int c = 31;
            int hashcode = Integer.hashCode(firstNumber);
            hashcode = c * hashcode + Integer.hashCode(secondNumber);
            hashcode = c * hashcode + Integer.hashCode(thirdNumber);
            return hashcode;
        }
    }

}
