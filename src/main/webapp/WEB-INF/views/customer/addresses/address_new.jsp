<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

		<div class="modal fade" id="modal-address-new" role="dialog" tabindex="-1" aria-labelledBy="modal-heading">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close modal-header-close-btn" data-dismiss="modal" type="button" aria-label="Close">
							<span aria-hidden="true">&times;</span>						
						</button>
						<h3 class="modal-header-heading modal-title" id="modal-heading">Add new address</h3>
						<h4 class="modal-header-subheading">
							<small>Please enter a label for your address</small>
						</h4>
					</div>
					<div class="modal-body">
						<sf:form action="${pageBaseUrl}" id="form-address-new" method="post" modelAttribute="address" role="form">
							<div class="form-group">
								<label for="label">Label</label>
								<sf:input class="form-control" id="label" maxlength="60" path="label" placeholder="Label (e.g. 'Freelance earnings address #1')" tabindex="1" type="text" autocomplete="off" autofocus="autofocus" required="required"/>
							</div>
						</sf:form>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal" tabindex="2" type="button">Cancel</button>
						<button class="btn btn-primary" id="btn-address-new-save" tabindex="3" type="button">Save changes</button> 
					</div>
				</div>
			</div>
		</div>