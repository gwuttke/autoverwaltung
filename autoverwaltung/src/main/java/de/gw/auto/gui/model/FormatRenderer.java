package de.gw.auto.gui.model;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;

/*
 * Code From: http://tips4java.wordpress.com/2008/10/11/table-format-renderers/  
 *	Use a formatter to format the cell Object
 */
public class FormatRenderer extends DefaultTableCellRenderer {
	private Format formatter;

	/*
	 * Use the specified formatter to format the Object
	 */
	public FormatRenderer(Format formatter) {
		this.formatter = formatter;
	}

	public void setValue(Object value) {
		// Format the Object before setting its value in the renderer

		try {
			if (value != null)
				value = formatter.format(value);
		} catch (IllegalArgumentException e) {
		}

		super.setValue(value);
	}


	/*
	 * Use the default date formatter for the default locale
	 */
	public static FormatRenderer getDateRenderer() {
		return new FormatRenderer(new SimpleDateFormat("EEE, dd MMM yyyy"));
	}
	
	
	/*
	 * Use the default date/time formatter for the default locale
	 */
	public static FormatRenderer getDateTimeRenderer() {
		return new FormatRenderer(DateFormat.getDateTimeInstance());
	}

	/*
	 * Use the default time formatter for the default locale
	 */
	public static FormatRenderer getTimeRenderer() {
		return new FormatRenderer(DateFormat.getTimeInstance());
	}
}
