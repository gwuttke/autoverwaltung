$(document).ready(function() {
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
