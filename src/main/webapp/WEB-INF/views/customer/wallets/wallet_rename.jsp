<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

		<div class="modal fade" id="modal-wallet-rename" role="dialog" tabindex="-1" aria-labelledBy="modal-heading">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close modal-header-close-btn" data-dismiss="modal" type="button" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="modal-header-heading modal-title" id="modal-heading">Rename wallet</h3>
						<h4 class="modal-header-subheading">
							<small>Please enter a new name for your wallet</small>
						</h4>
					</div>
					<div class="modal-body">
						<sf:form action="" id="form-wallet-rename" method="post" modelAttribute="wallet" role="form">
							<div class="form-group">
								<label for="name">Name</label>
								<sf:input class="form-control" id="name" maxlength="50" path="name" placeholder="Name (e.g. 'Tom's savings wallet')" tabindex="1" type="text" autocomplete="off" autofocus="autofocus" required="required"/>
							</div>
						</sf:form>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal" tabindex="2" type="button">Cancel</button>
						<button class="btn btn-primary" id="btn-wallet-rename-save" tabindex="3" type="button">Save changes</button>
					</div>
				</div>
			</div>
		</div>