package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.SqlServer;
import domain.Auto;
import domain.Tanken;

public class TankenDao {

	private List<Tanken> tankenList = new ArrayList<Tanken>();
	private List<Tanken> autoTankenList = new ArrayList<Tanken>();

	private SqlServer sqlS = new SqlServer();
	private AutoDAO aDao = new AutoDAO();

	public List<Tanken> getTankenList() {
		if (tankenList != null) {
			return tankenList;
		} else {
			this.setTankenList();
			this.getAutoTankenList();
		}
		return tankenList;
	}

	public void setTankenList() {
		ResultSet rs = this.getTanken();
		try {
			while (rs.next()) {
				final Tanken tank = new Tanken();

				tank.setAutoId(rs.getInt("Auto_ID"));
				tank.setBenzinArtId(rs.getInt("BenzinArt"));
				tank.setDatum(rs.getDate("Datum"));
				tank.setKmStand(rs.getInt("Km_Stand"));
				tank.setKosten(rs.getDouble("Kosten"));
				tank.setLandId(rs.getInt("Land"));
				tank.setLiter(rs.getDouble("Liter"));
				tank.setOrtId(rs.getInt("Ort"));
				tank.setPreisProLiter(rs.getDouble("Preis_p_Liter"));

				tankenList.add(tank);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Tanken> getAutoTankenList() {
		return autoTankenList;
	}

	public void setAutoTankenList(String kfz) {
		int AutoID;
		for (Auto a : aDao.getAutoList())
			if (a.getKfz() == kfz) {
				AutoID = a.getId();
				for (Tanken t : tankenList) {
					if (t.getAutoId() == AutoID) {
						final Tanken tank = new Tanken();

						tank.setAutoId(t.getAutoId());
						tank.setBenzinArtId(t.getBenzinArtId());
						tank.setDatum(t.getDatum());
						tank.setKmStand(t.getKmStand());
						tank.setKosten(new Double(t.getKosten()));
						tank.setLandId(t.getLandId());
						tank.setLiter(new Double(t.getLiter()));
						tank.setOrtId(t.getOrtId());
						tank.setPreisProLiter(new Double(t.getPreisProLiter()));

						autoTankenList.add(tank);

					}

				}
				return;
			}
	}

	private ResultSet getTanken() {
		ResultSet tankenRS = null;

		String sql = "SELECT Km_Stand, Land, Ort, Voll, "
				+ "Kosten, Auto_ID, Datum, Liter, Preis_p_Liter, Benzinart FROM T_Tanken";

		try {
			tankenRS = sqlS.retrieveRS(sql);
			return tankenRS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tankenRS;

	}
}
