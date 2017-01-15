initNewSonstigeAusgaben = function(){
	INIT.field.datePicker('inputDatum');
	$('#inputDatum').datepicker('setDate', new Date());
	INIT.field.numberic('inputKosten', {value:0});
};

$(function() {	
	$("body").on("click","#btnCancelSonstigeAusgaben",function(){
		window.location = urlSaveSonstigeAusgabenCancel;
	});
	$("body").on("click","#btnSaveSonstigeAusgaben",function(){
		$('#newSonstigeAusgabenForm').submit();
	});
	initNewSonstigeAusgaben();
});

