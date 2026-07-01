var supplierId = context.getVariable("supplierId");

var regex = /^SUP[0-9]{3}$/;

if (!regex.test(supplierId)) {
    context.setVariable("supplier.validation.failed", true);
}