package de.gw.auto.reports;

import java.util.List;

import de.gw.auto.domain.Settings;
import de.gw.auto.domain.Tanken;
import de.gw.auto.service.TankenService;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Report {

	private Settings setting;

	public Report(Settings setting) {

		this.setting = setting;
		build();

	}

	private void build(){
	
			JasperReportBuilder report = DynamicReports.report();
			report.columns(
					Columns.column("Datum", "datum", DynamicReports.type.dateType()),
					Columns.column("Benzinart", "benzinArt.name", DynamicReports.type.stringType()),
					Columns.column("Km-Stand", "kmStand", DynamicReports.type.integerType()),
					Columns.column("gefahren Km", "gefahreneKm", DynamicReports.type.integerType()),
					Columns.column("Verbrauch auf 100Km", "verbrauch100Km", DynamicReports.type.bigDecimalType()),
					Columns.column("Ort", "ort.ort", DynamicReports.type.stringType()),
					Columns.column("Land", "land.name", DynamicReports.type.stringType()),
					Columns.column("Tankinhalt", "tank.beschreibung", DynamicReports.type.stringType()),
					Columns.column("Liter", "liter", DynamicReports.type.bigDecimalType()),
					Columns.column("Preis pro Liter", "preisProLiter", DynamicReports.type.bigDecimalType()),
					Columns.column("Kosten", "kosten", DynamicReports.type.bigDecimalType())
					);			
			report.title(
					Components.text("Test")
					
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
		return new JRBeanCollectionDataSource(
				new TankenService(this.setting).getPrintList());
	}

}
