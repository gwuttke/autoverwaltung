package de.gw.auto.service;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Settings;
import de.gw.auto.gui.PrintPreview;
import de.gw.auto.reports.Report;

public class Drucken {

	private Settings settings = null;
	HashPrintRequestAttributeSet att = new javax.print.attribute.HashPrintRequestAttributeSet();

	public Drucken(Settings settings) {
		this.settings = settings;
	}

	public void printReport() {
		new Report(settings);
	}

	public void print(JTable[] tables) {
		Auto a = settings.getAktuellAuto();
		MessageFormat header = new MessageFormat(MessageFormat.format(
				"Kennzeichen: {0} Benzinarten: {1}", new Object[] { a.getKfz(),
						a.getBenzinarten() }));

		PrinterJob printer = PrinterJob.getPrinterJob();

		// Set 1/2 " margins and orientation
		PageFormat pf = printer.defaultPage();
		pf.setOrientation(PageFormat.LANDSCAPE);
		Paper paper = new Paper();
		double margin = 36; // half inch
		paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2,
				paper.getHeight() - margin * 2);
		pf.setPaper(paper);

		Book printJob = new Book();

		for (JTable t : tables) {
			printJob.append(t.getPrintable(PrintMode.FIT_WIDTH, header, null),
					pf);

			/*
			 * new PrintPreview(t.getPrintable( JTable.PrintMode.FIT_WIDTH, new
			 * MessageFormat( "Auto"), new MessageFormat("{0}")),
			 * printer.getPageFormat(att));
			 */
		}

		printer.setPageable(printJob);

		new PrintPreview(printJob, printer.getPageFormat(att));

		System.out.println(printJob.getNumberOfPages());

	}
}
