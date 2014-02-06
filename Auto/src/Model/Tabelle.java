package Model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import domain.Text;
import Exception.AllException;

public class Tabelle implements TableModel {

	private AllException allExc = new AllException();
	private Text t = new Text();
	private String[] columns;
	private Object[][] data;

	public Tabelle(String[] columnNames, Object[][] obj) {
		if (columnNames.length == 0) {
			allExc.messageBox("keine Spalten Namen",
					"Wenden sie sich bitte an den Entwickler");
		}
		if (obj.length == 0) {
			data[0][1] = t.TABELLE_KEINE_DATEN;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}