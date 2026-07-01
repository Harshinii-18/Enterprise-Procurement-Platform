var purchaseOrderId = context.getVariable("purchaseOrderId");

var regex = /^45[0-9]{7}$/;

if (!regex.test(purchaseOrderId)) {
    context.setVariable("purchaseOrder.validation.failed", true);
}