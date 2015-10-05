<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<c:url value="customer/wallets/${walletId}/subwallets" var="pageBaseUrl"/>

<bpl:page activeMenuItem="Wallets" title="Subwallets">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<h2 class="panel-base-heading">Subwallets</h2>
									<h3 class="page-header panel-base-subheading">
										<small>Use subwallets to quickly manage your funds across multiple currencies/chains</small>
									</h3>
									<table class="table table-hover table-navigable table-striped panel-item-margin-tall" id="subwallet-table">
										<thead>
											<tr>
												<th>Currency / Chain</th>
												<th class="th-balance">Balance</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="visibleRowCount" value="${0}"/>
											<c:forEach var="subwallet" items="${subwallets}">
												<c:set var="currencyNameLc" value="${fn:toLowerCase(fn:substringBefore(subwallet.key.code, '_'))}"/>
												<c:set var="currencyName" value="${fn:toUpperCase(fn:substring(currencyNameLc, 0, 1))}${fn:substring(currencyNameLc, 1, fn:length(currencyNameLc))}"/>
												<c:set var="chainName" value="${fn:toLowerCase(fn:substringBefore(subwallet.key.name, ' '))}"/>
												<tr>
													<td><a class="table-link" href="${pageBaseUrl}/${subwallet.key.chainId}/transactions">
														<img alt="${currencyName} (${chainName} chain)" class="currency-icon-sm" src="static/img/node/${currencyNameLc}/${chainName}_icon_200x200.png"/>
														<c:out value="${currencyName}"/> (<c:out value="${chainName}"/> chain)
													</a></td>
													<td><a class="table-link" href="${pageBaseUrl}/${subwallet.key.chainId}/transactions">
														<fmt:formatNumber groupingUsed="true" minFractionDigits="2" maxFractionDigits="8" type="number" value="${subwallet.value}"/>
														<c:forEach var="currency" items="${currencies}">
															<c:if test="${currency.name eq currencyName}">
																<c:out value=" "/><c:out value="${currency.abbreviation}"/>
															</c:if>	
														</c:forEach>
													</a></td>
												</tr>
												<c:set var="visibleRowCount" value="${visibleRowCount + 1}"/>
											</c:forEach>
											<c:if test="${visibleRowCount eq 0}">
												<tr>
													<td colspan="2">There are no subwallets to show.</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-5 col-md-offset-7 text-right">
									<a class="btn btn-primary body-btn-margin-shallow" href="${pageBaseUrl}/../../../wallets">
										<i class="btn-back-glyph fa fa-chevron-left" aria-hidden="true"></i>
										Back to wallets
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</bpl:page>