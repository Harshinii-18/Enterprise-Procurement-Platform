var employeeId = context.getVariable("employeeId");

var regex = /^EMP[0-9]{4}$/;

if (!regex.test(employeeId)) {
    context.setVariable("employee.validation.failed", true);
}