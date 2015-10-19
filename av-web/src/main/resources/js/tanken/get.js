$(document).ready(function() {
	var urlNewTanken = '/user/tanken/new';
	$('#TankenAdd').click(function() {
		$.ajax({
			url : urlNewTanken,
			type : 'GET',
			success : function() {
				$('#newTankenModal').modal('show');
			}
		});
	});
});
