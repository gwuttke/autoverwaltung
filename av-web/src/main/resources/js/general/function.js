/**
 * Klasse für Funktionen über JS
 */
var FUNCTION = FUNCTION || {};

/**
 * Sub-Class modal (Initialiesierung von Modal Fenstern (Overlays))
 * @memberOf   FUNCTION
 * @see     FUNCTION
 * @since   2.0.0
 */
FUNCTION.modal = {
	/**
	 * open a new Modal Window 
	 * @param: {String} id - ID des DIVs
	 * @param: {String} loadUrl - witch URL execute when its loaded, can be undefined
	 * @param: {function} callback - callback of th loadUrl, can be undefined
	 * @memberOf: {FUNCTION.modal}
	 */
	open : function(id, loadUrl, callback){
		var data = 'show';
		var $modal;
		if(loadUrl){
			$modal = $('#modalWindow');
			$modal.load(loadUrl, callback);
		}else{
			$modal = $('#'+id);
			if(callback){
				callback;
			}
		}
		$modal.on('hidden.bs.modal', function () {
			$(this).removeData('bs.modal');
			$(this).empty();
			$(this).removeAttr('style');
		});
		$modal.modal(data);
		$modal.trigger('modalReady');
	},
	close: function(id, callback){
		var isChild = true;
		if(id !== 'modalWindow'){
			if($("#modalWindow > #"+id )){
				isChild = true;
			}else{
				isChild = false;
			}
		}
		if(isChild){
			$('#modalWindow').modal('hide');
		}else{
			$('#'+id).modal('hide');
		}
		
		if(callback){
			callback;
		}
	}
	
};