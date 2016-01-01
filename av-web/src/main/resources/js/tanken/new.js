$(function() {	
	$('#TankenAdd').click(function() {
		openModal('newTankenModal', urlNewTanken, function(){
			$('#inputTankenDatum').datepicker({
				format : "dd.mm.yyyy",
				language:'de'
			});
			$('#selectLand').selectize({
			    create: false,
			    sortField: 'text'
			});
			
			$('#selectOrt').selectize({
			    create: false,
			    sortField: 'text'
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
			
			$('#selectLand').change(function(){updateOrte();});
		
			$('#inputKosten').autoNumeric('init');
			$('#inputLiter').autoNumeric('init');
			$('#inputPreisProLiter').autoNumeric('init', {mDec:'3'});
								
			$('#inputKosten').change(function(){
				if(($('#inputLiter').val().length > 0) && ($(this).val().length > 0)){
					var preisProLiter = $(this).val() /$('#inputLiter').val() ;
					$('#inputPreisProLiter').autoNumeric('set',preisProLiter);
				}		
			});
			
			$('#inputLiter').change(function(){
				if($(this).val() != ''){
					if($('#inputKosten').val().length > 0){
						var preisProLiter = $(this).val() * $('#inputKosten').val();
						$('#inputPreisProLiter').autoNumeric('set',preisProLiter);
						return;
					}
					if( $('#inputPreisProLiter').val().length > 0){
						var kosten = $(this).val() *  $('#inputPreisProLiter').val();
						$('#inputKosten').autoNumeric('set',kosten);
						return;
					}
				} 
				
			});
			
			 $('#inputPreisProLiter').change(function() {
				if(($('#inputLiter').val().length > 0) && ($(this).val().length > 0)){
					var kosten = $('#inputLiter').val() * $(this).val();
					$('#inputKosten').autoNumeric('set',kosten);
				}
				
			});
			 
			 $('#btnSaveTanken').click(function(){
				$('#newTankenForm').submit();
				// $.post(urlSaveTanken,$('#newTankenForm').serialize());
			});
		});
	});
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
				var selectize = $("#selectOrt")[0].selectize;
				selectize.clear();
				selectize.clearOptions();
				selectize.load(function(callback) {
				    callback(list);
				});
				/*
				$('#selectOrt').append($('<option>').text('keine Angabe').attr('value', '0'));
				$('#selectOrt').append('</option>');
				$.each(json, function(i, value) {
					$('#selectOrt').append($('<option>').text(value.ort).attr('value', value.id));
					$('#selectOrt').append('</option>');
				});
				*/
			}
		});
	};
	
});

