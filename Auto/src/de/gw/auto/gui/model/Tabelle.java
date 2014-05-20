package de.gw.auto.gui.model;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.gw.auto.domain.Texte;
import de.gw.auto.exception.AllException;

public class Tabelle implements TableModel {

	private Vector<TableModelListener> listener = new Vector<TableModelListener>();
	private Texte.Tabelle textTabelle = new Texte.Tabelle();
	private String[] columns;
	private Object[][] data;

	public Tabelle(String[] columnNames, Object[][] data) {
		if (columnNames.length == 0) {
			AllException.messageBox("keine Spalten Namen",
					"Wenden sie sich bitte an den Entwickler");
		}
		if (data.length == 0) {
			data[0][1] = textTabelle.KEINE_DATEN;
		}

		this.columns = columnNames;
		this.data = data;

	}

	public JTable getJTable() {
		JTable jT = new JTable(this);

		return jT;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		try {
			if (getRowCount() == 0) {
				return Object.class;
			} else {
				Object cellValue = getValueAt(0, columnIndex);
				return cellValue.getClass();
			}
		} catch (Exception e) {
			return Object.class;
		}

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listener.add(l);

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listener.remove(l);

	}
}
