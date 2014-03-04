package Model;

import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Texte;
import Exception.AllException;

public class Tabelle implements TableModel {

	private Vector<TableModelListener> listener = new Vector<TableModelListener>();
	private Texte.Tabelle textTabelle = new Texte.Tabelle();
	private String[] columns;
	private Object[][] data;

	public Tabelle(String[] columnNames, Object[][] obj) {
		if (columnNames.length == 0) {
			AllException.messageBox("keine Spalten Namen",
					"Wenden sie sich bitte an den Entwickler");
		}
		if (obj.length == 0) {
			data[0][1] = textTabelle.KEINE_DATEN;
		}

		this.columns = columnNames;
		this.data = obj;
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
		return data[columnIndex][0].getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
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
