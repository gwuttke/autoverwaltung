var INIT = INIT || {};

INIT.field = {
	datePicker : function(inputId) {
		var $input = $('#' + inputId);

		$input.datepicker({
			format : "dd.mm.yyyy",
			language : 'de',
			todayBtn : 'linked'
		});
	},
	selectize : function(inputId, createFunction) {
		var $input = $('#' + inputId);
		$input.selectize({
			sortField : 'text',
			create : !createFunction ? false :createFunction
		});
	},
	numberic: function(inputId, data){
		var $input = $('#' + inputId);
		if(data.option){
			$input.autoNumeric('init', {
				mDec : data.option.mDec
			});
		}else{
			$input.autoNumeric('init');
		}
		
		if(data.value){
			$input.autoNumeric('set', data.value);	
		}
	}
	
};
	
$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
