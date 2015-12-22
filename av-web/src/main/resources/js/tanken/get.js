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
	
	
	openModal = function(id, loadUrl, callback){
		if(loadUrl != null){
			$('#modelWindow').modal({show:true, backdrop:'static'}).load(loadUrl,callback);
		}else{
			$('#'+id).modal({show:true, backdrop:'static'});
		}
		$('#'+id).trigger('modalReady');
	}
});


