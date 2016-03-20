$(function() {
		
	$('#addAuto').click(function(){
		openModal('newAutoModal',urlNewAuto, function(){
			$('.date-picker').each(function(){
				$(this).datepicker({
					format : "dd.mm.yyyy",
					language:'de',
					todayBtn: 'linked'
				});
			});
		});
	});
	
	$('button[name="btnAuto"]').click(function(){
		var id = $(this).data('id');
		var token = $("meta[name='_csrf']").attr("content");
		$('#autoId').val(id);
		//$.post(urlUpdateCurrent, {});
		$.post(urlUpdateCurrent, {s: $('#currentAutoForm').serialize(),
			_csrf: token});
		//$("#currentAutoForm").ajaxForm({url: urlUpdateCurrent, type: 'post'});
		/*
		$.ajax({
	            type : "POST",
	            url : urlUpdateCurrent,
	            data : {autoModelShow: id},
	            success : function(response) {
	               
	            },
	            error : function(e) {
	               alert('Failed!: ' + e);
	            }
	        }); 
		*/
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


