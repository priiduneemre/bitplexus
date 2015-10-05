<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sc" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="bpl" uri="http://www.bitplexus.com/jstl/tags/layout" %>

<sc:eval expression="T(com.neemre.bitplexus.common.dto.VisitDto)" var="VisitDto"/> 

<c:url value="member/activity" var="pageBaseUrl"/>

<bpl:page activeMenuItem="Account" title="Recent activity">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-base panel-default">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<h2 class="panel-base-heading">Recent activity</h2>
									<h3 class="page-header panel-base-subheading">
										<small>Overview of all recent security-related events for this account</small>
									</h3>
									<table class="table table-hover table-striped panel-item-margin-tall" id="event-table">
										<thead>
											<tr>
												<th class="th-index">#</th>
												<th class="th-date-wide-pct">Date / Time</th>
												<th>Description</th>
												<th class="th-ip-address-pct">IP address</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="visibleRowCount" value="${0}"/>
											<c:forEach begin="0" end="${fn:length(events) - 1}" varStatus="status">
												<tr>
													<td><a class="table-link" href="${pageBaseUrl}">
														<c:out value="${status.index + 1}"/>
													</a></td>
													<c:if test="${events[status.index]['class'].simpleName eq VisitDto.simpleName}">
														<td><a class="table-link" href="${pageBaseUrl}">
															<fmt:formatDate pattern="MMM dd, YYYY hh:mm a" value="${events[status.index].loginAt}"/>
														</a></td>
														<td><a class="table-link" href="${pageBaseUrl}">
															<i class="fa fa-sign-in sign-in-glyph" aria-hidden="true"></i>
															Successfully logged in
														</a></td>
													</c:if>
													<td><a class="table-link" href="${pageBaseUrl}">
														<c:out value="${events[status.index].ipAddress}"/>
													</a></td>
												</tr>
												<c:set var="visibleRowCount" value="${visibleRowCount + 1}"/>
											</c:forEach>
											<c:if test="${visibleRowCount eq 0}">
												<tr>
													<td colspan="4">There are no recent events to show.</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row">
								<div class="col-md-2 col-md-offset-5 text-center">
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</bpl:page>