package de.gw.auto.gui.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class PageWrapper implements Printable {

	private Printable delegate;
	private int localPageIndex;


	public PageWrapper(Printable delegate, int pageIndex) {
		this.localPageIndex = pageIndex;
		this.delegate = delegate;
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		return delegate.print(graphics, pageFormat, localPageIndex);
	}

	public int getNumberOfPages(PageFormat pageFormat) throws PrinterException {

		Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
				.createGraphics();
		int numPages = 0;
		while (true) {
			int result = delegate.print(g, pageFormat, numPages);
			if (result == 0) {
				++numPages;
			} else {
				break;
			}
		}

		return numPages;

		/*
		 * Graphics g = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
		 * .createGraphics(); int numPages = 0;
		 * 
		 * while (true) { int result = this.print(g, pageFormat, numPages); if
		 * (result == Printable.PAGE_EXISTS) { ++numPages; } else { break; } }
		 * 
		 * return numPages;
		 */
	}
	
	

	public static BufferedImage createImage(JTable table) {
		JTableHeader tableHeaderComp = table.getTableHeader();
		int totalWidth = tableHeaderComp.getWidth() + table.getWidth();
		int totalHeight = tableHeaderComp.getHeight() + table.getHeight();
		BufferedImage tableImage = new BufferedImage(totalWidth, totalHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = (Graphics2D) tableImage.getGraphics();
		tableHeaderComp.paint(g2D);
		g2D.translate(0, tableHeaderComp.getHeight());
		table.paint(g2D);
		return tableImage;
	}
}