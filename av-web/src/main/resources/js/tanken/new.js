$(function() {	
	$('#TankenAdd').click(function() {
		openModal('newTankenModal', urlNewTanken, function(){
			$('#inputTankenDatum').datepicker({
				format : "dd.mm.yyyy",
				language:'de'
			});
			$("input[name='isKosten']").bootstrapSwitch();
			
			$('#selectLand').change(
					function() {
						$.ajax({
							url : urlLaender + $('#selectLand').val(),
							type : 'GET',
							dataType : 'json',
							success : function(json) {
								$("#selectOrt").empty();
								$.each(json, function(i, value) {
									$('#selectOrt').append($('<option>').text(value.ort).attr('value', value.id));
									$('#selectOrt').append('</option>');
								});
							}
						});
					});
		
			$('#inputKosten').number(true,2);
			$('#inputLiter').number(true,2);
			$('#inputPreisProLiter').number(true,3);
					
			$('#inputKosten').change(function(){
				if(($('#inputLiter').val().length > 0) && ($(this).val().length > 0)){
					var preisProLiter = $(this).val() /$('#inputLiter').val() ;
					 $('#inputPreisProLiter').val(preisProLiter);
				}		
			});
			
			$('#inputLiter').change(function(){
				if($(this).val() != ''){
					if($('#inputKosten').val().length > 0){
						var preisProLiter = $(this).val() * $('#inputKosten').val();
						 $('#inputPreisProLiter').val(preisProLiter);
						return;
					}
					if( $('#inputPreisProLiter').val().length > 0){
						var kosten = $(this).val() *  $('#inputPreisProLiter').val();
						$('#inputKosten').val(kosten);
						return;
					}
				} 
				
			});
			
			 $('#inputPreisProLiter').change(function(){
				if(($('#inputLiter').val().length > 0) && ($(this).val().length > 0)){
					var kosten = $('#inputLiter').val() * $(this).val();
					$('#inputKosten').val(kosten);
				}
				
			});
		});	
	});	
});

