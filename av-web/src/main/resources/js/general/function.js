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
		var data = {show:true, backdrop:'static'};
		if(!loadUrl){
			$('#modelWindow').modal(data).load(loadUrl,callback);
		}else{
			$('#'+id).modal(data);
		}
		$('#'+id).trigger('modalReady');
	}
};