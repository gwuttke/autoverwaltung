package dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlAbfrage;
import database.SqlServer;
import domain.Settings;
import domain.Tanken;

public class TankenDao extends SqlServer {

	private static OrtDao oDao = new OrtDao();
	private static LandDao lDao = new LandDao();
	private static BenzinartDAO bDao = new BenzinartDAO();
	private static TankDAO tDao = new TankDAO();
	private List<Tanken> tankenList = new ArrayList<Tanken>();


	public List<Tanken> getTankenList() {
		if (this.tankenList.isEmpty()){
			return null;
		}
		return tankenList;
	}

	
	public void setTankenList(Settings setting) throws SQLException {
		SqlAbfrage abfrage = new SqlAbfrage(setting);
		
		ResultSet rsTanken = retrieveRS(abfrage.getTanken());

		try {
			while (rsTanken.next()) {
				Tanken t = new Tanken();

				t.setAutoId(rsTanken.getInt("Auto_ID"));
				t.setBenzinArt(bDao.getBenzinart(rsTanken.getInt("BenzinartID")));
				t.setKmStand(rsTanken.getInt("Km_Stand"));
				t.setKosten((BigDecimal) rsTanken.getBigDecimal("Kosten"));
				t.setLand(lDao.getLand(rsTanken.getInt("Land")));
				t.setLiter(rsTanken.getBigDecimal("Liter"));
				t.setOrt(oDao.getOrt(rsTanken.getInt("Ort")));
				t.setPreisProLiter(rsTanken.getBigDecimal("Preis_p_Liter"));
				t.setTank(tDao.getTank(rsTanken.getInt("Voll")));
				this.tankenList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
