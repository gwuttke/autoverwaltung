package de.gw.auto.service.implementation;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.service.helper.CurrencyHelper;
import de.gw.auto.service.helper.NumberHelper;

@Service
public class TankfuellungenPdf {
	private static final int MAX_TABLE_ROWS = 30;

	List<Tankfuellung> tankfuellungen = new ArrayList<Tankfuellung>();

	private Document document;

	public TankfuellungenPdf() {
		super();
	}

	public TankfuellungenPdf(List<Tankfuellung> tankfuellungen) {
		this();
		this.tankfuellungen = tankfuellungen;
	}

	public void setTankfuellungen(List<Tankfuellung> tankfuellungen) {
		this.tankfuellungen = tankfuellungen;
	}

	public OutputStream create() throws DocumentException {
		return createAndGetBytes();
	}

	public ByteArrayOutputStream createAndGetBytes() throws DocumentException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		document = new Document();
		document.setPageSize(PageSize.A4.rotate());
		document.setMargins(0.5f, 0.5f, 50f, 50f);
		PdfWriter.getInstance(document, baos);
		build();
		return baos;
	}

	private void build() {
		document.open();
		document.addTitle("Tankfüllungen");
		try {
			document.add(body());
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}

	private void header() {
	}

	private PdfPTable body() {
		PdfPTable table = new PdfPTable(10);
		// header
		table.setHeaderRows(1);
		addRow(table, tableHeader());
		if (tankfuellungen == null || tankfuellungen.isEmpty()) {
			for (int i = 0; i < MAX_TABLE_ROWS - 1; i++) {
				addRow(table, tableEmptyRow());
			}
		} else {
			tankfuellungen.sort(tankfuellungen.get(0));
			for (Tankfuellung tankfuellung : tankfuellungen) {
				PdfPRow tableRow = tableRow(tankfuellung);
				addRow(table, tableRow);
			}
		}
		return table;
	}

	private void addRow(PdfPTable table, PdfPRow row) {
		for (PdfPCell cell : row.getCells()) {
			table.addCell(cell);
		}
		table.completeRow();
	}

	private PdfPRow tableRow(Tankfuellung tankfuellung) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(tankfuellung.getDatum());
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		PdfPCell datum = new PdfPCell(new Phrase(df.format(cal.getTime())));
		PdfPCell kmStand = new PdfPCell(new Phrase(String.valueOf(tankfuellung
				.getKmStand()) + " km"));
		PdfPCell kosten = new PdfPCell(
				new Phrase(CurrencyHelper.getCurrencyByGermany(
						tankfuellung.getKosten(), 2)));
		PdfPCell liter = new PdfPCell(new Phrase(
				NumberHelper.getGermanBigDecimalValue(tankfuellung.getLiter(),
						2, "l")));
		PdfPCell gefKm = new PdfPCell(new Phrase(String.valueOf(tankfuellung
				.getGefahreneKm()) + " km"));
		PdfPCell verbPro100Km = new PdfPCell(new Phrase(
				NumberHelper.getGermanBigDecimalValue(
						tankfuellung.getVerbrauch100Km(), 2, "l")));
		PdfPCell preisProLiter = new PdfPCell(new Phrase(
				CurrencyHelper.getCurrencyByGermany(
						tankfuellung.getPreisProLiter(), 3)));
		PdfPCell land = new PdfPCell(new Phrase("keine Angabe"));
		if (tankfuellung.getLand() != null) {
			land = new PdfPCell(new Phrase(tankfuellung.getLand().getName()));
		}
		PdfPCell ort = new PdfPCell(new Phrase("keine Angabe"));
		if (tankfuellung.getOrt() != null) {
			ort = new PdfPCell(new Phrase(tankfuellung.getOrt().getOrt()));
		}
		PdfPCell[] rowCells = { datum, kmStand, kosten, liter, gefKm,
				verbPro100Km, preisProLiter, land, ort };
		land.setColspan(2);
		ort.setColspan(2);
		return new PdfPRow(rowCells);
	}

	private PdfPRow tableEmptyRow() {
		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(40f);
		PdfPCell datum = new PdfPCell(cell);
		PdfPCell kmStand = new PdfPCell(cell);
		PdfPCell kosten = new PdfPCell(cell);
		PdfPCell liter = new PdfPCell(cell);
		PdfPCell gefKm = new PdfPCell(cell);
		PdfPCell verbPro100Km = new PdfPCell(cell);
		PdfPCell preisProLiter = new PdfPCell(cell);
		PdfPCell land = new PdfPCell(cell);
		land.setColspan(2);
		PdfPCell ort = new PdfPCell(cell);
		PdfPCell[] rowCells = { datum, kmStand, kosten, liter, gefKm,
				verbPro100Km, preisProLiter, land, ort };
		PdfPRow pdfPRow = new PdfPRow(rowCells);
		return pdfPRow;
	}

	private PdfPRow tableHeader() {
		Font font = FontFactory.getFont("Times-Roman");
		font.setStyle(Font.BOLD);
		PdfPCell datum = new PdfPCell(new Phrase("Datum", font));
		PdfPCell kmStand = new PdfPCell(new Phrase("Km-Stand", font));
		PdfPCell kosten = new PdfPCell(new Phrase("Kosten", font));
		PdfPCell liter = new PdfPCell(new Phrase("Liter", font));
		PdfPCell gefKm = new PdfPCell(new Phrase("gefahren", font));
		PdfPCell verbPro100Km = new PdfPCell(new Phrase("Verbrauch / 100km", font));
		PdfPCell preisProLiter = new PdfPCell(new Phrase("Preis pro Liter",
				font));
		PdfPCell land = new PdfPCell(new Phrase("Land", font));
		land.setColspan(2);
		PdfPCell ort = new PdfPCell(new Phrase("Ort", font));
		ort.setColspan(2);
		PdfPCell[] headerCells = { datum, kmStand, kosten, liter, gefKm,
				verbPro100Km, preisProLiter, land, ort };
		return new PdfPRow(headerCells);
	}
}
