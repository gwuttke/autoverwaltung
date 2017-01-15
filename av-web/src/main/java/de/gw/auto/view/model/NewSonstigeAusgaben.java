package de.gw.auto.view.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import de.gw.auto.domain.Auto;

public class NewSonstigeAusgaben extends ListGeneral {

	@NumberFormat(style = Style.NUMBER)
	@Max(value = 999999, message = "Der Km-Stand darf 999999 nicht überschreiten")
	@Min(value = 1, message = "Der Km-Stand darf 1 nicht unterschreiten")
	private int kmStand;
	/*
	 * @Min(value = 1, message = "Bitte wählen Sie etwas aus") private int
	 * landId;
	 * 
	 * @Min(value = 1, message =
	 * "Bitte wählen Sie etwas aus, oder fügen Sie einen Ort hinzu") private int
	 * ortId;
	 */
	@NotNull(message = "Bitte geben Sie die Kosten an")
	@DecimalMin(value = "0.01", message = "Die Kosten dürfen 0,01 nicht unterschreiten")
	@DecimalMax(value = "1000", message = "Die Kosten dürfen 1000,00 nicht unterschreiten")
	private BigDecimal kosten;

	@NotNull(message = "Ein Datum muss angegeben werden")
	@DateTimeFormat(pattern = "dd.MM.YYYY")
	private Date datum;

	private String kommentar;

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	private Auto auto;

	/*
	 * public int getLandId() { return landId; }
	 * 
	 * public void setLandId(int landId) { this.landId = landId; }
	 * 
	 * public int getOrtId() { return ortId; }
	 * 
	 * public void setOrtId(int ortId) { this.ortId = ortId; }
	 */
	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
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

	public void setDatum(Date date) {
		this.datum = date;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}
}
