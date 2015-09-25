
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12 layer-top panel-base-menu">
									<div class="pull-right" role="navigation">
										<ul class="nav nav-pills">
											<li role="presentation">
												<a class="panel-base-menu-pill" href="">Transactions</a>
											</li>
											<li class="active" role="presentation">
												<a class="panel-base-menu-pill" href="">Send money</a>
											</li>
											<li role="presentation">
												<a class="panel-base-menu-pill" href="">Receive money</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 layer-middle">
									<h2 class="panel-base-heading">Send money</h2>
									<h3 class="page-header panel-base-subheading">
										<small>Transfer funds from this wallet to an external address</small>
									</h3>
									<div class="row">
										<div class="col-md-12 panel-base-rates">
											<div class="pull-right">
												<div>
													<img alt="Bitcoin (testnet3 chain)" class="currency-icon-sm" src="static/img/node/bitcoin/testnet3_icon_200x200.png"/>
													1 BTC = 231.09 USD
												</div>
												<div>
													<img alt="Litecoin (testnet3 chain)" class="currency-icon-sm" src="static/img/node/litecoin/testnet3_icon_200x200.png"/>
													1 LTC = 1.78 USD
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 col-md-offset-3">
											<div class="row">
												<div class="col-md-10 col-md-offset-1">
													<form class="panel-base-form" method="post" role="form">
														<div class="form-group">
															<label for="recipient">Recipient</label>
															<input class="form-control" id="recipient" placeholder="Address or label" type="text" autocomplete="off" autofocus required/>
														</div>
														<div class="form-group">
															<label for="amount">Amount</label>
															<div class="row">
																<div class="col-md-6 col-padding-slim-right">
																	<div class="input-group">
																		<input class="form-control" id="amount" placeholder="0.0000" type="text" aria-describedby="crypto-abbreviation" autocomplete="off" required/>
																		<span class="input-group-addon" id="crypto-abbreviation">BTC</span>
																	</div>
																</div>
																<div class="col-md-6 col-padding-slim-left">
																	<div class="input-group">
																		<input class="form-control" id="amount-usd" placeholder="0.00" type="text" aria-describedby="usd-abbreviation" autocomplete="off" readonly required/>
																		<span class="input-group-addon" id="usd-abbreviation">USD</span>
																	</div>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label for="note">Message</label>
															<textarea class="form-control" id="note" placeholder="Additional notes (optional)" rows="3"></textarea>
														</div>
														<button class="btn btn-success body-btn-margin-short" type="submit">
															<i class="btn-send-glyph fa fa-send" aria-hidden="true"></i>
															Send payment
														</button>
													</form>
												</div>
											</div>
										</div>
									</div>						
								</div>
							</div>
							<div class="row">
								<div class="col-md-5 col-md-offset-7 text-right">
									<a class="btn btn-primary body-btn-margin-shallow" href="">
										<i class="btn-back-glyph fa fa-chevron-left" aria-hidden="true"></i>
										Back to subwallets
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>