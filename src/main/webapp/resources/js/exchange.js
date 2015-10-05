
$(document).ready(function() {
	$('#form-wallet-new').on('submit', function(e) {
		e.preventDefault();
		$('#btn-wallet-new-save').click();
	});
});

$(document).ready(function() {
	$('#btn-wallet-new-save').on('click', function(e) {
		$('#modal-wallet-new').modal('toggle');
		var targetForm = $('#form-wallet-new');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Wallet created successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to create the specified wallet... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});

$(document).ready(function() {
	$('#form-wallet-rename').on('submit', function(e) {
		e.preventDefault();
		$('#btn-wallet-rename-save').click();
	});
});

$(document).ready(function() {
	$('#btn-wallet-rename-save').on('click', function(e) {
		$('#modal-wallet-rename').modal('toggle');
		var targetForm = $('#form-wallet-rename');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Wallet renamed successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to rename the specified wallet... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});

$(document).ready(function() {
	$('#wallet-table').on('click', 'a.btn-wallet-archive', function(e) {
		e.preventDefault();
		var targetForm = $(this).siblings('#form-wallet-archive');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Wallet archived successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to archive the specified wallet... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});

$(document).ready(function() {
	$('#form-address-new').on('submit', function(e) {
		e.preventDefault();
		$('#btn-address-new-save').click();
	});
});

$(document).ready(function() {
	$('#btn-address-new-save').on('click', function(e) {
		$('#modal-address-new').modal('toggle');
		var targetForm = $('#form-address-new');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Address created successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to create the specified address... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});

$(document).ready(function() {
	$('#form-address-relabel').on('submit', function(e) {
		e.preventDefault();
		$('#btn-address-relabel-save').click();
	});
});

$(document).ready(function() {
	$('#btn-address-relabel-save').on('click', function(e) {
		$('#modal-address-relabel').modal('toggle');
		var targetForm = $('#form-address-relabel');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Address relabeled successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to relabel the specified address... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});

$(document).ready(function() {
	$('#address-table').on('click', 'a.btn-address-delete', function(e) {
		e.preventDefault();
		var targetForm = $(this).siblings('#form-address-delete');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Address deleted successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to delete the specified address... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});

$(document).ready(function() {
	$('#address-table').on('click', 'a.btn-address-hide', function(e) {
		e.preventDefault();
		var targetForm = $(this).siblings('#form-address-hide');
		$.ajax({
			url: $(targetForm).attr('action'),
			method: $(targetForm).attr('method'),
			data: $(targetForm).serialize(),
			success: function() {
				$('#alert-success-heading').text('Done!');
				$('#alert-success-body').text('Address hidden successfully.');
				$('#alert-success').show(400);
				setTimeout(function() {location.reload();}, 1500);
			},
			error: function() {
				$('#alert-danger-heading').text('Whoops!');
				$('#alert-danger-body').text('Failed to hide the specified address... (try again?)');
				$('#alert-danger').show(400);
				setTimeout(function() {location.reload();}, 2500);
			}
		});
	});
});