package de.gw.auto.view.model;

public class MainViewModel {

	private TankenViewModel tankenViewModel;
	
	private SonstigeAusgabenView sonstigeAusgabenView;

	
	protected MainViewModel() {
		super();
	}

	public MainViewModel(TankenViewModel tankenViewModel, SonstigeAusgabenView sonstigeAusgabenView) {
		this();
		this.tankenViewModel = tankenViewModel;
		this.sonstigeAusgabenView = sonstigeAusgabenView;
	}

	public TankenViewModel getTankenViewModel() {
		return tankenViewModel;
	}

	public void setTankenViewModel(TankenViewModel tankenViewModel) {
		this.tankenViewModel = tankenViewModel;
	}

	public SonstigeAusgabenView getSonstigeAusgabenView() {
		return sonstigeAusgabenView;
	}

	public void setSonstigeAusgabenView(SonstigeAusgabenView sonstigeAusgabenView) {
		this.sonstigeAusgabenView = sonstigeAusgabenView;
	}
}
