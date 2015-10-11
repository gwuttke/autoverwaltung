package de.gw.auto.view.model.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import de.gw.auto.domain.Auto;
import de.gw.auto.domain.Tankfuellung;
import de.gw.auto.service.implementation.TankenService;
import de.gw.auto.view.model.NewTanken;
import de.gw.auto.view.model.TankenModel;
import de.gw.auto.view.model.TankenViewModel;

@Component
public class TankenModelHelper {
	@Autowired
	private TankenService tankenService;

	public TankenViewModel prepareTankViewModel(
			TankenViewModel tankenViewModel, Auto auto, int page) {
		if (auto == null) {
			return tankenViewModel;
		}
		DateTime kaufDatum = new DateTime(auto.getKauf().getTime());
		int currentYear = DateTime.now().getYear();
		double[] kosten = new double[3];
		double[] liter = new double[3];
		double[] minPreisProLiter = new double[3];
		double[] maxPreisProLiter = new double[3];
		double[] avgPreisProLiter = new double[3];
		int[] km = new int[3];
		kosten[0] = tankenService.getKostenOfYear(currentYear, auto);
		kosten[1] = tankenService.getKostenOfYear(currentYear - 1, auto);
		kosten[2] = tankenService.getAllKosten(auto);
		liter[0] = tankenService.getLiterOfYear(currentYear, auto);
		liter[1] = tankenService.getLiterOfYear(currentYear - 1, auto);
		liter[2] = tankenService.getAllLiter(auto);
		maxPreisProLiter[0] = tankenService.getMaxPreisProLiter(currentYear,
				auto);
		maxPreisProLiter[1] = tankenService.getMaxPreisProLiter(
				currentYear - 1, auto);
		maxPreisProLiter[2] = tankenService.getMaxPreisProLiter(auto);
		minPreisProLiter[0] = tankenService.getMinPreisProLiter(currentYear,
				auto);
		minPreisProLiter[1] = tankenService.getMinPreisProLiter(
				currentYear - 1, auto);
		minPreisProLiter[2] = tankenService.getMinPreisProLiter(auto);
		avgPreisProLiter[0] = tankenService.getAVGPreisLiter(currentYear, auto);
		avgPreisProLiter[1] = tankenService.getAVGPreisLiter(currentYear - 1,
				auto);
		avgPreisProLiter[2] = tankenService.getAVGPreisLiter(auto);
		km[0] = tankenService.getKmOfYear(currentYear, auto);
		km[1] = tankenService.getKmOfYear(currentYear - 1, auto);
		km[2] = tankenService.getAllKm(auto);

		
		setPagination(tankenViewModel, auto, page);
	
		tankenViewModel.setKosten(kosten);
		tankenViewModel.setLiter(liter);
		tankenViewModel.setKm(km);
		tankenViewModel.setMaxPreisProLiter(maxPreisProLiter);
		tankenViewModel.setMinPreisProLiter(minPreisProLiter);
		tankenViewModel.setAvgPreisProLiter(avgPreisProLiter);
		
		return tankenViewModel;
	}

	private void setPagination(TankenViewModel tankenViewModel, Auto auto, int page) {
		List<Tankfuellung> tankfuellungen = tankenService.getTankfuellungen(
				auto);
		List<TankenModel> tankenModels = new ArrayList<TankenModel>();
		for (Tankfuellung tfuellung : tankfuellungen) {
			tankenModels.add(new TankenModel(tfuellung));
		}
		tankenModels.sort(new Comparator<TankenModel>() {

			@Override
			public int compare(TankenModel o1, TankenModel o2) {
				return o2.getKmStand() - o1.getKmStand();
			}
		});
		PagedListHolder<TankenModel> pages = new PagedListHolder<TankenModel>(
				tankenModels, new MutableSortDefinition("getKmStand()", true, false));
		pages.setPage(page);
		tankenViewModel.setTankfuellungenView(pages);
	}
}
