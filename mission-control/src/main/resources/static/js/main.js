function flipPin(button) {

	fire_ajax_submit(button);

}

function fire_ajax_submit(button) {

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/" + button.name,
		data : null,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			jQuery.each(data, function(i, val) {

				$('button[name=' + i + ']').html(val);
				//console.log(i, $('button[name=' + i + ']').css());

			});
			
		},
		error : function(e) {
			console.log("ERROR : ", e);
		}
	});

}
