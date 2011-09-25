function prefillClear(field) {
	if (field.defaultValue==field.value) {field.value = '';}
	else if (field.value == '') {field.value = field.defaultValue;}
}

function initPie(charities){
	var data = [];
	var series = charities;
	for( var i = 0; i<series; i++)	{
		data[i] = { label: "Series"+(i+1), data: charities }
	}

	$.plot($("#heart-pie"), data, 
	{
		series: {
			pie: { 
				show: true,
				radius:300,
				label: {
					show: false,
					formatter: function(label, series){
						return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
					},
					threshold: 0.1
				}
			}
		},
		legend: {
			show: false
		}
	});	
}	

function submitToServer(){
  	var params = "";
	$('input[@type=checkbox]:checked').each(function(key, val){
		$("#results").append('<li>['+key+'] Name=['+val.name+', ID='+val.value+']</li>');
	});
	
    var charities = $('input[@type=checkbox]:checked').map(function(i,n) {
        return $(n).val();
    }).get(); //get converts it to an array

    if(charities.length == 0) { 
        charities = "none"; 
    }
	
	var total = $('#amount').val();
	var increment = total/charities.length;
	
	
	var url = "http://google.com";
    $.post(url, {'charities[]': charities, 'increment': increment}, function(response) {});
};


