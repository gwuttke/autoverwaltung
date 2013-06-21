package domain;

import java.math.BigDecimal;

public class Berechnung {
	private BigDecimal b;
	
	
	public BigDecimal getRound(double value, int nachkommata){
		return round(value, nachkommata);
		
	}
	
	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	private BigDecimal round(double value, int nachkommaStelle) {
		setB(new BigDecimal(value));
		setB(getB().setScale(nachkommaStelle, BigDecimal.ROUND_HALF_UP));

		return getB();

	}

}
