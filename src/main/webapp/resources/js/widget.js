
$(document).ready(function() {
	$('#modal-wallet-rename').on('show.bs.modal', function(e) {
		var walletName = $(e.relatedTarget).data('wallet-name');
		var formAction = $(e.relatedTarget).data('form-action');
		$(e.currentTarget).find('#name').val(walletName);
		$(e.currentTarget).find('#form-wallet-rename').attr('action', formAction);
	});
});

$(document).ready(function() {
	$('#modal-address-relabel').on('show.bs.modal', function(e) {
		var addressLabel = $(e.relatedTarget).data('address-label');
		var formAction = $(e.relatedTarget).data('form-action');
		$(e.currentTarget).find('#label').val(addressLabel);
		$(e.currentTarget).find('#form-address-relabel').attr('action', formAction);
	});
});

$(document).ready(function() {
	$('#modal-wallet-new').on('shown.bs.modal', function(e) {
		$(e.currentTarget).find('#name').focus();
	});
});

$(document).ready(function() {
	$('#modal-wallet-rename').on('shown.bs.modal', function(e) {
		$(e.currentTarget).find('#name').focus();
	});
});

$(document).ready(function() {
	$('#modal-address-new').on('shown.bs.modal', function(e) {
		$(e.currentTarget).find('#label').focus();
	});
});

$(document).ready(function() {
	$('#modal-address-relabel').on('shown.bs.modal', function(e) {
		$(e.currentTarget).find('#label').focus();
	});
});

$(document).ready(function() {
	$('#amount').on('input', function(e) {
		var amount = parseFloat($(this).val());
		var chainRate = parseFloat($('#chain-rate').text());
		var amountUsd = (amount * chainRate).toFixed(4);
		if (isNaN(amountUsd)) {
			amountUsd = (0).toFixed(2);
		}
		$('#amount-usd').attr('placeholder', amountUsd);
	});
});