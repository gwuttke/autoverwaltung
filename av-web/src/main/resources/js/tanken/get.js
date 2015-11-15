$(function() {
	$('#TankenAdd').click(function() {
		openModal('newTankenModal', urlNewTanken, function(){
			$('#inputTankenDatum').datepicker({
				format : "dd.mm.yyyy",
				language:'de'
			});
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
		});
	});
	
	openModal = function(id, loadUrl, callback){
		if(loadUrl != null){
			$('#modelWindow').modal({show:true, backdrop:'static'}).load(loadUrl,callback);
		}else{
			$('#'+id).modal({show:true, backdrop:'static'});
		}
		$('#'+id).trigger('modalReady');
	}
});


