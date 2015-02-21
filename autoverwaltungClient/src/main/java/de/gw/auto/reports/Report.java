package de.gw.auto.reports;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import de.gw.auto.domain.Settings;
import de.gw.auto.service.TankenService;

@Controller
public class Report {

	@Autowired
	private TankenService tankenService;

	private Settings setting;

	protected Report() {
	}

	public void init(Settings settings) {
		this.setting = setting;
	}

	public void build() {

		JasperReportBuilder report = DynamicReports.report();
		report.columns(Columns.column("Datum", "datum",
				DynamicReports.type.dateType()), Columns.column("Benzinart",
				"benzinArt.name", DynamicReports.type.stringType()), Columns
				.column("Km-Stand", "kmStand",
						DynamicReports.type.integerType()),
				Columns.column("gefahren Km", "gefahreneKm",
						DynamicReports.type.integerType()), Columns.column(
						"Verbrauch auf 100Km", "verbrauch100Km",
						DynamicReports.type.bigDecimalType()), Columns.column(
						"Ort", "ort.ort", DynamicReports.type.stringType()),
				Columns.column("Land", "land.name",
						DynamicReports.type.stringType()), Columns.column(
						"Tankinhalt", "tank.beschreibung",
						DynamicReports.type.stringType()),
				Columns.column("Liter", "liter",
						DynamicReports.type.bigDecimalType()), Columns.column(
						"Preis pro Liter", "preisProLiter",
						DynamicReports.type.bigDecimalType()), Columns.column(
						"Kosten", "kosten",
						DynamicReports.type.bigDecimalType()));
		report.title(Components.text("Test")

		);
		report.setDataSource(getDatasource());
		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);
		try {
			report.show();
		} catch (DRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JRDataSource getDatasource() {
		tankenService.init(this.setting);

		return new JRBeanCollectionDataSource(tankenService.getPrintList());
	}

}
