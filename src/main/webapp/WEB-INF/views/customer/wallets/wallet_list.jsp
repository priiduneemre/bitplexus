<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.WalletStateTypes).CREATED" var="CREATED_STATE_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.WalletStateTypes).ACTIVE" var="ACTIVE_STATE_TYPE"/>

<c:url value="customer/wallets" var="pageBaseUrl"/>

<bpl:page activeMenuItem="Wallets" title="Wallets">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<h2 class="panel-base-heading">Wallets</h2>
									<h3 class="page-header panel-base-subheading">
										<small>Use wallets to separate your funds into multiple logical subgroups</small>
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
									<table class="table table-hover table-navigable table-striped panel-item-margin-tall" id="wallet-table">
										<thead>
											<tr>
												<th>Name</th>
												<th class="th-date-wide">Date created</th>
												<th class="th-balance">Balance</th>
												<th class="th-manage"></th>
											</tr>
										</thead>
										<tbody>
											<c:set var="visibleRowCount" value="${0}"/>
											<c:forEach var="wallet" items="${wallets}">
												<c:if test="${(wallet.walletStateType.code eq CREATED_STATE_TYPE.name) or (wallet.walletStateType.code eq ACTIVE_STATE_TYPE.name)}">
													<tr>
														<td><a class="table-link" href="${pageBaseUrl}/${wallet.walletId}/subwallets">
															<i class="fa fa-database wallet-glyph" aria-hidden="true"></i>
															<c:out value="${wallet.name}"/>
														</a></td>
														<td><a class="table-link" href="${pageBaseUrl}/${wallet.walletId}/subwallets">
															<fmt:formatDate pattern="MMM dd, YYYY hh:mm a" value="${wallet.createdAt}"/>
														</a></td>
														<td><a class="table-link" href="${pageBaseUrl}/${wallet.walletId}/subwallets">
															<fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" type="number" value="${wallet.balance}"/>
															USD
														</a></td>
														<td>
															<div class="dropdown table-dropdown">
																<button class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown" role="button" tabindex="${visibleRowCount + 1}" type="button" aria-expanded="false" aria-haspopup="true">
																	<span>Manage</span>
																	<span class="caret"></span>
																</button>
																<ul class="dropdown-menu" role="menu">
																	<li>
																		<a class="btn-wallet-rename" data-wallet-name="${wallet.name}" data-form-action="${pageBaseUrl}/${wallet.walletId}/rename" data-toggle="modal" href="#modal-wallet-rename">Rename wallet</a>
																	</li>
																	<li>
																		<a class="btn-wallet-archive" href="${pageBaseUrl}">Archive wallet</a>
																		<sf:form action="${pageBaseUrl}/${wallet.walletId}/archive" id="form-wallet-archive" method="post" modelAttribute="wallet" role="form"></sf:form>
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
													<td colspan="3">There are no wallets to show.</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-5 col-md-offset-7 text-right">
									<a class="btn btn-success body-btn-margin-shallow" data-toggle="modal" href="#modal-wallet-new">
										<i class="btn-create-new-glyph fa fa-plus" aria-hidden="true"></i>
										New wallet
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<jsp:include page="wallet_new.jsp"/>
<jsp:include page="wallet_rename.jsp"/>
</bpl:page>