$(function() {	
	$('#TankenAdd').click(function() {
		FUNCTION.modal.open('newTankenModal', urlNewTanken, function(){
			initNewTanken();
		});	
	});
	
	$('#addSonstigeAusgaben').click(function() {
		FUNCTION.modal.open('newSonstigeAusgabenModal', urlNewSonstigeAusgaben, function(){
			initNewSonstigeAusgaben();
		});	
	});
});

