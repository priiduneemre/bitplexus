<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<sc:eval expression="T(com.neemre.bitplexus.common.dto.enums.TransactionTypes).INCOMING" var="INCOMING_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.common.dto.enums.TransactionTypes).INTERNAL" var="INTERNAL_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.common.dto.enums.TransactionTypes).OUTGOING" var="OUTGOING_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes).UNCONFIRMED" var="UNCONFIRMED_STATUS_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes).CONFIRMED" var="CONFIRMED_STATUS_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes).COMPLETED" var="COMPLETED_STATUS_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes).FAILED" var="FAILED_STATUS_TYPE"/>
<sc:eval expression="T(com.neemre.bitplexus.backend.model.enums.TransactionStatusTypes).DROPPED" var="DROPPED_STATUS_TYPE"/>

<c:url value="customer/wallets/${walletId}/subwallets/${subwalletId}/transactions" var="pageBaseUrl"/>

<bpl:page activeMenuItem="Wallets" title="Transactions">
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
												<a class="panel-base-menu-pill" href="${pageBaseUrl}">Transactions</a>
											</li>
											<li role="presentation">
												<a class="panel-base-menu-pill" href="${pageBaseUrl}/new">Send money</a>
											</li>
											<li role="presentation">
												<a class="panel-base-menu-pill" href="${pageBaseUrl}/../addresses">Receive money</a>
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
									<table class="table table-hover table-navigable table-striped panel-item-margin-tall" id="transaction-table">
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
											<c:set var="visibleRowCount" value="${0}"/>
											<c:forEach var="transaction" items="${transactions}">
												<tr>
													<td class="td-transaction-type">
														<a class="table-link" data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="${transaction.transactionType.name}">
															<c:choose>
																<c:when test="${transaction.transactionType.name eq INCOMING_TYPE.name}">
																	<i class="fa fa-long-arrow-right incoming-txn-glyph" aria-hidden="true"></i>
																</c:when>
																<c:when test="${transaction.transactionType.name eq INTERNAL_TYPE.name}">
																	<i class="fa fa-exchange internal-transfer-glyph" aria-hidden="true"></i>
																</c:when>
																<c:when test="${transaction.transactionType.name eq OUTGOING_TYPE.name}">
																	<i class="fa fa-long-arrow-left outgoing-txn-glyph" aria-hidden="true"></i>
																</c:when>
															</c:choose>
														</a>
													</td>
													<td><a class="table-link" data-delay='{"hide":"3000"}' data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="Network ID: ${transaction.networkUid}">
														<fmt:formatDate pattern="MMM dd, YYYY hh:mm a" value="${transaction.receivedAt}"/>
													</a></td>
													<c:forEach var="transactionInput" items="${transaction.transactionInputs}">
														<c:set value="${transactionInput.encodedForm}<br/>" var="fromAddress"/>
														<c:set value="${fromAddresses}${fromAddress}" var="fromAddresses"/>
													</c:forEach>
													<td><a class="table-link" data-delay='{"hide":"3000"}' data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="${fromAddresses}">
														<c:choose>
															<c:when test="${fn:length(transaction.transactionInputs) eq 1}">
																<c:out value="${transaction.transactionInputs[0].encodedForm}"/>
															</c:when>
															<c:when test="${fn:length(transaction.transactionInputs) gt 1}">
																<span class="label label-primary table-label">${fn:length(transaction.transactionInputs)} addresses</span>
															</c:when>
														</c:choose>
													</a></td>
													<c:remove var="fromAddresses"/>
													<c:forEach var="transactionOutput" items="${transaction.transactionOutputs}">
														<c:set value="${transactionOutput.encodedForm}<br/>" var="toAddress"/>
														<c:set value="${toAddresses}${toAddress}" var="toAddresses"/>
													</c:forEach>
													<td><a class="table-link" data-delay='{"hide":"3000"}' data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="${toAddresses}">
														<c:choose>
															<c:when test="${fn:length(transaction.transactionOutputs) eq 1}">
																<c:out value="${transaction.transactionOutputs[0].encodedForm}"/>
															</c:when>
															<c:when test="${fn:length(transaction.transactionOutputs) gt 1}">
																<span class="label label-primary table-label">${fn:length(transaction.transactionOutputs)} addresses</span>
															</c:when>
														</c:choose>
													</a></td>
													<c:remove var="toAddresses"/>
													<td><a class="table-link" data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="<c:choose><c:when test="${transaction.blockHeight eq null}">Block: n/a</c:when><c:otherwise>Block: #<fmt:formatNumber groupingUsed="true" type="number" value="${transaction.blockHeight}"/></c:otherwise></c:choose><br/>Size: <fmt:formatNumber groupingUsed="true" type="number" value="${transaction.binarySize}"/>&nbsp;bytes">
														<c:out value="${transaction.confirmations}"/><c:out value=" "/>
														<c:choose>
															<c:when test="${transaction.transactionStatusType.code eq UNCONFIRMED_STATUS_TYPE.name}">
																<span class="label label-warning table-label">Unconfirmed</span>
															</c:when>
															<c:when test="${transaction.transactionStatusType.code eq CONFIRMED_STATUS_TYPE.name}">
																<span class="label label-info table-label">Confirmed</span>
															</c:when>
															<c:when test="${transaction.transactionStatusType.code eq COMPLETED_STATUS_TYPE.name}">
																<span class="label label-success table-label">Completed</span>
															</c:when>
															<c:when test="${transaction.transactionStatusType.code eq FAILED_STATUS_TYPE.name}">
																<span class="label label-danger table-label">Failed</span>
															</c:when>
															<c:when test="${transaction.transactionStatusType.code eq DROPPED_STATUS_TYPE.name}">
																<span class="label label-danger table-label">Dropped</span>
															</c:when>
														</c:choose>
													</a></td>
													<td><a class="table-link" data-html="true" data-placement="bottom" data-toggle="tooltip" href="${pageBaseUrl}" title="Total input: <fmt:formatNumber groupingUsed="true" minFractionDigits="8" maxFractionDigits="8" type="number" value="${transaction.totalInput}"/>&nbsp;${currency.abbreviation}<br/>Total output: <fmt:formatNumber groupingUsed="true" minFractionDigits="8" maxFractionDigits="8" type="number" value="${transaction.totalOutput}"/>&nbsp;${currency.abbreviation}<br/>Fee: <fmt:formatNumber groupingUsed="true" minFractionDigits="8" maxFractionDigits="8" type="number" value="${transaction.fee}"/>&nbsp;${currency.abbreviation}">
														<fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="8" type="number" value="${transaction.amount}"/>
														<c:out value=" "/><c:out value="${currency.abbreviation}"/>
													</a></td>
												</tr>
												<c:set var="visibleRowCount" value="${visibleRowCount + 1}"/>
											</c:forEach>
											<c:if test="${visibleRowCount eq 0}">
												<tr>
													<td colspan="6">There are no transactions to show.</td>
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
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</bpl:page>