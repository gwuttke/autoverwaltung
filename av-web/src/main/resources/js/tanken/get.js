$(function() {
	
	$('#addAuto').click(function(){
		openModal('newAutoModal',urlNewAuto, function(){
			$('.date-picker').each(function(){
				$(this).datepicker({
					format : "dd.mm.yyyy",
					language:'de'
				});
			});
		});
	});
	
	$('a[name="auto"]').click(function(){
		autoModelShow = {
        		id : $(this).data('value')
        }
		 $.ajax({
	            type : "POST",
	            url : urlUpdateCurrent,
	            data : autoModelShow,
	            success : function(response) {
	               
	            },
	            error : function(e) {
	               alert('Failed!: ' + e);
	            }
	        }); 
		
	});
	
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


