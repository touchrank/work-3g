function referenceExcelDownload() {
	var form = document.createElement('form');
	form.action = '/ha-web/result-reference-excelDownload.html';
	form.method = 'get';
	document.body.appendChild(form);
	form.submit();
}
function referenceCsvDownload() {
	var form = document.createElement('form');
	form.action = '/ha-web/result-reference-csvDownload';
	form.method = 'get';
	document.body.appendChild(form);
	form.submit();
}
