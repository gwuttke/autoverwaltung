package de.gw.auto.gui.model;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Spinner {
	private double dValue;
	private double dMinimum;
	private double dMaximum;
	private double dStepSize;

	private int iValue;
	private int iMinimum;
	private int iMaximum;
	private int iStepSize;
	private JSpinner sp;

	public Spinner(int iValue, int iMinimum, int iMaximum, int iStepSize) {
		super();
		this.iValue = iValue;
		this.iMinimum = iMinimum;
		this.iMaximum = iMaximum;
		this.iStepSize = iStepSize;

		getIntNummberSpinner();
	}

	public Spinner(double dValue, double dMinimum, double dMaximum,
			double dStepSize) {
		super();

		this.dValue = dValue;
		this.dMinimum = dMinimum;
		this.dMaximum = dMaximum;
		this.dStepSize = dStepSize;
		
		getDoubleNummberSpinner();
	}
	
	public JSpinner getSpinner() {
		return sp;
	}

	private void getDoubleNummberSpinner() {
		SpinnerNumberModel spnm = new SpinnerNumberModel(this.dValue,
				this.dMinimum, this.dMaximum, this.dStepSize);
		sp = new JSpinner(spnm);
	}

	private void getIntNummberSpinner() {
		SpinnerNumberModel spnm = new SpinnerNumberModel(this.iValue,
				this.iMinimum, this.iMaximum, this.iStepSize);
		sp = new JSpinner(spnm);
	}
}
