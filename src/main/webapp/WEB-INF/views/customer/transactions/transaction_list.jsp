
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12 layer-top panel-base-menu">
									<div class="pull-right" role="navigation">
										<ul class="nav nav-pills">
											<li class="active" role="presentation">
												<a class="panel-base-menu-pill" href="">Transactions</a>
											</li>
											<li role="presentation">
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
									<h2 class="panel-base-heading">Transactions</h2>
									<h3 class="page-header panel-base-subheading">
										<small>Overview of your most recent transactions</small>
									</h3>
									<table class="table table-hover table-navigable table-striped panel-item-margin-tall">
										<thead>
											<tr>
												<th class="th-transaction-type"></th>
												<th class="th-date-narrow">Date / Time</th>
												<th class="th-address">From</th>
												<th class="th-address">To</th>
												<th class="th-confirmations">Confirmations</th>
												<th class="th-amount-narrow">Amount</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="td-transaction-type">
													<a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#" title="Incoming transaction">
														<i class="fa fa-long-arrow-right incoming-txn-glyph" aria-hidden="true"></i>
														<!--<i class="fa fa-long-arrow-left outgoing-txn-glyph" aria-hidden="true"></i>-->
														<!--<i class="fa fa-exchange internal-transfer-glyph" aria-hidden="true"></i>-->
													</a>
												</td>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													2015-05-31 07:30:55
												</a></td>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													miH27nBXbKuhiie2EndaL7HpyEU3Pfjmu4
													<!--<span class="label label-primary table-label">2 addresses</span>-->
												</a></td>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													muuxD5NeoQnhq5muyizhYGC62AMW9Za2Eg
													<!--<span class="label label-primary table-label">3 addresses</span>-->
												</a></td>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													493
													<span class="label label-warning table-label">Unconfirmed</span>
													<!--<span class="label label-info table-label">Confirmed</span>-->
													<!--<span class="label label-success table-label">Completed</span>-->
													<!--<span class="label label-danger table-label">Failed</span>-->
													<!--<span class="label label-danger table-label">Dropped</span>-->
												</a></td>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													0.2 BTC
												</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2 col-md-offset-5 text-center">
									<div role="navigation">
										<ul class="pagination pagination-bottom">
											<li class="disabled">
												<a href="${requestScope['javax.servlet.forward.request_uri']}#" aria-label="Previous">
													<span aria-hidden="true">&laquo;</span>
												</a>
											</li>
											<li class="active">
												<a href="${requestScope['javax.servlet.forward.request_uri']}#">
													1<span class="sr-only">(current)</span>
												</a>
											</li>
											<li class="disabled">
												<a href="${requestScope['javax.servlet.forward.request_uri']}#" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
												</a>
											</li>
										</ul>
									</div>
								</div>
								<div class="col-md-5 text-right">
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