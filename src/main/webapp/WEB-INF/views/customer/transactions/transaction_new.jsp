<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<c:url value="customer/wallets/${walletId}/subwallets/${subwalletId}/transactions/new" var="pageBaseUrl"/>

<bpl:page activeMenuItem="Wallets" title="Send money">
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
												<a class="panel-base-menu-pill" href="${pageBaseUrl}/../../transactions">Transactions</a>
											</li>
											<li class="active" role="presentation">
												<a class="panel-base-menu-pill" href="${pageBaseUrl}">Send money</a>
											</li>
											<li role="presentation">
												<a class="panel-base-menu-pill" href="${pageBaseUrl}/../../addresses">Receive money</a>
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
										<div class="col-md-12 layer-middle panel-base-rates">
											<div class="pull-right">
												<c:forEach var="chain" items="${chains}">
													<c:set var="currencyNameLc" value="${fn:toLowerCase(fn:substringBefore(chain.key.code, '_'))}"/>
													<c:set var="currencyName" value="${fn:toUpperCase(fn:substring(currencyNameLc, 0, 1))}${fn:substring(currencyNameLc, 1, fn:length(currencyNameLc))}"/>
													<c:set var="chainName" value="${fn:toLowerCase(fn:substringBefore(chain.key.name, ' '))}"/>
													<div>
														<img alt="${currencyName} (${chainName} chain)" class="currency-icon-sm" src="static/img/node/${currencyNameLc}/${chainName}_icon_200x200.png"/>
														<span>1</span>
														<c:forEach var="currency" items="${currencies}">
															<c:if test="${currency.name eq currencyName}">
																<c:out value=" "/><c:out value="${currency.abbreviation}"/>
															</c:if>
														</c:forEach>
														<c:out value=" "/><span>=</span>
														<fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="2" type="number" value="${chain.value}"/>
														<c:out value=" "/><span>USD</span>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 layer-top">
											<div class="alert alert-success alert-dismissible <c:if test="${empty success}">element-hidden</c:if> panel-base-alert" id="alert-success" role="alert">
												<button class="close" data-dismiss="alert" type="button" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<strong id="alert-success-heading"><c:out value="${successHeading}"/></strong>
												<span id="alert-success-body"><c:out value="${successBody}"/></span>
											</div>
											<div class="alert alert-danger alert-dismissible <c:if test="${empty danger}">element-hidden</c:if> panel-base-alert" id="alert-danger" role="alert">
												<button class="close" data-dismiss="alert" type="button" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<strong id="alert-danger-heading"><c:out value="${dangerHeading}"/></strong>
												<span id="alert-danger-body"><c:out value="${dangerBody}"/></span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 col-md-offset-3">
											<div class="row">
												<div class="col-md-10 col-md-offset-1">
													<sf:form action="${pageBaseUrl}/../../transactions" class="panel-base-form" id="form-transaction-new" method="post" modelAttribute="paymentDetails" role="form">
														<div class="form-group">
															<label for="recipient">Recipient</label>
															<sf:input class="form-control" id="recipient" maxlength="60" path="recipientAddress" placeholder="Address or label" tabindex="1" type="text" autocomplete="off" autofocus="autofocus" required="required"/>
														</div>
														<div class="form-group">
															<label for="amount">Amount</label>
															<div class="row">
																<div class="col-md-6 col-padding-slim-right">
																	<div class="input-group">
																		<sf:input class="form-control" id="amount" min="0.00000001" path="amount" placeholder="0.0000" step="0.00000001" tabindex="2" type="number" aria-describedby="crypto-abbreviation" autocomplete="off" required="required"/>
																		<span class="input-group-addon" id="crypto-abbreviation"><c:out value="${currency.abbreviation}"/></span>
																	</div>
																</div>
																<div class="col-md-6 col-padding-slim-left">
																	<div class="input-group">
																		<span class="hidden" id="chain-rate"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="8" type="number" value="${chainRate}"/></span>
																		<input class="form-control" id="amount-usd" min="0.0001" placeholder="0.00" step="0.0001" type="number" aria-describedby="usd-abbreviation" autocomplete="off" readonly="readonly" required="required"/>
																		<span class="input-group-addon" id="usd-abbreviation">USD</span>
																	</div>
																</div>
															</div>
														</div>
														<div class="form-group">
															<label for="note">Message</label>
															<sf:textarea class="form-control" id="note" maxlength="255" path="note" placeholder="Additional notes (optional)" rows="3" tabindex="3"></sf:textarea>
														</div>
														<button class="btn btn-success body-btn-margin-short" tabindex="4" type="submit">
															<i class="btn-send-glyph fa fa-send" aria-hidden="true"></i>
															Send payment
														</button>
													</sf:form>
												</div>
											</div>
										</div>
									</div>						
								</div>
							</div>
							<div class="row">
								<div class="col-md-5 col-md-offset-7 text-right">
									<a class="btn btn-primary body-btn-margin-shallow" href="${pageBaseUrl}/../../../../subwallets">
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
</bpl:page>