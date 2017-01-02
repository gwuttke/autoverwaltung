$(function() {
		
	$('#addAuto').click(function(){
		FUNCTION.modal.open('newAutoModal',urlNewAuto, function(){
			$('.date-picker').each(function(){
				INIT.field.datePicker($(this).attr('id'));
			});
		});
	});
	
	$('button[name="btnAuto"]').click(function(){
		var id = $(this).data('id');
		var token = $("meta[name='_csrf']").attr("content");
		$('#autoId').val(id);
		$.post(urlUpdateCurrent, {s: $('#currentAutoForm').serialize(),
			_csrf: token});
	});
});
