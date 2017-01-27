package de.gw.auto.view.model.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.stereotype.Component;

import com.lowagie.text.DocumentException;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.SonstigeAusgaben;
import de.gw.auto.service.RegisteredUser;
import de.gw.auto.service.implementation.SonstigeAusgabenService;
import de.gw.auto.service.implementation.StammdatenService;
import de.gw.auto.service.implementation.TankfuellungenPdf;
import de.gw.auto.view.model.NewSonstigeAusgaben;
import de.gw.auto.view.model.SonstigeAusgabenView;

@Component
public class SonstigeAusgabenModelHelper {
	@Autowired
	private SonstigeAusgabenService sonstigeAusgabenService;

	@Autowired
	private TankfuellungenPdf tankfuellungenPdf;

	@Autowired
	private StammdatenService stammdatenService;

	public void prepare(NewSonstigeAusgaben newSonstigeAusgaben, Auto auto) {
		if(newSonstigeAusgaben == null){
			 newSonstigeAusgaben = new NewSonstigeAusgaben();
		}
		newSonstigeAusgaben.setAuto(auto);
		newSonstigeAusgaben.setLaender(stammdatenService.getLaender());
	}
	
	public SonstigeAusgabenView prepare(SonstigeAusgabenView tankenViewModel, RegisteredUser user, int page) {
		if (user == null) {
			return tankenViewModel;
		}
		SonstigeAusgabenView sav = setPagination(user, page);
		
		return (sav == null ? tankenViewModel : sav);
	}

	private SonstigeAusgabenView setPagination(RegisteredUser user, int page) {
		List<SonstigeAusgaben> sonstigeAusgaben = sonstigeAusgabenService.get(user);
		if (sonstigeAusgaben.isEmpty()) {
			return null;
		}

		SonstigeAusgabenView sav = new SonstigeAusgabenView(sonstigeAusgaben);
		sav.getPages().setSort(new MutableSortDefinition("getKmStand()", true, false));
		sav.getPages().setPage(page);
		return sav;
	}

	public byte[] createEmptyPDF(final Auto auto) throws DocumentException {
		tankfuellungenPdf.setTankfuellungen(null);
		return tankfuellungenPdf.createAndGetBytes().toByteArray();
	}

	public byte[] createPDF(final Auto auto) throws DocumentException {
		/*List<Tankfuellung> tankfuellungen = tankenService.getTankfuellungen(auto);
		tankfuellungenPdf.setTankfuellungen(tankfuellungen);
		return tankfuellungenPdf.createAndGetBytes().toByteArray();
		*/
		return null;
	}
}
