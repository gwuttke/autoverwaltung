$(document).ready(
		function() {
			$('#inputTankenDatum').datepicker({
				format : "dd.mm.yyyy"
			});
			$('#selectLand').change(
					function() {
						$.ajax({
							url : urlLaender + $('#selectLand').val(),
							type : 'GET',
							dataType : 'json',
							success : function(json) {
								$("#selectOrt").empty();
								$.each(json, function(i, value) {
									$('#selectOrt').append(
											$('<option>').text(value.ort).attr(
													'value', value.id));
									$('#selectOrt').append('</option>');
								});
							}
						});
					});
		});