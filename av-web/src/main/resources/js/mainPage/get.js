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
	
	$('a[name="TankenDelete"]').click(function(){
		var id = $(this).data("dbid");
		var token = $("meta[name='_csrf']").attr("content");
		bootbox.confirm("Sind Sie wirklich Sicher, dass Sie diesen Eintrag löschen wollen?", function(result){
		   if(result){
			   $.post(urlDeleteTanken, {id: id,	_csrf: token}, function(data){
					$('body').html(data);
				});
		   }
		});
	});
	
	$('#tankfuellungenTab').click(function(){
		$(this).tab('show')
	});
	
	$('#sonstigeAusgabenTab').click(function(){
		$(this).tab('show')
	});
});

