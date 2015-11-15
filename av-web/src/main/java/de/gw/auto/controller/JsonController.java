package de.gw.auto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.gw.auto.dao.LandDao;
import de.gw.auto.domain.Land;
import de.gw.auto.domain.Ort;

@Controller
@RequestMapping("user/json")
public class JsonController {
	@Autowired
	private LandDao landDao;

	@RequestMapping(value = "/laender", method = RequestMethod.GET)
	public @ResponseBody List<Land> laender() {
		return landDao.getLaenderAlphabetisch();
	}

	@RequestMapping(value = "/orte", params = "land", method = RequestMethod.GET)
	public @ResponseBody List<Ort> orteByLand(@RequestParam("land") int land) {
		Land l = landDao.searchLand(land);
		return landDao.getOrteByLand(l);
	}
}
