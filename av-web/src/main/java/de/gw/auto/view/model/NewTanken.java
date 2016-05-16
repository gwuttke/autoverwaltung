package de.gw.auto.view.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Land;

public class NewTanken extends ListTanken {
	
	@NumberFormat(style=Style.NUMBER)
	@Max(value=999999,message="Der Km-Stand darf 999999 nicht überschreiten")
	@Min(value=1,message="Der Km-Stand darf 1 nicht unterschreiten")
	private int kmStand;

	@Min(value=1,message="Bitte wählen Sie etwas aus")
	private int landId;


	@Min(value=1 ,message="Bitte wählen Sie etwas aus, oder fügen Sie einen Ort hinzu")
	private int ortId;

	private int tankId;

	@NotNull(message="Bitte geben Sie die Kosten an")
	@DecimalMin(value="0.01", message="Die Kosten dürfen 0,01 nicht unterschreiten")
	@DecimalMax(value="1000",message="Die Kosten dürfen 1000,00 nicht unterschreiten")
	private BigDecimal kosten;
	
	@NotNull(message="Ein Datum muss angegeben werden")
    @DateTimeFormat(pattern="dd.MM.YYYY")
	private Date datum;

	@NotNull(message="Bitte geben Sie eine Literanzahl an")
	@DecimalMin(value="0.01", message="Die Literanzahl darf 0,01 nicht unterschreiten")
	@DecimalMax(value="1000.00", message="Die Literanzahl darf 1000,00 nicht üerschreiten")
	private BigDecimal liter;

	@NotNull(message="Bitte geben Sie einen Preis je Liter ein im Format 0.000")
	@DecimalMin(value="0.01", message="Bitte geben Sie relevante Plausible daten ein")
	@DecimalMax(value="5.00", message="Bitte geben Sie relevante Plausible daten ein")
	private BigDecimal preisProLiter;

	private int userKraftstoffsorte;

	private Auto auto;

	public List<Land> getLaender() {
		return laender;
	}

	public int getLandId() {
		return landId;
	}

	public void setLandId(int landId) {
		this.landId = landId;
	}

	public int getOrtId() {
		return ortId;
	}

	public void setOrtId(int ortId) {
		this.ortId = ortId;
	}

	public void setLaender(List<Land> laender) {
		this.laender = laender;
	}

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}


	public int getTankId() {
		return tankId;
	}

	public void setTankId(int tankId) {
		this.tankId = tankId;
	}

	public BigDecimal getKosten() {
		return kosten;
	}

	public void setKosten(BigDecimal kosten) {
		this.kosten = kosten;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date date) { this.datum = date; }
	 
	/*
	public void setDatum(String datum) throws ParseException {
		this.datum = AutoModel.stringToDate(datum);
	}
*/
	public BigDecimal getLiter() {
		return liter;
	}

	public void setLiter(BigDecimal liter) {
		this.liter = liter;
	}

	public BigDecimal getPreisProLiter() {
		return preisProLiter;
	}

	public void setPreisProLiter(BigDecimal preisProLiter) {
		this.preisProLiter = preisProLiter;
	}

	public int getUserKraftstoffsorte() {
		return userKraftstoffsorte;
	}

	public void setUserKraftstoffsorte(int userKraftstoffsorte) {
		this.userKraftstoffsorte = userKraftstoffsorte;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
}
