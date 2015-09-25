<%@ include file="address_new.jsp" %>

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
											<li role="presentation">
												<a class="panel-base-menu-pill" href="">Send money</a>
											</li>
											<li class="active" role="presentation">
												<a class="panel-base-menu-pill" href="">Receive money</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 layer-middle">
									<h2 class="panel-base-heading">Addresses</h2>
									<h3 class="page-header panel-base-subheading">
										<small>Overview of your personal (receiving) addresses</small>
									</h3>
									<table class="table table-hover table-navigable table-striped panel-item-margin-tall">
										<thead>
											<tr>
												<th>Label</th>
												<th>Address</th>
												<th class="th-balance">Balance</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													Freelance earnings address #1
												</a></td>
												<td><a class="table-linK" href="${requestScope['javax.servlet.forward.request_uri']}#">
													mxzsZRCAzMQKD8BpgPhfbKywSQERF7eLEr
												</a></td>
												<td><a class="table-link" href="${requestScope['javax.servlet.forward.request_uri']}#">
													0.1399
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
									<a class="btn btn-success body-btn-margin-shallow" data-toggle="modal" href="#modal-address-new">
										<i class="btn-create-new-glyph fa fa-plus" aria-hidden="true"></i>
										New address
									</a>							
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>