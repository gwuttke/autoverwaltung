/**
 * Klasse für initialisierungen von Komponenten über JS
 */
var INIT = INIT || {};

/**
 * Sub-Class field (Initialiesierung von Feldern (z.b. input, datePicker, select)
 * @memberOf   INIT
 * @see     INIT
 * @since   2.0.0
 */
INIT.field = {
	/**
	 * DatePicker initialisierung 
	 * @param: {String} inputId - ID des Input Feldes
	 * @memberOf: {INIT.field}
	 */
	datePicker : function(inputId) {
		var $input = $('#' + inputId);

		$input.datepicker({
			format : "dd.mm.yyyy",
			language : 'de',
			todayBtn : 'linked'
		});
	},
	/**
	 * Selectize initialisierung 
	 * @param: {String} inputId - ID des Input Feldes
	 * @param: {function} createFunction(input, callback) - function for new Option creation, if {undefined} creation is {false}
	 * @memberOf: {INIT.field}
	 */
	selectize : function(inputId, createFunction) {
		var $input = $('#' + inputId);
		$input.selectize({
			sortField : 'text',
			create : !createFunction ? false :createFunction
		});
	},
	/**
	 * Numeric initialisierung 
	 * @param: {String} inputId - ID des Input Feldes
	 * @param: {Object} data - allowed variables are {value}, {option{mDec as {String}}}
	 * @Bsp. aufruf: INIT.field.numberic('inputPreisProLiter', {option:{mDec:'3'},value:0});  
	 * @memberOf: {INIT.field}
	 */
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

/**
 * Configuration after Init.field
 */
INIT.config = {}
/**
 * Config for Selectize
 */
INIT.config.selectize = {
		/**
		 * ajax JSON create Request 
		 */
		ajaxJSONCreateRequest: function(url,input, newData,callback){
			$.ajax({
				type : "POST",
				url : url,
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
}
	
$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});
