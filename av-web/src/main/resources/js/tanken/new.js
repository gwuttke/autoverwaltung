$(function() {	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	$('#TankenAdd').click(function() {
		openModal('newTankenModal', urlNewTanken, function(){
			initNewTanken();
		});	
	});
	
	$("body").on("click","#btnSaveTankenCancel",function(){
		window.location = urlSaveTankenCancel;
	});
		
		
	initNewTanken = function(){
		$('#inputTankenDatum').datepicker({
			format : "dd.mm.yyyy",
			language:'de',
			todayBtn: 'linked'
		});
		$('#inputTankenDatum').datepicker('setDate', new Date());
		
		$('#selectLand').selectize({
			sortField: 'text',
			create:function (input, callback){
				var newData = {text: input}
				$.ajax({
					type : "POST",
					url : urlAddLand,
					contentType: "application/json; charset=utf-8",
					dataType: 'json',
					data: JSON.stringify(newData),
					success : function(result) {
						if (result>0) {
							callback({ value: result, text: input });
						}
					},
					error : function(e) {
						alert('Failed!: ' + e);
					}
				});
			}
		});
		
		$('#selectOrt').selectize({
			sortField: 'text',
			create:function (input, callback){
				var landID = $('#selectLand').val();
				var newData = {parent: landID, text: input}
				$.ajax({
					type : "POST",
					url : urlAddOrt,
					contentType: "application/json; charset=utf-8",
					dataType: 'json',
					data: JSON.stringify(newData),
					success : function(result) {
						if (result>0) {
							callback({ value: result, text: input });
						}
					},
					error : function(e) {
						alert('Failed!: ' + e);
					}
				});
			}
		});
		
		$('#selectBenzinart').selectize({
			create: false,
			sortField: 'text'
		});
		
		$('#selectFuellstaendet').selectize({
			create: false,
			sortField: 'text'
		});
						
		updateOrte();
		
		$("body").on("change","#selectLand",function(){
			 updateOrte();
		});

		$('#inputKosten').autoNumeric('init');
		$('#inputLiter').autoNumeric('init');
		$('#inputPreisProLiter').autoNumeric('init', {mDec:'3'});
		$('#inputKosten').autoNumeric('set', 0);
		$('#inputLiter').autoNumeric('set', 0);
		$('#inputPreisProLiter').autoNumeric('set', 0);
		
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
		$.ajax({
			url : urlLaender + $('#selectLand').val(),
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
	};
	
	initNewTanken();
});

