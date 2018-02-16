package de.gw.auto.view.model.helper;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;

import com.itextpdf.text.DocumentException;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.service.implementation.TankenService;
import de.gw.auto.service.implementation.TankfuellungenPdf;
import de.gw.auto.view.model.AuswertungProJahr;
import de.gw.auto.view.model.TankenModel;
import de.gw.auto.view.model.TankenViewModel;

@Component
public class TankenModelHelper {
	@Autowired
	private TankenService tankenService;

	@Autowired
	private TankfuellungenPdf tankfuellungenPdf;

	public TankenViewModel prepareTankViewModel(
			TankenViewModel tankenViewModel, Auto auto, int page) {
		if (auto == null) {
			return tankenViewModel;
		}
		DateTime kaufDatum = new DateTime(auto.getKauf().getTime());
		int currentYear = DateTime.now().getYear();
		
		List<AuswertungProJahr> auswertungProJahre = new ArrayList<AuswertungProJahr>();
		
		for (int year = currentYear; year >= currentYear-3;year--){
			AuswertungProJahr auswertung = new AuswertungProJahr();
			auswertung.setJahr(year);
			auswertung.setLiter(getLiter(year, auto));
			auswertung.setKosten(getKosten(year, auto));
			auswertung.setKm(getKm(year, auto));
			auswertung.setPreisProLiterMin(getPreisProLiterMin(year, auto));
			auswertung.setPreisProLiterMax(getPreisProLiterMax(year, auto));
			auswertung.setPreisProLiterAvg(getPreisProLiterAvg(year, auto));
			auswertungProJahre.add(auswertung);
		}
		setPagination(tankenViewModel, auto, page);
		tankenViewModel.setAuswertungProJahre(auswertungProJahre);
		
		return tankenViewModel;
	}


	
	/**
	 * 
	 * @param year
	 * @param auto
	 * @return Kilomenter eines Jahres vom angegebenen Auto
	 */
	private Integer getKm(int year, Auto auto) {
		return tankenService.getKmOfYear(year, auto);
	}

	/**
	 * 
	 * @param year
	 * @param auto
	 * @return  Kosten eines Jahres vom angegebenen Auto;
	 */
	private Double getKosten(int year, Auto auto) {
			return tankenService.getKostenOfYear(year, auto);
	}
	
	/**
	 * 
	 * @param year
	 * @param auto
	 * @return Liter eines Jahres vom angegebenen Auto;
	 */
	private Double getLiter(int year, Auto auto) {
			return tankenService.getLiterOfYear(year, auto);
	}
	
	/**
	* 
	* @param year
	* @param auto
	* @return  Minimum Preis Pro Liter eines Jahres vom angegebenen Auto;
	*/
	private Double getPreisProLiterMin(int year, Auto auto) {
			return tankenService.getMinPreisProLiter(year, auto);
	}
	
	/**
	* 
	* @param year
	* @param auto
	* @return  Minimum Preis Pro Liter eines Jahres vom angegebenen Auto;
	*/
	private Double getPreisProLiterAvg(int year, Auto auto) {
			return tankenService.getAvgPreisProLiter(year, auto);
	}	
	
	/**
	* 
	* @param year
	* @param auto
	* @return  Maximum Preis Pro Liter eines Jahres vom angegebenen Auto;
	*/
	private Double getPreisProLiterMax(int year, Auto auto) {
			return tankenService.getMaxPreisProLiter(year, auto);
	}	


	private void setPagination(TankenViewModel tankenViewModel, Auto auto,
			int page) {
		List<Tankfuellung> tankfuellungen = tankenService
				.getTankfuellungen(auto);
		if(tankfuellungen.isEmpty()){
			return;
		}
		List<TankenModel> tankenModels = new ArrayList<TankenModel>();
		for (Tankfuellung tfuellung : tankfuellungen) {
			tankenModels.add(new TankenModel(tfuellung));
		}
		tankenModels.sort(tankenModels.get(0));
		PagedListHolder<TankenModel> pages = new PagedListHolder<TankenModel>(
				tankenModels, new MutableSortDefinition("getKmStand()", true,
						false));
		pages.setPage(page);
		pages.setMaxLinkedPages(3);
		tankenViewModel.setTankfuellungenView(pages);
	}


	public byte[] createEmptyPDF(final Auto auto) throws DocumentException{
		tankfuellungenPdf.setTankfuellungen(null);
		return tankfuellungenPdf.createAndGetBytes().toByteArray();
	}
	
	public byte[] createPDF(final Auto auto) throws DocumentException{
		List<Tankfuellung> tankfuellungen = tankenService
				.getTankfuellungen(auto);
		tankfuellungenPdf.setTankfuellungen(tankfuellungen);
		return tankfuellungenPdf.createAndGetBytes().toByteArray();
	}
}
