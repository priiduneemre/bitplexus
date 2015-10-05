<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.AddressStateTypes).ALLOCATED" var="ALLOCATED_STATE_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.AddressStateTypes).ACTIVE" var="ACTIVE_STATE_TYPE"/>

<c:url value="customer/wallets/${walletId}/subwallets/${subwalletId}/addresses" var="pageBaseUrl"/>

<bpl:page activeMenuItem="Wallets" title="Addresses">
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
												<a class="panel-base-menu-pill" href="${pageBaseUrl}/../transactions">Transactions</a>
											</li>
											<li role="presentation">
												<a class="panel-base-menu-pill" href="${pageBaseUrl}/../transactions/new">Send money</a>
											</li>
											<li class="active" role="presentation">
												<a class="panel-base-menu-pill" href="${pageBaseUrl}">Receive money</a>
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
									<div class="alert alert-success alert-dismissible <c:if test="${empty success}">element-hidden</c:if> panel-base-alert" id="alert-success" role="alert">
										<button class="close" data-dismiss="alert" type="button" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<strong id="alert-success-heading"></strong>
										<span id="alert-success-body"></span>
									</div>
									<div class="alert alert-danger alert-dismissible <c:if test="${empty danger}">element-hidden</c:if> panel-base-alert" id="alert-danger" role="alert">
										<button class="close" data-dismiss="alert" type="button" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<strong id="alert-danger-heading"></strong>
										<span id="alert-danger-body"></span>
									</div>
									<table class="table table-hover table-navigable table-striped panel-item-margin-tall" id="address-table">
										<thead>
											<tr>
												<th class="th-label-pct">Label</th>
												<th>Address</th>
												<th class="th-balance">Balance</th>
												<th class="th-manage"></th>
											</tr>
										</thead>
										<tbody>
											<c:set var="visibleRowCount" value="${0}"/>
											<c:forEach var="address" items="${addresses}">
												<c:if test="${(address.addressStateType.code eq ALLOCATED_STATE_TYPE.name) or (address.addressStateType.code eq ACTIVE_STATE_TYPE.name)}">
													<tr>
														<td><a class="table-link" data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="Date created: <fmt:formatDate pattern="MMM dd, YYYY hh:mm a" value="${address.indexedAt}"/>">
															<i class="fa fa-credit-card address-glyph" aria-hidden="true"></i>
															<c:out value="${address.label}"/>
														</a></td>
														<td><a class="table-link" data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="Type: ${address.addressType.name}">
															<c:out value="${address.encodedForm}"/>
														</a></td>
														<td><a class="table-link" data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="No. of transactions: ${address.transactionCount}<br/>Total received: <fmt:formatNumber groupingUsed="true" minFractionDigits="8" maxFractionDigits="8" type="number" value="${address.totalReceived}"/>&nbsp;${currency.abbreviation}<br/>Total sent: <fmt:formatNumber groupingUsed="true" minFractionDigits="8" maxFractionDigits="8" type="number" value="${address.totalSent}"/>&nbsp;${currency.abbreviation}">
															<fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="8" type="number" value="${address.balance}"/>
															<c:out value=" "/><c:out value="${currency.abbreviation}"/>
														</a></td>
														<td>
															<div class="dropdown table-dropdown">
																<button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" role="button" tabindex="${visibleRowCount + 1}" type="button" aria-expanded="false" aria-haspopup="true">
																	<span>Manage</span>
																	<span class="caret"></span>
																</button>
																<ul class="dropdown-menu" role="menu">
																	<li>
																		<a class="btn-address-hide" href="${pageBaseUrl}">Hide address</a>
																		<sf:form action="${pageBaseUrl}/${address.addressId}/hide" id="form-address-hide" method="post" modelAttribute="address" role="form"></sf:form>
																	</li>
																	<li>
																		<a class="btn-address-relabel" data-address-label="${address.label}" data-form-action="${pageBaseUrl}/${address.addressId}/relabel" data-toggle="modal" href="#modal-address-relabel">Relabel address</a>
																	</li>
																	<li>
																		<a class="btn-address-delete" href="${pageBaseUrl}">Delete address</a>
																		<sf:form action="${pageBaseUrl}/${address.addressId}/delete" id="form-address-delete" method="post" modelAttribute="address" role="form"></sf:form>
																	</li>
																</ul>
															</div>
														</td>
													</tr>
													<c:set var="visibleRowCount" value="${visibleRowCount + 1}"/>
												</c:if>
											</c:forEach>
											<c:if test="${visibleRowCount eq 0}">
												<tr>
													<td colspan="4">There are no addresses to show.</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2 col-md-offset-5 layer-bottom text-center">
									<div role="navigation">
										<ul class="pagination pagination-bottom">
											<li class="disabled">
												<a href="${pageBaseUrl}" aria-label="Previous">
													<span aria-hidden="true">&laquo;</span>
												</a>
											</li>
											<li class="active">
												<a href="${pageBaseUrl}">
													1<span class="sr-only">(current)</span>
												</a>
											</li>
											<li class="disabled">
												<a href="${pageBaseUrl}" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
												</a>
											</li>
										</ul>
									</div>									
								</div>
								<div class="col-md-5 text-right">
									<a class="btn btn-primary body-btn-margin-shallow" href="${pageBaseUrl}/../../../subwallets">
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
<jsp:include page="address_new.jsp"/>
<jsp:include page="address_relabel.jsp"/>
</bpl:page>