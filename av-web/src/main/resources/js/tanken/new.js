initNewTanken = function(){
	INIT.field.datePicker('inputTankenDatum');
	$('#inputTankenDatum').datepicker('setDate', new Date());
	
	INIT.field.selectize('selectLand', function (input, callback){
		var newData = {text: input}
		INIT.config.selectize.ajaxJSONCreateRequest(urlAddLand, input, newData, callback)
	});
	
	INIT.field.selectize('selectOrt', function (input, callback){
		var landID = $('#selectLand').val();
		var newData = {parent: landID, text: input}
		INIT.config.selectize.ajaxJSONCreateRequest(urlAddOrt, input, newData, callback)
	});

	INIT.field.selectize('selectBenzinart');
	INIT.field.selectize('selectFuellstaendet');

	updateOrte();
	
	$("body").on("change","#selectLand",function(){
		 updateOrte();
	});
	
	INIT.field.numberic('inputKosten', {value:0});
	INIT.field.numberic('inputLiter', {value:0});
	INIT.field.numberic('inputPreisProLiter', {option:{mDec:'3'},value:0});
	
	$("body").on("change","#inputKosten",function(){
		if(($('#inputLiter').val().length > 0) && ($(this).val().length > 0)){
			var preisProLiter = $(this).val() /$('#inputLiter').val() ;
			$('#inputPreisProLiter').autoNumeric('set',preisProLiter);
		}		
	});
	
	$("body").on("change","#inputLiter",function(){
		if($(this).val() != ''){
			if($('#inputKosten').val().length > 0){
				var preisProLiter = $(this).val() * $('#inputKosten').val();
				if(preisProLiter.toString().length === 5){
					$('#inputPreisProLiter').autoNumeric('set',preisProLiter);
					return;
				}
			}
			if( $('#inputPreisProLiter').val().length > 0){
				var kosten = $(this).val() * $('#inputPreisProLiter').val();
				$('#inputKosten').autoNumeric('set',kosten);
				return;
			}
		}
	});
	
	$("body").on("change","#inputPreisProLiter",function(){
		if(($('#inputLiter').val().length > 0) && ($(this).val().length > 0)){
			var kosten = $('#inputLiter').val() * $(this).val();
			$('#inputKosten').autoNumeric('set',kosten);
		}
	});
	 
	 $("body").on("click","#btnSaveTanken",function(){

		 if($('#selectLand').val() === undefined || $('#selectLand').val() === '' ){
			 $('#selectLand').val(0);
		 }
		 if($('#selectOrt').val() === undefined || $('#selectOrt').val() === '' ){
			 $('#selectOrt').val(0);
		 }
		 $('#newTankenForm').submit();
	});
};

updateOrte = function() {
	var landValue = $('#selectLand').val();
	if(landValue){
		$.ajax({
			url : urlLaender + landValue,
			type : 'GET',
			dataType : 'json',
			success : function(json) {
				var list = new Array();
				var ort = {text: 'keine Angabe',
						value: 0};
				list.push(ort);
				$.each(json, function(i, value) {
					var ort = {text: value.ort,
							value: value.id};
					list.push(ort);
				});
				var selectize = $("body").find("#selectOrt")[0].selectize;
				selectize.clear();
				selectize.clearOptions();
				selectize.load(function(callback) {
					callback(list);
				});
			}
		});
	}
};

$(function() {	
	$("body").on("click","#btnSaveTankenCancel",function(){
		$.ajax({url: urlSaveTankenCancel, 
			success: function(result){
				$("#tankenTable").html(result);
			}
		});
	});
	initNewTanken();
});

