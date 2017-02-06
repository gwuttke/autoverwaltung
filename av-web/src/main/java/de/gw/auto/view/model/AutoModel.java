package de.gw.auto.view.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Kraftstoff;

public class AutoModel {
	private String kfz;

	@Max(value = 999999, message = "Der Kauf Km-Stand darf 999999 nicht überschreiten")
	@Min(value = 1, message = "Der Kauf Km-Stnd darf 1 nicht unterschreiten")
	private int kmKauf;

	@NotNull(message = "Ein Datum muss angegeben werden")
	@DateTimeFormat(pattern = "dd.MM.YYYY")
	private Date kauf;

	@NotNull(message = "Ein Datum muss angegeben werden")
	@DateTimeFormat(pattern = "dd.MM.YYYY")
	private Date erstZulassung;

	private List<Kraftstoff> kraftstoffarten = new ArrayList<Kraftstoff>();

	@NotNull(message = "es muss ein Kaftstoff ausgewählt werden")
	private Kraftstoff userKraftstoffart;

	@Min(value = 1, message = "Der aktuelle Km-Stand darf nicht 1 unterschreiten und sollte größer ald der Kauf Km-Stand sein")
	private int kmAktuell;

	@Min(value = 1, message = "Der Start Km-Stand darf nicht 1 unterschreiten und sollte kleiner oder Gleich dem kmAktuell sein")
	private int kmStart;

	protected AutoModel() {
		super();
	}

	public AutoModel(Auto auto) {
		this(auto.getKfz(), auto.getKmKauf(), auto.getKauf(), auto.getErstZulassung(), null, auto.getKraftstoff(),
				auto.getKmAktuell(), auto.getKmStart());
	}

	protected AutoModel(String kfz, int kmKauf, Date kauf, Date erstZulassung, List<Kraftstoff> kraftstoffarten,
			Kraftstoff userKraftstoffart, int kmAktuell, int kmStart) {
		this();
		this.kfz = kfz;
		this.kmKauf = kmKauf;
		this.kauf = kauf;
		this.erstZulassung = erstZulassung;
		this.kraftstoffarten = kraftstoffarten;
		this.userKraftstoffart = userKraftstoffart;
		this.kmAktuell = kmAktuell;
		this.kmStart = kmStart;
	}

	public int getKmStart() {
		return kmStart;
	}

	public void setKmStart(int kmStart) {
		this.kmStart = kmStart;
	}

	public Kraftstoff getUserKraftstoffart() {
		return userKraftstoffart;
	}

	public void setUserKraftstoffart(Kraftstoff userKraftstoffart) {
		this.userKraftstoffart = userKraftstoffart;
	}

	public List<Kraftstoff> getKraftstoffarten() {
		return kraftstoffarten;
	}

	public void setKraftstoffarten(List<Kraftstoff> kraftstoffarten) {
		this.kraftstoffarten = kraftstoffarten;
	}

	public String getKfz() {
		return kfz;
	}

	public void setKfz(String kfz) {
		this.kfz = kfz;
	}

	public int getKmKauf() {
		return kmKauf;
	}

	public void setKmKauf(int kmKauf) {
		this.kmKauf = kmKauf;
	}

	public Date getKauf() {
		return kauf;
	}

	public void setKauf(Date kauf) throws ParseException {
		this.kauf = kauf;
	}

	/**
	 * @deprecated
	 * @since 2.0.00
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	@Deprecated
	public static Date stringToDate(String dateString) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
		return format.parse(dateString);
	}

	/**
	 * @deprecate
	 * @since 2.0.00
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	@Deprecated
	public static String formattedDate(Date date) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
		return format.format(date);
	}

	public Date getErstZulassung() {
		return erstZulassung;
	}

	public void setErstZulassung(Date erstZulassung) {
		this.erstZulassung = erstZulassung;
	}

	public int getKmAktuell() {
		return kmAktuell;
	}

	public void setKmAktuell(int kmAktuell) {
		this.kmAktuell = kmAktuell;
	}
}
